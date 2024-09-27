package com.fako.retroo.retrofit

import com.fako.retroo.entity.BasketFoodResponse
import com.fako.retroo.entity.CrudCevap
import com.fako.retroo.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {

    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun bringFoods():FoodResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun load(@Field("yemek_adi") yemek_adi:String,
                     @Field("yemek_resim_adi") yemek_resim_adi:String,
                     @Field("yemek_fiyat") yemek_fiyat:Int,
                     @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                     @Field("kullanici_adi") kullanici_adi:String):CrudCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun bringsBasket(@Field("kullanici_adi") kullanici_adi: String):BasketFoodResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                       @Field("kullanici_adi") kullanici_adi: String):CrudCevap
}