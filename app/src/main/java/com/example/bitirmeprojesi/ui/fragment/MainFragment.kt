package com.example.bitirmeprojesi.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentCartDiscountBinding
import com.example.bitirmeprojesi.databinding.FragmentMainBinding
import com.example.bitirmeprojesi.ui.adapter.MealsAdapter
import com.example.bitirmeprojesi.ui.viewmodel.MainFragmentViewModel
import com.example.bitirmeprojesi.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.bitirmeprojesi.data.entity.SepetYemek
import java.util.Objects


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.anasayfaFragment = this

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(newText ?: "")
                return true
            }
        })

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { filteredYemekList ->
            val adapter = MealsAdapter(requireContext(), filteredYemekList, viewModel)
            binding.mealsadapter = adapter
            binding.recyclerView.adapter = adapter
        }



        return binding.root
    }
}
