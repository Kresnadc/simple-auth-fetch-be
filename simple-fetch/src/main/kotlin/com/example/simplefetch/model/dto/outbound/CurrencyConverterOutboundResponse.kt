package com.example.simplefetch.model.dto.outbound

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.Column

@JsonIgnoreProperties(ignoreUnknown = true)
data class CurrencyConverterOutboundResponse(
    @JsonProperty("uuid")
    var success: String?,
    @JsonProperty("rates")
    var rates: RatesOutboundResponse?,
    @JsonProperty("base")
    var base: String?,
    @JsonProperty("date")
    var date: String?,
    @JsonProperty("timestamp")
    var timestamp: String?,
)