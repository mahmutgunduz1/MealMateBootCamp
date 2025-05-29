package com.example.bitirmeprojesi.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitirmeprojesi.data.entity.FavoriYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriYemeklerDao {

    @Query("SELECT * FROM favori_yemekler")
    suspend fun getAllFavoriYemekler(): List<FavoriYemekler>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriYemek(favoriyemek: FavoriYemekler)

    @Query("DELETE FROM favori_yemekler WHERE yemek_id = :yemekId")
    suspend fun deleteFavoriYemek(yemekId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favori_yemekler WHERE yemek_adi = :yemekAdi)")
    suspend fun isFavori(yemekAdi: String): Boolean
}