package com.example.simplefetch.model.dao

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Fishery")
data class Fishery(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,
    @Column(name = "uuid", nullable = true)
    var uuid: String?,
    @Column(name = "komoditas", nullable = true)
    var komoditas: String?,
    @Column(name = "area_provinsi", nullable = true)
    var areaProvinsi: String?,
    @Column(name = "area_kota", nullable = true)
    var areaKota: String?,
    @Column(name = "size", nullable = true)
    var size: String?,
    @Column(name = "price", nullable = true)
    var price: Double?,
    @Column(name = "tgl_parsed", nullable = true)
    var tglParsed: LocalDateTime?,
    @Column(name = "timestamp", nullable = true)
    var timestamp: Timestamp?,

    @Column(name = "createdDate")
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @Column(name = "modifiedDate")
    var modifiedDate: LocalDateTime = LocalDateTime.now(),
)

