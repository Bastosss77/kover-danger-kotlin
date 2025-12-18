package io.github.bastosss77.danger.kover.model

data class KoverReportPackage(
    val name: String,
    val sourceFiles: List<KoverReportSourceFile>,
    val coverage: KoverCoverageReport
)
