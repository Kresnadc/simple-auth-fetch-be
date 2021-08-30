package com.example.simplefetch.model.dao

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Currency")
data class Currency(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "base")
    var base: String,
    @Column(name = "convertTo")
    var convertTo: String,
    @Column(name = "value")
    var value: Double,

    @Column(name = "modifiedDate")
    var modifiedDate: LocalDateTime = LocalDateTime.now(),
)

