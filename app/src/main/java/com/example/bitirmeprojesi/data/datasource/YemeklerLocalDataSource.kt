package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.data.Retrofit.YemeklerDaoRetrofit
import com.example.bitirmeprojesi.data.entity.Yemekler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerLocalDataSource(private val yemeklerDao: YemeklerDaoRetrofit) {

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.tumYemekleriYukle().yemekler
    }

}