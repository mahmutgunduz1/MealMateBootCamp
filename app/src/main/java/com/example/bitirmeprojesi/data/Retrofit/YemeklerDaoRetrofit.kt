package com.example.bitirmeprojesi.data.Retrofit

import com.example.bitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.http.GET

interface YemeklerDaoRetrofit {

    @GET("tumYemekleriGetir.php")
    suspend fun tumYemekleriYukle(): YemeklerCevap

}