package com.example.cachingproject.data.remote

import com.example.cachingproject.data.model.CocktailsList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CocktailsApiService {
    @GET("search.php")
    suspend fun getCocktails(
        @Query("f") letter: Char = 'c',
    ): CocktailsList

}

object CocktailsApi {
    val cocktailsApiService: CocktailsApiService by lazy { retrofit.create(CocktailsApiService::class.java) }
}

