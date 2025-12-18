package io.github.bastosss77.danger.kover.model

data class KoverReportLine(
    val lineNumber: Int,
    val missedInstruction: Int,
    val coveredInstruction: Int,
    val missedBranch: Int,
    val coveredBranch: Int
)