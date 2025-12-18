package io.github.bastosss77.danger.kover.parser.xml.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

internal data class XmlReportCounter(
    @field:JacksonXmlProperty
    val type: String = "",

    @field:JacksonXmlProperty
    val missed: String = "",

    @field:JacksonXmlProperty
    val covered: String = "",
)
