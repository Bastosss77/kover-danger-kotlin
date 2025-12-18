package io.github.bastosss77.danger.kover

import io.github.bastosss77.danger.kover.parser.xml.KoverXmlReportParser
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

object KoverPlugin : DangerPlugin() {
    override val id: String = "koverPlugin"

    private val parsers =
        mapOf(
            "xml" to KoverXmlReportParser()
        )

    fun parse(file: File) {
        val parser = parsedBy(file).parse(file)
    }

    /**
     * Find the correct parser to use based on the file extension provided as Kover report file
     * @param file Kover report file
     */
    private fun parsedBy(file: File) = parsers[file.extension] ?: throw IllegalArgumentException("No parser found for file ${file.path} with extension ${file.extension}")
}