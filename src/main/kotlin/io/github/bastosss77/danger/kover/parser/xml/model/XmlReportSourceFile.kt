package io.github.bastosss77.danger.kover.parser.xml.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

internal data class XmlReportSourceFile(
    @field:JacksonXmlProperty
    val name: String = "",

    @field:JsonProperty("line")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val lines: List<XmlReportLine> = emptyList(),

    @field:JsonProperty("counter")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val counters: List<XmlReportCounter> = emptyList()
)
