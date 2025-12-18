package io.github.bastosss77.danger.kover.model

data class KoverReportMethod(
    val name: String,
    val description: String,
    val coverage: KoverCoverageReport
)

