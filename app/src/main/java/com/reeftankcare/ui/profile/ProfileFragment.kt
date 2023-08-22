package com.reeftankcare.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.reeftankcare.databinding.FragmentProfileBinding

class ProfileFragment :
    Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeTextButton.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.agoToHome())
        }
        binding.photoTextButton.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.goToPhoto())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}