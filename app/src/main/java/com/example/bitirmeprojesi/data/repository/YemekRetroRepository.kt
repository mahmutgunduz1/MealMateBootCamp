package com.example.bitirmeprojesi.data.repository


import com.example.bitirmeprojesi.data.datasource.YemeklerLocalDataSource
import com.example.bitirmeprojesi.data.entity.Yemekler


class YemekRetroRepository(var yds: YemeklerLocalDataSource) {

    suspend fun tumYemekleriGetir(): List<Yemekler> = yds.yemekleriYukle()


}
