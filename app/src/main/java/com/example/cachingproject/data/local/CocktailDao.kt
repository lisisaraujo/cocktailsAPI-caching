package com.example.cachingproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cachingproject.data.model.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail_table")
    fun getCocktails() : LiveData<List<Cocktail>>

    @Query("DELETE FROM cocktail_table")
    fun deleteCocktails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktailsList(cocktailsList: List<Cocktail>)
}
