package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.entity.FavoriYemekler
import com.example.bitirmeprojesi.data.repository.FavoriYemeklerRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavorilerFragmentViewModel @Inject constructor(var favorirepo: FavoriYemeklerRepository) :
    ViewModel() {

    val favorilistesi = MutableLiveData<List<FavoriYemekler>>()

    init {
        favoriYemekleriYukle()
    }

    fun favoriYemekleriYukle() {
        viewModelScope.launch(Dispatchers.IO) {  // IO thread
            val liste = favorirepo.getAllFavoriYemekler()
            withContext(Dispatchers.Main) {
                // Ana thread'de LiveData güncelle
                favorilistesi.value = liste
            }
        }
    }


    fun favoridenYemekSil(yemek_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favorirepo.favoriSil(yemek_id)  // IO thread'de çalışmalı
            favoriYemekleriYukle()          // Yine IO thread içinde veriyi yenile
        }
    }

}








