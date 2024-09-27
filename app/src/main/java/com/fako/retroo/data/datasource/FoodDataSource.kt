package com.fako.retroo.data.datasource

import android.util.Log
import androidx.navigation.Navigation
import com.fako.retroo.entity.BasketFood
import com.fako.retroo.entity.Food
import com.fako.retroo.retrofit.ApiUtils
import com.fako.retroo.retrofit.FoodDao
import com.fako.retroo.retrofit.RetrofitClient
import com.fako.retroo.uı.fragment.BasketFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource {
        var foodDao:FoodDao = ApiUtils.getFoodDao()
    suspend fun bringFoods():List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.bringFoods().yemekler
    }
    suspend fun bringBasket(kullanici_adi: String):List<BasketFood> = withContext(Dispatchers.IO){

        return@withContext foodDao.bringsBasket(kullanici_adi).sepet_yemekler
    }
    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        try {
            val response = foodDao.delete(sepet_yemek_id, kullanici_adi)
            Log.e("Silme Durumu", "Cevap: ${response.message}, Başarı: ${response.success}")
            if (response.success == 1) {
                Log.e("Silme", "Başarılı: $sepet_yemek_id, $kullanici_adi")
            } else {
                Log.e("Silme", "Başarısız: ${response.message}")
            }
        } catch (e: Exception) {
            Log.e("Silme Hatası", "Hata: ${e.message}")
        }
    }
   suspend fun load(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        foodDao.load(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    }

}