package io.github.bastosss77.danger.kover.model

data class KoverReportClass(
    val name: String,
    val methods: List<KoverReportMethod>,
    val coverageReport: KoverCoverageReport
)
