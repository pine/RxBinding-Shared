package moe.pine.rxbinding.shared

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import java.util.logging.Logger

/**
 * SimpleMessageCollector
 * Created by pine on 2016/03/30.
 */
class SimpleMessageCollector(
        val logger: Logger
) : MessageCollector {
    override fun report(severity: CompilerMessageSeverity, message: String, location: CompilerMessageLocation) {
        val path = location.path
        val position = if (path == null) "" else "$path: (${location.line}, ${location.column}) "
        val text = position + message

        when {
            CompilerMessageSeverity.VERBOSE.contains(severity) -> this.logger.finest(text)
            CompilerMessageSeverity.ERRORS.contains(severity) -> this.logger.severe(text)
            CompilerMessageSeverity.INFO == severity -> this.logger.info(text)
            else -> this.logger.warning(text)
        }
    }
}