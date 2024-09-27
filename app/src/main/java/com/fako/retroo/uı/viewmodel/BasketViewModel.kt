package com.fako.retroo.uı.viewmodel

import androidx.lifecycle.ViewModel
import com.fako.retroo.data.repo.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel :ViewModel() {
    var frepo = FoodRepository()

    fun load(yemek_adi:String,yemek_resim_adi:String
             ,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.load(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }
}