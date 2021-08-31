package com.example.simplefetch.repository

import com.example.simplefetch.model.dao.AggregateResults
import com.example.simplefetch.model.dao.Fishery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FisheryRepository : JpaRepository<Fishery, String> {

    @Query("SELECT new com.example.simplefetch.model.dao.AggregateResults(areaProvinsi, week(tglParsed), " +
            "max(price), min(price), avg(price)" +
//            ", median(price)" +
            ") " +
            "FROM Fishery AS f " +
            "GROUP BY f.areaProvinsi, week(f.tglParsed)")
    fun findByAggregate(): List<AggregateResults>
}