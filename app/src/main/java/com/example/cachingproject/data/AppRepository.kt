package com.example.cachingproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cachingproject.data.local.CocktailDatabase
import com.example.cachingproject.data.model.Cocktail
import com.example.cachingproject.data.remote.CocktailsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "AppRepository"
class AppRepository(private val api: CocktailsApi, private val database: CocktailDatabase) {


    val cocktails: LiveData<List<Cocktail>> = database.cocktailDao.getCocktails()

    suspend fun getCocktails() {
        Log.d(TAG, cocktails.value.toString())
        try {
            withContext(Dispatchers.IO){
                val newCocktailsList = api.cocktailsApiService.getCocktails().drinks
                database.cocktailDao.insertCocktailsList(newCocktailsList)
                Log.d("CocktailsList", newCocktailsList.toString())
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error loading cocktails list", e)
        }
    }
}

