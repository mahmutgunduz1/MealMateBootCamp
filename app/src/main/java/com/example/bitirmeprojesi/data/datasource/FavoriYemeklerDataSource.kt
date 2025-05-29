package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.Room.FavoriYemeklerDao
import com.example.bitirmeprojesi.data.entity.FavoriYemekler

class FavoriYemeklerDataSource(private val dao: FavoriYemeklerDao) {

    suspend fun getAllFavoriYemekler(): List<FavoriYemekler> = dao.getAllFavoriYemekler()

    suspend fun insertFavoriYemek(favoriYemek: FavoriYemekler) = dao.insertFavoriYemek(favoriYemek)

    suspend fun deleteFavoriYemek(favoriId: Int) = dao.deleteFavoriYemek(favoriId)

    suspend fun isFavori(yemekAdi: String) = dao.isFavori(yemekAdi)
}
