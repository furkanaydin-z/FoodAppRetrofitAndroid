package com.fako.retroo.uı.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fako.retroo.databinding.FragmentApproveBinding
import com.fako.retroo.uı.adapter.ApproveAdapter
import com.fako.retroo.uı.viewmodel.ApproveViewModel

class ApproveFragment : Fragment() {
    private lateinit var binding: FragmentApproveBinding
    private lateinit var viewModel:ApproveViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApproveBinding.inflate(inflater, container, false)

        val backPress = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPress)


        val bundle : ApproveFragmentArgs by navArgs()
        val dataTransfer = listOf(bundle.basketFood)

        viewModel.basketList.observe(viewLifecycleOwner){
            Log.e("Fragment", "ViewModel'den gelen liste boyutu: ${it.size}")
            val approveAdapter = ApproveAdapter(requireContext(),dataTransfer,viewModel)
            binding.adapterApprove = approveAdapter
            approveAdapter.setData(it)
            val totalPrice = viewModel.getTotalPrice()
            binding.textLastPrice.text = totalPrice.toString()
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ApproveViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onResume() {
        super.onResume()
        viewModel.bringBasket(kullanici_adi = "Manuel")
    }
}