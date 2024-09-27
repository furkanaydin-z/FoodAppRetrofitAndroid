package com.fako.retroo.uı.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fako.retroo.R
import com.fako.retroo.databinding.CardApproveBinding
import com.fako.retroo.entity.BasketFood
import com.fako.retroo.uı.fragment.ApproveFragmentArgs
import com.fako.retroo.uı.viewmodel.ApproveViewModel

class ApproveAdapter(var aContext: Context,var appList: List<BasketFood>,var viewModel: ApproveViewModel)
    :RecyclerView.Adapter<ApproveAdapter.CardDesignKeeper>() {
    fun setData(newList: List<BasketFood>) {
        this.appList = newList
        notifyDataSetChanged()
    }

    inner class CardDesignKeeper(var design:CardApproveBinding):RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignKeeper {
        val binding:CardApproveBinding = DataBindingUtil.inflate(LayoutInflater.from(aContext),R.layout.card_approve,parent,false)
        return CardDesignKeeper(binding)
    }

    override fun onBindViewHolder(holder: CardDesignKeeper, position: Int) {
        val list = appList.get(position)
        val a = holder.design
        a.basketFoodEntity = list

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${list.yemek_resim_adi}"
        Glide.with(aContext).load(url).override(300,300).into(a.imageCardApprove)

        a.delete.setOnClickListener {
            viewModel.sil(list.sepet_yemek_id,list.kullanici_adi)
        }

    }
    override fun getItemCount(): Int {
        Log.e("Adapter", "Eleman Sayısı: ${appList.size}")
        return appList.size
    }
}