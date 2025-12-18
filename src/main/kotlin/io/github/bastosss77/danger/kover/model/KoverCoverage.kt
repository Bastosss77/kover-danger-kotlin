package io.github.bastosss77.danger.kover.model

data class KoverCoverageReport(
    val instruction: KoverCoverage,
    val branch: KoverCoverage,
    val line: KoverCoverage,
    val method: KoverCoverage?,
    val clazz: KoverCoverage?
)

data class KoverCoverage(
    val covered: Int,
    val missed: Int
) {
    companion object {
        fun empty() = KoverCoverage(0, 0)
    }
}
