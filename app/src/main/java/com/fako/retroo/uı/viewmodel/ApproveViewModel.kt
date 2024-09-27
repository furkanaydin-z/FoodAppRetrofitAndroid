package com.fako.retroo.uı.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fako.retroo.data.repo.FoodRepository
import com.fako.retroo.entity.BasketFood
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApproveViewModel :ViewModel() {
    var frepo = FoodRepository()
    var basketList = MutableLiveData<List<BasketFood>>()
    init {
        bringBasket(kullanici_adi = "Manuel")
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
         CoroutineScope(Dispatchers.Main).launch {
             try{
             frepo.sil(sepet_yemek_id, kullanici_adi)
                 bringBasket(kullanici_adi)
         } catch (e: Exception) {
            Log.e("ViewModel Silme Hatası", "Hata: ${e.message}")
        }
         }
     }
     fun bringBasket(kullanici_adi: String){
         CoroutineScope(Dispatchers.Main).launch {
             basketList.value = frepo.bringBasket(kullanici_adi)
             Log.e("Güncell Sepet", "Sepet İçeriği: ${basketList.value}")

         }
     }
    fun getTotalPrice(): Int {
        return basketList.value?.sumBy { it.yemek_fiyat } ?: 0
    }
}