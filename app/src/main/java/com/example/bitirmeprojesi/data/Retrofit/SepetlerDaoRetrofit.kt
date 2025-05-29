package com.example.bitirmeprojesi.data.Retrofit

import com.example.bitirmeprojesi.data.entity.CRUDCevap
import com.example.bitirmeprojesi.data.entity.SepetYemeklerCevap
import retrofit2.http.*

interface SepetlerDaoRetrofit {

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriYukle(@Field("kullanici_adi") kullaniciAdi: String): SepetYemeklerCevap


    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDCevap


    @POST("/yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDCevap


}
