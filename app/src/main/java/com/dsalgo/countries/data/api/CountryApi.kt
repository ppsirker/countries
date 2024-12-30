package com.dsalgo.countries.data.api

import com.dsalgo.countries.data.model.Country
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

const val BASE_URL= "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

interface CountryApi {
    @GET("countries.json")
    suspend fun getCountries():List<Country>

    companion object{
        fun getClient():CountryApi{
            return Retrofit.Builder().client(OkHttpClient()).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create<CountryApi>()
        }
    }




}