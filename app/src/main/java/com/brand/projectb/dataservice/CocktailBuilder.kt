package com.brand.projectb.dataservice

import com.brand.projectb.BuildConfig
import com.brand.projectb.model.CocktailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

object CocktailBuilder {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .build()


    private val cocktailService: CocktailService = retrofit.create(CocktailService::class.java)

    suspend fun requestCocktailDetailsOf(id: String): CocktailModel =
        withContext(Dispatchers.IO) {
            cocktailService.fetchCocktail(id)
        }


    suspend fun requestCocktailListOf(name: String): List<CocktailModel> =
        withContext(Dispatchers.IO) {
            cocktailService.fetchCocktailList(name)
        }
}