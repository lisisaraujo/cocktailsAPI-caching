package com.example.cachingproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cocktail_table")
data class Cocktail(

    @PrimaryKey
    @Json(name = "idDrink")
    val id: String,
    @Json(name = "strDrink")
    val name: String?,
    @Json(name = "strDrinkThumb")
    val image: String?,
    @Json(name = "strCategory")
    val category: String,
    @Json(name = "strAlcoholic")
    val alcoholic: String?,
    @Json(name = "strGlass")
    val glass: String?,
    @Json(name = "strInstructions")
    val instructions:  String?,

)