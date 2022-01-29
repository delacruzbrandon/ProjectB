package com.brand.projectb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brand.projectb.model.CocktailModel
import com.brand.projectb.dataservice.CocktailBuilder
import kotlinx.coroutines.launch

class CocktailViewModel: ViewModel() {

    /** Cocktail Details */
    private val _cocktailDetails = MutableLiveData<CocktailModel>()
    val cocktailDetails: LiveData<CocktailModel>
    get() = _cocktailDetails

    /** Cocktail Detailed List */
    private val _cocktailList = MutableLiveData<List<CocktailModel>>()
    val cocktailList: LiveData<List<CocktailModel>>
    get() = _cocktailList

    private val cocktailBuilder = CocktailBuilder

    init {

    }

    private fun getCocktails() {
        viewModelScope.launch {
            _cocktailList.value = getCocktailDataList()
        }
    }

    private suspend fun getCocktailDataList(): List<CocktailModel> {
        return cocktailBuilder.requestCocktailListOf("margarita")
    }
}