package io.github.bastosss77.danger.kover.parser.xml.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

internal data class XmlReportClass(

    @field:JacksonXmlProperty
    val name: String = "",

    @field:JacksonXmlProperty(localName = "sourcefilename")
    val sourceFileName: String = "",

    @field:JsonProperty("method")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val methods: List<XmlReportMethod> = emptyList(),

    @field:JsonProperty("counter")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val counters: List<XmlReportCounter> = emptyList()
)
