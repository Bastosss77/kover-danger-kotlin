package io.github.bastosss77.danger.kover.parser.xml.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(namespace = "report")
internal data class XmlReport(

    @field:JacksonXmlProperty
    val name: String = "",

    @field:JsonProperty("package")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val packages: List<XmlReportPackage> = emptyList(),

    @field:JsonProperty("counter")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val counters: List<XmlReportCounter> = emptyList()
)