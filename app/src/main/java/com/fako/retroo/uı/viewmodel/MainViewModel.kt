package com.fako.retroo.uı.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fako.retroo.data.repo.FoodRepository
import com.fako.retroo.entity.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel :ViewModel() {
    var frepo = FoodRepository()
    var foodsList = MutableLiveData<List<Food>>()
    init {
        bringFoods()
    }
     fun bringFoods(){
         CoroutineScope(Dispatchers.Main).launch {
             foodsList.value = frepo.bringFoods()
         }
     }
}