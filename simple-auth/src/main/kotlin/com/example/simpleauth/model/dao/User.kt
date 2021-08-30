package com.example.simpleauth.model.dao

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "User")
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "createdDate")
    var createdDate: LocalDateTime,
    @Column(name = "modifiedDate")
    var modifiedDate: LocalDateTime,
    @Column(name = "name", unique = true)
    var name: String,
    @Column(name = "phone")
    var phone: String,
    @Column(name = "role")
    var role: String,
    @Column(name = "password")
    var password: String,
)

