package com.reeftankcare.ui.photo_base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.reeftankcare.databinding.FragmentPhotoBaseBinding
import com.reeftankcare.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoBaseFragment : Fragment() {

    private var _binding: FragmentPhotoBaseBinding? = null
    private val binding
    get() = checkNotNull(_binding){}

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
          //  val response = PhotoRepository().fetchPhotos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
