package com.reeftankcare.ui.photo_base

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reeftankcare.databinding.FragmentPhotoBaseBinding
import kotlinx.coroutines.launch


class PhotoBaseFragment : Fragment() {

    private var _binding: FragmentPhotoBaseBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    private val photoBaseViewModel: PhotoBaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoBaseBinding.inflate(inflater, container, false)
        binding.photoRvGrid.layoutManager = GridLayoutManager(context, 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            photoBaseViewModel.galleryItem.collect { items ->
                binding.photoRvGrid.adapter = PhotoBaseAdapter(items)
            }
        }
    }

        binding.homeTextButton.setOnClickListener {
            findNavController().navigate(PhotoBaseFragmentDirections.agoToHome())
        }

        binding.profileTextButton.setOnClickListener {
            findNavController().navigate(PhotoBaseFragmentDirections.goToProfile())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
