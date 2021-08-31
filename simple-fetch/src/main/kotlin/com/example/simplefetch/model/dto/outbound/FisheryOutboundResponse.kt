package com.example.simplefetch.model.dto.outbound

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column

@JsonIgnoreProperties(ignoreUnknown = true)
data class FisheryOutboundResponse(
    @JsonProperty("uuid")
    var uuid: String?,
    @JsonProperty("komoditas")
    var komoditas: String?,
    @JsonProperty("area_provinsi")
    var areaProvinsi: String?,
    @JsonProperty("area_kota")
    var areaKota: String?,
    @JsonProperty("size")
    var size: String?,
    @JsonProperty("price")
    var price: Double?,
    @JsonProperty("tgl_parsed")
    var tglParsed: String?,
    @JsonProperty("timestamp")
    var timestamp: Timestamp?,
)