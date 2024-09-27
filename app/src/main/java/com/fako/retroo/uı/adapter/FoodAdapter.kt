package com.fako.retroo.uı.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fako.retroo.R
import com.fako.retroo.databinding.CardViewBinding
import com.fako.retroo.entity.Food
import com.fako.retroo.uı.fragment.MainFragmentDirections

class FoodAdapter(var mContext:Context,var foodList:List<Food>):RecyclerView.Adapter<FoodAdapter.CardDesignKeeper>(){

    inner class CardDesignKeeper(var design:CardViewBinding):RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignKeeper {
        val binding:CardViewBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_view,parent,false)
        return CardDesignKeeper(binding)
    }

    override fun onBindViewHolder(holder: CardDesignKeeper, position: Int) {
        val food = foodList.get(position)
        val f = holder.design
        f.foodEntity = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(250,250).into(f.imageView2)
        f.card.setOnClickListener {
            val transfer = MainFragmentDirections.mainToBasket(food = food)
            Navigation.findNavController(it).navigate(transfer)
        }
    }
    override fun getItemCount(): Int {
        return foodList.size
    }
}