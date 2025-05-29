package com.example.bitirmeprojesi.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitirmeprojesi.data.entity.FavoriYemekler


@Database(entities = [FavoriYemekler::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriYemeklerDao(): FavoriYemeklerDao
}