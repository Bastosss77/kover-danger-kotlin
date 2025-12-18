package io.github.bastosss77.danger.kover.parser.xml.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

internal data class XmlReportLine(

    //Line number
    @field:JacksonXmlProperty
    val nr: String = "",

    //Missed instructions
    @field:JacksonXmlProperty
    val mi: String = "",

    //Covered instructions
    @field:JacksonXmlProperty
    val ci: String = "",

    //Missed branches
    @field:JacksonXmlProperty
    val mb: String = "",

    //Covered branches
    @field:JacksonXmlProperty
    val cb: String = "",
)
