package com.fako.retroo.uı.fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fako.retroo.R
import com.fako.retroo.databinding.FragmentBasketBinding
import com.fako.retroo.entity.BasketFood
import com.fako.retroo.uı.viewmodel.BasketViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.Locale

class BasketFragment : Fragment(),OnMapReadyCallback {
        private lateinit var binding: FragmentBasketBinding
        private lateinit var viewModel:BasketViewModel
        private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket,container,false)

        val backPress = object : OnBackPressedCallback(true) { // true ile geri tuşunu etkinleştiriyoruz
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPress)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val bundle:BasketFragmentArgs by navArgs()
        val transfer = bundle.food
        binding.basketEnti = transfer

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${transfer.yemek_resim_adi}"
        Glide.with(this).load(url).override(450,550).into(binding.imageViewBasket)

        binding.picker.minValue = 1
        binding.picker.maxValue = 8

        binding.picker.setOnValueChangedListener { picker, oldVal, newVal ->

            val yeniFiyat = transfer.yemek_fiyat * newVal
            binding.textPrice.text = yeniFiyat.toString()
        }
        binding.buttonBasket.setOnClickListener {
            val yemek_siparis_adet = binding.picker.value
            val x = transfer.yemek_fiyat
            val yemek_fiyat = binding.picker.value * x
            viewModel.load(transfer.yemek_adi,transfer.yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,"Manuel")
            val toApprove = BasketFood(transfer.yemek_id,transfer.yemek_adi,transfer.yemek_resim_adi,yemek_fiyat, yemek_siparis_adet ,"Manuel")
            val dataT = BasketFragmentDirections.basketToApprove(basketFood = toApprove)
            Navigation.findNavController(requireView()).navigate(dataT)

        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : BasketViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onMapReady(p0: GoogleMap) {
       googleMap = p0
        val location = LatLng(39.9334, 32.8597) // Ankara örneği
        googleMap.addMarker(MarkerOptions().position(location).title("Ankara"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMapClickListener { latLng ->
            getAddressFromLatLng(latLng)
        }

    }
    private fun getAddressFromLatLng(latLng: LatLng) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address>? =
            geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0].getAddressLine(0) // İlk adres satırını al
            binding.editTextText.setText(address)
        }
    }
}