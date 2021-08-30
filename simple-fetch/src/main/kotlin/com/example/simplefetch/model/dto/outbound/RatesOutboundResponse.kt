package com.example.simplefetch.model.dto.outbound

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.Column

@JsonIgnoreProperties(ignoreUnknown = true)
data class RatesOutboundResponse(
    @JsonProperty("USD")
    var usd: Double?,
    @JsonProperty("IDR")
    var idr: Double?,

)