package io.github.bastosss77.danger.kover.model

data class KoverReport(
    val packages: List<KoverReportPackage>,
    val coverage: KoverCoverageReport
)