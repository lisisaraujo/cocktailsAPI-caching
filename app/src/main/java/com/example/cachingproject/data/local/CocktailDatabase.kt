package com.example.cachingproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cachingproject.data.model.Cocktail

@Database(entities = [Cocktail::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract val cocktailDao: CocktailDao
}

private lateinit var INSTANCE: CocktailDatabase

fun getDatabase(context: Context): CocktailDatabase {

    synchronized(CocktailDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            //Datenbank initialisieren und in INSTANCE Variable speichern
            INSTANCE = Room.databaseBuilder(
                context,
                CocktailDatabase::class.java,
                "cocktails_database"
            ).build()
        }
        return INSTANCE
    }
}