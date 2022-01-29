package com.brand.projectb.dataservice

import com.brand.projectb.model.CocktailModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailService {

    @GET("/api/json/v1/1/lookup.php?i={id}")
    suspend fun fetchCocktail(
        @Path("id") cocktailId: String
    ): CocktailModel

    @GET("/api/json/v1/1/search.php?s={name}")
    suspend fun fetchCocktailList(
        @Path("name") cocktailId: String
    ): List<CocktailModel>

}