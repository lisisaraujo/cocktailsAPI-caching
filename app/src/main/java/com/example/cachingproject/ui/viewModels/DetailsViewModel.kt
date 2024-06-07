package com.example.cachingproject.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cachingproject.data.AppRepository
import com.example.cachingproject.data.local.getDatabase
import com.example.cachingproject.data.model.Cocktail
import com.example.cachingproject.data.remote.CocktailsApi
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = AppRepository(CocktailsApi, database)
    private val cocktails = database.cocktailDao.getCocktails()


    fun getCocktailDetails(cocktailID: String): LiveData<Cocktail> {
        viewModelScope.launch {
            repository.getCocktailDetails(cocktailID)
        }
        return repository.getCocktailDetails(cocktailID)
    }

    fun loadNextCocktail(): LiveData<Cocktail> {
        val randomCocktail: MutableLiveData<Cocktail> = MutableLiveData()

        viewModelScope.launch {
            randomCocktail.value = repository.getRandomCocktail()
            Log.d("RandomCocktail", randomCocktail.value.toString())
        }
        return randomCocktail
    }

}
