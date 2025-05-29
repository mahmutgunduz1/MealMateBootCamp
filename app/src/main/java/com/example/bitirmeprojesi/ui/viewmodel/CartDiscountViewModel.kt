package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.entity.SepetYemek
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repository.SepetRepository
import com.example.bitirmeprojesi.ui.fragment.DetailFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartDiscountViewModel @Inject constructor(
    private val sepetrepo: SepetRepository
) : ViewModel() {

    // ViewModel içinde


    // ViewModel içinde

    val sepetListesi = MutableLiveData<List<SepetYemek>>()


    init {

        sepettekiYemekleriGetir()
    }

    fun sepettekiYemekleriGetir() {
        viewModelScope.launch {
            try {
                val yemekler = sepetrepo.sepettekiYemekleriGetir()
                Log.d("CartDiscountViewModel", "Yemekler repo'dan: $yemekler")
                sepetListesi.postValue(yemekler)
            } catch (e: Exception) {
                Log.e("CartDiscountViewModel", "Sepet yemekleri getirilemedi", e)
                sepetListesi.postValue(emptyList())
            }
        }
    }


    fun sepettenYemekSil(sepet_yemek_id: Int) {
        viewModelScope.launch {
            sepetrepo.sepetYemekSil(sepet_yemek_id)
            sepettekiYemekleriGetir()
        }

    }

    // Sepete yemek ekleme işlemi
    fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int
    ) {
        viewModelScope.launch {
            try {
                sepetrepo.sepeteYemekEkle(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet
                )
                sepettekiYemekleriGetir() // Sepeti güncellemek için tekrar çağır
                Log.d(
                    "CartDiscountViewModel",
                    "Sepete ekleme başarılı: $yemek_adi x$yemek_siparis_adet"
                )
            } catch (e: Exception) {
                Log.e("CartDiscountViewModel", "Sepete ekleme başarısız", e)
            }
        }
    }

    fun toplamparaHesapla(): Int {

        return sepetListesi.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet } ?: 0
    }


}






















