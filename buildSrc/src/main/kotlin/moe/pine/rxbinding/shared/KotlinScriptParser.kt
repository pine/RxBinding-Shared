package moe.pine.rxbinding.shared

import com.intellij.openapi.util.Disposer
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.common.messages.MessageSeverityCollector
import org.jetbrains.kotlin.cli.jvm.compiler.CliLightClassGenerationSupport
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProvider
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.config.JVMConfigurationKeys
import org.jetbrains.kotlin.cli.jvm.config.addJavaSourceRoots
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoots
import org.jetbrains.kotlin.cli.jvm.config.getModuleName
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.addKotlinSourceRoots
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.frontend.java.di.createContainerForTopDownAnalyzerForJvm
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.load.java.JvmAbi
import org.jetbrains.kotlin.resolve.TopDownAnalysisContext
import org.jetbrains.kotlin.resolve.TopDownAnalysisMode
import org.jetbrains.kotlin.resolve.jvm.JvmAnalyzerFacade
import org.jetbrains.kotlin.resolve.jvm.TopDownAnalyzerFacadeForJVM
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory
import org.jetbrains.kotlin.utils.PathUtil
import java.io.File
import java.util.*
import java.util.logging.Logger

/**
 * KotlinScriptParser
 * Created by pine on 2016/03/30.
 */
class KotlinScriptParser(
        private val classPaths: List<Class<*>> = emptyList()
) {
    private val logger: Logger by lazy {
        Logger.getLogger(KotlinScriptParser::class.java.name)
    }

    private val messageCollector: MessageCollector by lazy {
        MessageSeverityCollector(SimpleMessageCollector(this.logger))
    }

    private val classPathFiles: List<File> by lazy {
        this.classPaths.map { PathUtil.getResourcePathForClass(it) }
    }

    fun parse(
            kotlinFiles: List<String>,
            javaRootPaths: List<String> = emptyList()
    ): TopDownAnalysisContext {
        val javaFiles = javaRootPaths.map { File(it) }
        javaFiles.forEach { if (!it.isDirectory) throw RuntimeException("$it is not directory") }
        return this.parseImpl(kotlinFiles, javaFiles)
    }

    private fun parseImpl(
            kotlinFiles: List<String>,
            javaFiles: List<File>
    ): TopDownAnalysisContext {
        val configuration = this.getConfiguration(kotlinFiles, javaFiles)
        val rootDisposable = Disposer.newDisposable()

        try {
            val environment = KotlinCoreEnvironment.createForProduction(
                    rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES)
            val ktFiles = environment.getSourceFiles()

            val sharedTrace = CliLightClassGenerationSupport.NoScopeRecordCliBindingTrace()
            val moduleContext = TopDownAnalyzerFacadeForJVM.createContextWithSealedModule(
                    environment.project, environment.getModuleName())

            val project = moduleContext.project
            val allFiles = JvmAnalyzerFacade.getAllFilesToAnalyze(project, null, ktFiles)
            val providerFactory = FileBasedDeclarationProviderFactory(moduleContext.storageManager, allFiles)
            val lookupTracker = LookupTracker.DO_NOTHING
            val packagePartProvider = JvmPackagePartProvider(environment)
            val container = createContainerForTopDownAnalyzerForJvm(
                    moduleContext,
                    sharedTrace,
                    providerFactory,
                    GlobalSearchScope.allScope(project),
                    lookupTracker,
                    packagePartProvider)

            val additionalProviders = ArrayList<PackageFragmentProvider>()
            additionalProviders.add(container.javaDescriptorResolver.packageFragmentProvider)

            return container.lazyTopDownAnalyzerForTopLevel.analyzeFiles(
                    TopDownAnalysisMode.LocalDeclarations, allFiles, additionalProviders)
        } finally {
            rootDisposable.dispose()
        }
    }

    private fun getConfiguration(
            kotlinFiles: List<String> = emptyList(),
            javaFiles: List<File> = emptyList()
    ): CompilerConfiguration {
        val configuration = CompilerConfiguration()
        configuration.put(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, this.messageCollector)
        configuration.put(JVMConfigurationKeys.MODULE_NAME, JvmAbi.DEFAULT_MODULE_NAME)
        configuration.addJvmClasspathRoots(PathUtil.getJdkClassesRoots())
        configuration.addJvmClasspathRoots(this.classPathFiles)
        configuration.addJavaSourceRoots(javaFiles)
        configuration.addKotlinSourceRoots(kotlinFiles)

        return configuration
    }
}