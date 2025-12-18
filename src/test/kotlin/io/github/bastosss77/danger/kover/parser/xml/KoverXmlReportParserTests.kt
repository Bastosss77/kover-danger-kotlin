package io.github.bastosss77.danger.kover.parser.xml

import io.github.bastosss77.danger.kover.model.*
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class KoverXmlReportParserTests {
    private val parser = KoverXmlReportParser()

    internal fun loadFile(fileName: String): File = File(ClassLoader.getSystemResource(fileName).toURI())

    @Test
    fun `test parse xml report`() {
        val file = loadFile("kover/report.xml")
        val expectedReport = KoverReport(
            packages = listOf(
                KoverReportPackage(
                    name = "io/github/bastosss77/danger/kover/parser/xml",
                    sourceFiles = listOf(
                        KoverReportSourceFile(
                            name = "KoverXmlReportParser.kt",
                            coverage = KoverCoverageReport(
                                instruction = KoverCoverage(0, 16),
                                branch = KoverCoverage.empty(),
                                line = KoverCoverage(0, 2),
                                method = KoverCoverage.empty(),
                                clazz = KoverCoverage.empty()
                            )
                        )
                    ),
                    coverage = KoverCoverageReport(
                        instruction = KoverCoverage(0, 16),
                        branch = KoverCoverage.empty(),
                        line = KoverCoverage(0, 2),
                        method = KoverCoverage(0, 2),
                        clazz = KoverCoverage(0, 1)
                    )
                )
            ),
            coverage = KoverCoverageReport(
                instruction = KoverCoverage(0, 65),
                branch = KoverCoverage(0, 2),
                line = KoverCoverage(0, 13),
                method = KoverCoverage(0, 10),
                clazz = KoverCoverage(0, 5)
            )
        )

        val result = parser.parse(file)

        assertEquals(expectedReport, result)
    }
}