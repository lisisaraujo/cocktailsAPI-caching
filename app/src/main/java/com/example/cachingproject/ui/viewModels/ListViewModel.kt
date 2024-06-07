package com.example.cachingproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cachingproject.data.AppRepository
import com.example.cachingproject.data.local.getDatabase
import com.example.cachingproject.data.remote.CocktailsApi
import kotlinx.coroutines.launch


val TAG = "SharedViewModel"

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AppRepository(CocktailsApi, database)

    val letters: List<Char> = ('a'..'z').toList()
    var letterPosition = 0

    val cocktails = repository.cocktails

    init {
        loadData(letters[letterPosition])
    }

    fun loadData(letter: Char) {
        viewModelScope.launch {
            try {
                repository.getCocktails(letter)
            } catch (e: Exception) {
                Log.d(TAG, "Error Loading Data $e")
            }
        }
    }

    fun loadNextPage() {
        if (letterPosition < letters.size - 1) {
            letterPosition++
        } else {
            letterPosition = 0
        }
        val nextLetter = letters[letterPosition]
        loadData(nextLetter)
    }

    fun loadPrevPage() {
        if (letterPosition > 0) {
            letterPosition--
        } else {
            letterPosition = 0
        }
        val nextLetter = letters[letterPosition]
        loadData(nextLetter)
    }
}


