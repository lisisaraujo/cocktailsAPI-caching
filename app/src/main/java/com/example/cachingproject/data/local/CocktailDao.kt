package com.example.cachingproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cachingproject.data.model.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail_table")
    fun getCocktails() : LiveData<List<Cocktail>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCocktail(cocktail : Cocktail)
//
//    @Query("SELECT COUNT(*) FROM cocktail_table")
//    suspend fun getCocktailCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktailsList(cocktailsList: List<Cocktail>)
}
