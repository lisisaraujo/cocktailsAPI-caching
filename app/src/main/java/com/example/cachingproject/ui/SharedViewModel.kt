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
import org.jetbrains.annotations.ApiStatus


val TAG = "SharedViewModel"

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AppRepository(CocktailsApi, database)

//    private val _loading = MutableLiveData<ApiStatus>()
//    val loading: LiveData<ApiStatus>
//        get() = _loading

val cocktails = repository.cocktails
    init {
        loadData()
        Log.d(TAG, database.toString())
    }

    fun loadData() {
        viewModelScope.launch {
//
            try {
                repository.getCocktails()
//
            } catch (e: Exception){
                Log.d(TAG, "Error Loading Data $e")
            }
        }
    }


}
