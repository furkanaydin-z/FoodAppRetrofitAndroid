package com.fako.retroo.data.repo

import com.fako.retroo.data.datasource.FoodDataSource
import com.fako.retroo.entity.BasketFood
import com.fako.retroo.entity.Food

class FoodRepository  {
    var fds = FoodDataSource()
    suspend fun bringFoods():List<Food> = fds.bringFoods()
    suspend fun bringBasket(kullanici_adi: String):List<BasketFood> = fds.bringBasket(kullanici_adi)
    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String) = fds.sil(sepet_yemek_id,kullanici_adi)
    suspend fun load(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String)= fds.load(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
}