package com.example.simpleauth.repository

import com.example.simpleauth.model.dao.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findOneByName(name: String): User?
    fun findOneByPhoneAndPassword(phone: String, password: String): User
}