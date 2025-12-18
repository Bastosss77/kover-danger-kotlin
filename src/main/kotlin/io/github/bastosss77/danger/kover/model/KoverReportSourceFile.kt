package io.github.bastosss77.danger.kover.model

data class KoverReportSourceFile(
    val name: String,
    val classes: List<KoverReportClass>,
    val lines: List<KoverReportLine>,
    val coverage: KoverCoverageReport,
)
