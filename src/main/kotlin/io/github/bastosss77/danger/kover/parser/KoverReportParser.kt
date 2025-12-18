package io.github.bastosss77.danger.kover.parser

import io.github.bastosss77.danger.kover.model.KoverReport
import java.io.File

/**
 * Interface to create a file parser for Kover report
 */
internal interface KoverReportParser {
    /**
     * Implement this function to parse a Kover report file
     */
    fun parse(file: File): KoverReport
}