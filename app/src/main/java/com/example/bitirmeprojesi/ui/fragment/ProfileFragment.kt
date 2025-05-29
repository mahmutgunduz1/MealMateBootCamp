package com.example.bitirmeprojesi.ui.fragment

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


import androidx.appcompat.app.AppCompatDelegate
import com.example.bitirmeprojesi.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.buttonChangeAvatar.setOnClickListener {
            val linkedInUrl = "https://www.linkedin.com/in/mahmutgunduz/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
            intent.setPackage("com.linkedin.android")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl)))
            }
        }

        binding.buttonLogout.setOnClickListener {
            requireActivity().finishAffinity()
        }

        return binding.root
    }
}

