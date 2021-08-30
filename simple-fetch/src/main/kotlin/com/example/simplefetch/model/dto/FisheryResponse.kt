package com.example.simplefetch.model.dto

import io.jsonwebtoken.Claims
import javax.persistence.Column

data class FisheryResponse(
    var uuid: String?,
    var komoditas: String?,
    var areaProvinsi: String?,
    var areaKota: String?,
    var size: String?,
    var price: Double?,
    var priceUsd: Double?,
    var tglParsed: String?,
    var timestamp: String?,
)