package com.example.cachingproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.cachingproject.databinding.FragmentDetailsBinding
import com.example.cachingproject.ui.viewModels.DetailsViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cocktailID = args.cocktailID


       val cocktail = viewModel.getCocktailDetails(cocktailID).observe(viewLifecycleOwner){cocktail ->

           binding.detailImageView.load(cocktail?.image)
           binding.detailNameTextView.text = cocktail?.name
           binding.detailGlassTV.text = cocktail?.glass
           binding.detailDescriptionTV.text = cocktail?.instructions
           binding.detailCategoryTextView.text = cocktail?.category
           binding.detailAlcoholicTextView.text = cocktail.alcoholic
           binding.nextPageBTN.setOnClickListener {
               viewModel.loadNextCocktail()
           }
       }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }


    }
}
