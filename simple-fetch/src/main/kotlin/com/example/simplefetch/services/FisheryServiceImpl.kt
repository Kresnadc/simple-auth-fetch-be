package com.example.simplefetch.services

import com.example.simplefetch.configuration.UserProp
import com.example.simplefetch.model.dao.Currency
import com.example.simplefetch.model.dao.Fishery
import com.example.simplefetch.model.dto.FisheryResponse
import com.example.simplefetch.model.dto.outbound.CurrencyConverterOutboundResponse
import com.example.simplefetch.model.dto.outbound.FisheryOutboundResponse
import com.example.simplefetch.repository.CurrencyRepository
import com.example.simplefetch.repository.FisheryRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


@Service
class FisheryServiceImpl(
    private val fisheryRepository: FisheryRepository,
    private val currencyRepository: CurrencyRepository,
    private val userProp: UserProp,
    private val logger: KLogger = KotlinLogging.logger {}
) : FisheryService {

    override fun fetchOutboundData(): String {
        val uri = "https://stein.efishery.com/v1/storages/5e1edf521073e315924ceab4/list"

        var restTemplate = RestTemplate()
        var responseEntity: ResponseEntity<List<FisheryOutboundResponse?>?> =
            restTemplate.exchange(
                uri, HttpMethod.GET, null,
                object : ParameterizedTypeReference<List<FisheryOutboundResponse?>?>() {}
            )

        if (responseEntity.statusCode.equals(HttpStatus.OK)) {
            for (fisheryRes in responseEntity.body!!) {

                val fishery = Fishery(
                    null,
                    fisheryRes!!.uuid,
                    fisheryRes.komoditas,
                    fisheryRes.areaProvinsi,
                    fisheryRes.areaKota,
                    fisheryRes.size,
                    fisheryRes.price,
                    fisheryRes.tglParsed,
                    fisheryRes.timestamp,
                )

                println(fishery)
                fisheryRepository.save(fishery)
            }
        }

        return responseEntity.statusCode.name
    }

    override fun fetchData(): List<FisheryResponse> {
        val fisheries = fisheryRepository.findAll()
        var res = mutableListOf<FisheryResponse>()

        val rate = getPriceUsd()

        logger.info("Price IDR to USD : $rate")

        for (fishery in fisheries) {
            var priceUsd : Double? = null

            if (fishery.price != null) {
                priceUsd = rate * fishery.price!!
            }

            val fisheryResponse = FisheryResponse(
                fishery.uuid,
                fishery.komoditas,
                fishery.areaProvinsi,
                fishery.areaKota,
                fishery.size,
                fishery.price,
                priceUsd,
                fishery.tglParsed,
                fishery.timestamp,
            )

            res.add(fisheryResponse)
        }

        return res
    }

    private fun getPriceUsd(): Double {
        var currencyExisting =
            currencyRepository.findOneByBaseAndConvertTo("USD", "IDR")

        if (currencyExisting == null || isCurrencyExpired(currencyExisting)) {
            logger.info("### Fetch currency rate data online ###")

            // https://fixer.io/quickstart
            // kresna_dcahyo@yahoo.co.id

            val uri =
                "http://data.fixer.io/api/latest?access_key=3bb2fde0fb69222982de89dc45c30030&symbols=USD,IDR"

            var restTemplate = RestTemplate()
            var responseEntity: ResponseEntity<CurrencyConverterOutboundResponse?> =
                restTemplate.exchange(
                    uri, HttpMethod.GET, null,
                    object :
                        ParameterizedTypeReference<CurrencyConverterOutboundResponse?>() {}
                )

            println(responseEntity)

            if (responseEntity.statusCode.equals(HttpStatus.OK)) {
                val rates = responseEntity.body!!.rates!!
                val value = rates.usd!! / rates.idr!!

                currencyExisting = Currency(
                    null,
                    "USD",
                    "IDR",
                    value,
                    LocalDateTime.now()
                )

                currencyRepository.save(currencyExisting)
                return value
            }

            return 0.0
        }

        return currencyExisting.value
    }

    private fun isCurrencyExpired(currencyExisting: Currency): Boolean {
        return ChronoUnit.DAYS.between(currencyExisting.modifiedDate, LocalDateTime.now()) > 1
    }
}