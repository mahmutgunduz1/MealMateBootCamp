package com.example.bitirmeprojesi.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.FavoriYemekler
import com.example.bitirmeprojesi.data.entity.SepetYemek
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repository.FavoriYemeklerRepository
import com.example.bitirmeprojesi.data.repository.SepetRepository
import com.example.bitirmeprojesi.data.repository.YemekRetroRepository
import com.example.bitirmeprojesi.utils.gecisYap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    var yemekrepo: YemekRetroRepository,
    var sepetrepo: SepetRepository,
    var favoriYemeklerRepository: FavoriYemeklerRepository


) : ViewModel() {
    private val yemeklerFullList = ArrayList<Yemekler>()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        tumyemekleriGetir()
    }

    fun tumyemekleriGetir() {
        viewModelScope.launch {
            val yemekler = yemekrepo.tumYemekleriGetir()
            yemeklerFullList.clear()
            yemeklerFullList.addAll(yemekler)
            yemeklerListesi.value = yemeklerFullList
        }
    }

    fun favoriyemekEkle(favoriyemek: FavoriYemekler) {

        viewModelScope.launch {
            favoriYemeklerRepository.favoriEkle(favoriyemek)
        }


    }

    suspend fun kartkontrol(yemek_adi: String): SepetYemek? {
        val cartItems = sepetrepo.sepettekiYemekleriGetir() ?: emptyList()
        return cartItems.find { it.yemek_adi == yemek_adi }
    }

    fun sepeteYemekEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int) {
        viewModelScope.launch {
            val mevcutUrun = kartkontrol(yemek_adi)

            if (mevcutUrun != null) {
                sepetrepo.sepetYemekSil(mevcutUrun.sepet_yemek_id)
                sepetrepo.sepeteYemekEkle(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    mevcutUrun.yemek_siparis_adet + 1
                )
            } else {
                sepetrepo.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, 1)
            }
        }
    }

    fun filter(text: String) {
        if (text.isEmpty()) {
            yemeklerListesi.value = yemeklerFullList
            return
        }
        val filteredList = yemeklerFullList.filter {
            it.yemek_adi.lowercase().contains(text.lowercase())
        }
        yemeklerListesi.value = filteredList
    }
}










