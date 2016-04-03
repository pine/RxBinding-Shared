package moe.pine.rxbinding.shared

import android.view.View
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.types.typeUtil.getImmediateSuperclassNotAny
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import org.jetbrains.kotlin.types.typeUtil.supertypes
import rx.Observable
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.util.*

/**
 * CodeGenerator
 * Created by pine on 2016/03/30.
 */
class CodeGenerator(
        val javaRootPaths: List<String> = emptyList()
) {
    val classPaths: List<Class<*>> by lazy {
        listOf(
                AnnotationTarget::class.java,
                View::class.java,
                Observable::class.java
        )
    }

    fun generate(
            namespace: String,
            kotlinFilePath: String,
            imports: List<String>,
            outputFilePath: String
    ): List<String> {
        val parser = KotlinScriptParser(this.classPaths)
        val ast = parser.parse(listOf(kotlinFilePath), this.javaRootPaths)
        val (enumTypes,sharedFunctions) = this.generateFunctions(ast.functions.toList())
        val kotlinCode = StringBuffer()

        kotlinCode.append("package $namespace\n")
        kotlinCode.append("\n")
        imports.forEach { kotlinCode.append("import $it\n") }
        kotlinCode.append("import moe.pine.rxbinding.shared.CachedObservable\n")
        kotlinCode.append("import moe.pine.rxbinding.shared.ObservableType\n")
        kotlinCode.append("import rx.Observable\n")
        kotlinCode.append("import rx.functions.*\n")
        kotlinCode.append("\n")
        kotlinCode.append(this.generateComment())
        kotlinCode.append(sharedFunctions.joinToString("\n\n"))

        File(outputFilePath).let { file ->
            file.parentFile.mkdirs()

            BufferedWriter(FileWriter(file)).let { writer ->
                writer.append(kotlinCode)
                writer.close()
            }
        }

        return enumTypes
    }

    fun generateEnumTypes(
            enumTypes: List<String>,
            outputFilePath: String
    ) {
        val kotlinCode = StringBuffer()
        kotlinCode.append("package moe.pine.rxbinding.shared\n")
        kotlinCode.append("\n")
        kotlinCode.append(this.generateComment())
        kotlinCode.append("enum class ObservableType {\n")

        enumTypes.distinct().sorted().forEach {
            kotlinCode.append("    $it,\n")
        }

        kotlinCode.append("}\n")

        File(outputFilePath).let { file ->
            file.parentFile.mkdirs()

            BufferedWriter(FileWriter(file)).let { writer ->
                writer.append(kotlinCode)
                writer.close()
            }
        }
    }

    private fun generateFunctions(
            functions: List<Pair<KtNamedFunction, SimpleFunctionDescriptor>>
    ) : Pair<List<String>, List<String>> {
        val enumTypes = mutableListOf<String>()
        val sharedFunctions = mutableListOf<String>()

        functions.forEach { function ->
            if (function.returnType?.name == "Observable") {
                val enumType = this.generateEnumType(function)
                enumTypes.add(enumType)
                sharedFunctions.add(this.generateFunction(function, enumType))
            }
        }

        return Pair(enumTypes, sharedFunctions)
    }

    private fun generateFunction(
            function: Pair<KtNamedFunction, SimpleFunctionDescriptor>,
            enumType: String
    ): String {
        val generics = function.extensionType?.toGenericsCode()
                .orEmpty().let { if (it.length > 0) "$it " else "" }

        val sharedFunction = StringBuffer()
        sharedFunction.append("fun ")
        sharedFunction.append(generics)
        sharedFunction.append(function.extensionType)
        sharedFunction.append(".")
        sharedFunction.append("shared" + function.name.capitalize())
        sharedFunction.append("(" + function.arguments.toArgumentsCode() + "): ")
        sharedFunction.append(function.returnType)
        sharedFunction.append(" {\n")
        sharedFunction.append("    return CachedObservable.getOrCreate(this, ObservableType.$enumType) {\n")
        sharedFunction.append("        " + function.body + "\n")
        sharedFunction.append("    }\n")
        sharedFunction.append("}")
        return sharedFunction.toString()
    }

    private fun generateEnumType(
            function: Pair<KtNamedFunction, SimpleFunctionDescriptor>
    ): String {
        var typeName = function.extensionType?.name

        if (function.extensionType?.isTypeParameter() ?: false) {
            typeName = function.extensionType?.supertypes()?.first()?.name
        }

        return typeName.orEmpty() + function.name.capitalize()
    }

    private fun generateComment() : String{
        val today = DateFormat.getDateTimeInstance().format(Date())
        val buffer = StringBuffer()
        buffer.append("/**\n")
        buffer.append(" * Auto generated\n")
        buffer.append(" * Created by CodeGenerator.kt on $today.\n")
        buffer.append(" */\n")
        return buffer.toString()
    }
}