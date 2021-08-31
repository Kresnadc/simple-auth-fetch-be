package com.example.simplefetch.model.dto

import io.jsonwebtoken.Claims
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.Column

data class FisheryResponse(
    var uuid: String?,
    var komoditas: String?,
    var areaProvinsi: String?,
    var areaKota: String?,
    var size: String?,
    var price: Double?,
    var priceUsd: Double?,
    var tglParsed: LocalDateTime?,
    var timestamp: Timestamp?,
)