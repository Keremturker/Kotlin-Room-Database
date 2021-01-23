package com.keremturker.roomdatabase.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.keremturker.roomdatabase.model.Country

@Dao
interface CountryDao {


    @Insert
    fun insertAll(vararg country: Country): List<Long>


    @Query("SELECT * FROM Country")
    fun getAll(): List<Country>

    @Query("DELETE FROM Country WHERE uuid = :countryId")
    fun deleteCountry(countryId: Int)

    @Update
    fun updateCountry(country: Country)

}