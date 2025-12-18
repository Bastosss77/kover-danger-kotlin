package io.github.bastosss77.danger.kover.parser.xml

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.bastosss77.danger.kover.model.*
import io.github.bastosss77.danger.kover.parser.KoverReportParser
import io.github.bastosss77.danger.kover.parser.xml.model.XmlReport
import io.github.bastosss77.danger.kover.parser.xml.model.XmlReportClass
import io.github.bastosss77.danger.kover.parser.xml.model.XmlReportPackage
import io.github.bastosss77.danger.kover.parser.xml.model.XmlReportSourceFile
import java.io.File

internal class KoverXmlReportParser : KoverReportParser {
    private val xmlParser = XmlMapper()

    override fun parse(file: File): KoverReport {
        val rawReport = xmlParser.readValue<XmlReport>(file)

        return mapToReport(rawReport)
    }

    private fun mapToReport(xmlReport: XmlReport) : KoverReport {
        val packages = xmlReport.packages.map { xmlPackage ->
            val sourceFileAndClasses = mutableMapOf<XmlReportSourceFile, MutableList<XmlReportClass>>()

            xmlPackage.sourceFiles.forEach { sourceFile ->
                val classesForSourceFile = xmlPackage.classes.filter { xmlClass ->
                    xmlClass.sourceFileName == sourceFile.name
                }

                if(sourceFileAndClasses.containsKey(sourceFile)) {
                    sourceFileAndClasses[sourceFile]?.addAll(classesForSourceFile)
                } else {
                    sourceFileAndClasses[sourceFile] = classesForSourceFile.toMutableList()
                }
            }

            val reportSourceFile = sourceFileAndClasses.map { (sourceFile, classes) ->
                KoverReportSourceFile(
                    name = sourceFile.name,
                    classes = classes.map { clazz ->
                        KoverReportClass(
                            name = clazz.name,
                            methods = clazz.methods.map { method ->
                                KoverReportMethod(
                                    name = method.name,
                                    description = method.desc,
                                    coverage = KoverCoverageReport(
                                        instruction = method.counters.firstOrNull { it.type == "INSTRUCTION" }?.let { coverage ->
                                            KoverCoverage(
                                                covered = coverage.covered.toInt(),
                                                missed = coverage.missed.toInt()
                                            )
                                        } ?: KoverCoverage.empty(),
                                        branch = method.counters.firstOrNull { it.type == "BRANCH" }?.let { coverage ->
                                            KoverCoverage(
                                                covered = coverage.covered.toInt(),
                                                missed = coverage.missed.toInt()
                                            )
                                        } ?: KoverCoverage.empty(),
                                        line = method.counters.firstOrNull { it.type == "LINE" }?.let { coverage ->
                                            KoverCoverage(
                                                covered = coverage.covered.toInt(),
                                                missed = coverage.missed.toInt()
                                            )
                                        } ?: KoverCoverage(0, 0),
                                        method = null,
                                        clazz = null
                                    )
                                )
                            },
                            coverageReport = KoverCoverageReport(
                                instruction = clazz.counters.firstOrNull { it.type == "INSTRUCTION" }?.let { coverage ->
                                    KoverCoverage(
                                        covered = coverage.covered.toInt(),
                                        missed = coverage.missed.toInt()
                                    )
                                } ?: KoverCoverage.empty(),
                                branch = clazz.counters.firstOrNull { it.type == "BRANCH" }?.let { coverage ->
                                    KoverCoverage(
                                        covered = coverage.covered.toInt(),
                                        missed = coverage.missed.toInt()
                                    )
                                } ?: KoverCoverage.empty(),
                                line = clazz.counters.firstOrNull { it.type == "LINE" }?.let { coverage ->
                                    KoverCoverage(
                                        covered = coverage.covered.toInt(),
                                        missed = coverage.missed.toInt()
                                    )
                                } ?: KoverCoverage.empty(),
                                method = clazz.counters.firstOrNull { it.type == "METHOD" }?.let { coverage ->
                                    KoverCoverage(
                                        covered = coverage.covered.toInt(),
                                        missed = coverage.missed.toInt()
                                    )
                                } ?: KoverCoverage.empty(),
                                clazz = null
                            )
                        )
                    },
                    lines = sourceFile.lines.map { line ->
                        KoverReportLine(
                            lineNumber = line.nr.toInt(),
                            missedInstruction = line.mi.toInt(),
                            coveredInstruction = line.ci.toInt(),
                            missedBranch = line.mb.toInt(),
                            coveredBranch = line.cb.toInt()
                        )
                    },
                    coverage = KoverCoverageReport(
                        instruction = sourceFile.counters.firstOrNull { it.type == "INSTRUCTION" }?.let { coverage ->
                            KoverCoverage(
                                covered = coverage.covered.toInt(),
                                missed = coverage.missed.toInt()
                            )
                        } ?: KoverCoverage.empty(),
                        branch = sourceFile.counters.firstOrNull { it.type == "BRANCH" }?.let { coverage ->
                            KoverCoverage(
                                covered = coverage.covered.toInt(),
                                missed = coverage.missed.toInt()
                            )
                        } ?: KoverCoverage.empty(),
                        line = sourceFile.counters.firstOrNull { it.type == "LINE" }?.let { coverage ->
                            KoverCoverage(
                                covered = coverage.covered.toInt(),
                                missed = coverage.missed.toInt()
                            )
                        } ?: KoverCoverage.empty(),
                        method = null,
                        clazz = null
                    )
                )
            }

            KoverReportPackage(
                name = xmlPackage.name,
                sourceFiles = reportSourceFile,
                coverage = KoverCoverageReport(
                    instruction = xmlPackage.counters.firstOrNull { it.type == "INSTRUCTION" }?.let { coverage ->
                        KoverCoverage(
                            covered = coverage.covered.toInt(),
                            missed = coverage.missed.toInt()
                        )
                    } ?: KoverCoverage.empty(),
                    branch = xmlPackage.counters.firstOrNull { it.type == "BRANCH" }?.let { coverage ->
                        KoverCoverage(
                            covered = coverage.covered.toInt(),
                            missed = coverage.missed.toInt()
                        )
                    } ?: KoverCoverage.empty(),
                    line = xmlPackage.counters.firstOrNull { it.type == "LINE" }?.let { coverage ->
                        KoverCoverage(
                            covered = coverage.covered.toInt(),
                            missed = coverage.missed.toInt()
                        )
                    } ?: KoverCoverage.empty(),
                    method = xmlPackage.counters.firstOrNull { it.type == "METHOD" }?.let { coverage ->
                        KoverCoverage(
                            covered = coverage.covered.toInt(),
                            missed = coverage.missed.toInt()
                        )
                    } ?: KoverCoverage.empty(),
                    clazz = xmlPackage.counters.firstOrNull { it.type == "CLASS" }?.let { coverage ->
                        KoverCoverage(
                            covered = coverage.covered.toInt(),
                            missed = coverage.missed.toInt()
                        )
                    } ?: KoverCoverage.empty()
                )
            )
        }

        return KoverReport(
            packages = packages,
            coverage = KoverCoverageReport(
                instruction = xmlReport.counters.firstOrNull { it.type == "INSTRUCTION" }?.let { coverage ->
                    KoverCoverage(
                        covered = coverage.covered.toInt(),
                        missed = coverage.missed.toInt()
                    )
                } ?: KoverCoverage.empty(),
                branch = xmlReport.counters.firstOrNull { it.type == "BRANCH" }?.let { coverage ->
                    KoverCoverage(
                        covered = coverage.covered.toInt(),
                        missed = coverage.missed.toInt()
                    )
                } ?: KoverCoverage.empty(),
                line = xmlReport.counters.firstOrNull { it.type == "LINE" }?.let { coverage ->
                    KoverCoverage(
                        covered = coverage.covered.toInt(),
                        missed = coverage.missed.toInt()
                    )
                } ?: KoverCoverage.empty(),
                method = xmlReport.counters.firstOrNull { it.type == "METHOD" }?.let { coverage ->
                    KoverCoverage(
                        covered = coverage.covered.toInt(),
                        missed = coverage.missed.toInt()
                    )
                } ?: KoverCoverage.empty(),
                clazz = xmlReport.counters.firstOrNull { it.type == "CLASS" }?.let { coverage ->
                    KoverCoverage(
                        covered = coverage.covered.toInt(),
                        missed = coverage.missed.toInt()
                    )
                } ?: KoverCoverage.empty()
            )
        )
    }
}

