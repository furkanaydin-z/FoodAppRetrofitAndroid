package com.fako.retroo.uı.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fako.retroo.R
import com.fako.retroo.databinding.FragmentMainBinding
import com.fako.retroo.entity.Food
import com.fako.retroo.uı.adapter.FoodAdapter
import com.fako.retroo.uı.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel:MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)


        binding.toolbarMain = "Main Page"

        viewModel.foodsList.observe(viewLifecycleOwner){
            var foodAdapter = FoodAdapter(requireContext(),it)
            binding.fAdapter = foodAdapter
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val tempViewModel:MainViewModel by viewModels()
        viewModel = tempViewModel
    }
}