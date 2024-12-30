package com.dsalgo.countries.data.repository

import com.dsalgo.countries.data.api.CountryApi
import com.dsalgo.countries.data.model.Country


class CountryRepository(private val api: CountryApi) {
    suspend fun getCountries(): List<Country> {
        return api.getCountries()
    }
}