package com.example.simplefetch.repository

import com.example.simplefetch.model.dao.Currency
import com.example.simplefetch.model.dao.Fishery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository : JpaRepository<Currency, Long> {
    fun findOneByBaseAndConvertTo(base: String, convertTo: String): Currency?

}