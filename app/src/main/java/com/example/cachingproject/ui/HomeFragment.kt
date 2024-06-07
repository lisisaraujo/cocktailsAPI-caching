package com.example.cachingproject.ui

import CocktailAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cachingproject.ListViewModel
import com.example.cachingproject.R
import com.example.cachingproject.data.model.Cocktail
import com.example.cachingproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemClickedCallback: (Cocktail) -> Unit = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id))
        }

        val currentLetter = viewModel.letters[viewModel.letterPosition]
        viewModel.loadData(currentLetter)

        viewModel.cocktails.observe(viewLifecycleOwner) { cocktails ->
            binding.rvCocktailsList.adapter = CocktailAdapter(cocktails, itemClickedCallback)
        }

        binding.nextPageBTN.setOnClickListener {
            Log.d("NextPage", viewModel.cocktails.value.toString())
            viewModel.loadNextPage()
        }

        binding.previousPageBTN.setOnClickListener {
            viewModel.loadPrevPage()
        }
    }
}
