package com.example.simplefetch.repository

import com.example.simplefetch.model.dao.Fishery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FisheryRepository : JpaRepository<Fishery, String> {
//    fun findOneByName(name: String): Fishery?
//    fun findOneByPhoneAndPassword(phone: String, password: String): Fishery?
}