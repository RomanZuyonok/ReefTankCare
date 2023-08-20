package com.reeftankcare.ui.photo_base


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reeftankcare.R
import com.reeftankcare.databinding.FragmentPhotoBaseBinding
import kotlinx.coroutines.launch

class PhotoBaseFragment : Fragment() {

    private var _binding: FragmentPhotoBaseBinding? = null
    private val binding
        get() = checkNotNull(_binding) {}

    private var searchView: SearchView? = null

    private val photoBaseViewModel: PhotoBaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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
                photoBaseViewModel.uiState.collect { state ->
                    binding.photoRvGrid.adapter = PhotoBaseAdapter(state.images)
                    searchView?.setQuery(state.query, false)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_photo_base, menu)

        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        searchView = searchItem.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                photoBaseViewModel.setQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_clear -> {
                photoBaseViewModel.setQuery("")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        searchView = null
    }
}
