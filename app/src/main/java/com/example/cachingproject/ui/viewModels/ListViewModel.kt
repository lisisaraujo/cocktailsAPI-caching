package com.example.cachingproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cachingproject.data.AppRepository
import com.example.cachingproject.data.local.getDatabase
import com.example.cachingproject.data.remote.CocktailsApi
import kotlinx.coroutines.launch


val TAG = "ListViewModel"

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AppRepository(CocktailsApi, database)

    val letters: List<Char> = ('a'..'z').toList()

    val cocktails = repository.cocktails

    private val _letterPosition = MutableLiveData(0)
    val letterPosition: LiveData<Int> = _letterPosition

    init {
        loadData(letters[letterPosition.value!!])
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
        if (_letterPosition.value!! < letters.size - 1) {
            _letterPosition.value = _letterPosition.value!! + 1
        } else {
            _letterPosition.value = 0
        }
        val nextLetter = letters[_letterPosition.value!!]
        loadData(nextLetter)
    }

    fun loadPrevPage() {
        if (_letterPosition.value!! > 0) {
            _letterPosition.value = _letterPosition.value!! - 1
        } else {
            _letterPosition.value = 0
        }
        val nextLetter = letters[_letterPosition.value!!]
        loadData(nextLetter)
    }
}


