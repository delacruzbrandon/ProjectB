package com.brand.projectb.model

import com.google.gson.annotations.SerializedName

data class CocktailModel(
    @SerializedName("idDrink")
    var drinkId: String,

    @SerializedName("strDrink")
    var drinkName: String,

    @SerializedName("strTags")
    var drinkTags: String,

    @SerializedName("strCategory")
    var drinkType: String,

    @SerializedName("strIBA")
    var drinkIBA: String, // cocktails selected by the International Bartenders Association (IBA)

    @SerializedName("strGlass")
    var drinkGlass: String,

    @SerializedName("strDrinkThumb")
    var drinkImage: String,

    @SerializedName("strAlcoholic")
    var drinkIsAlcoholic: String,

    @SerializedName("strInstructions")
    var drinkInstructions: String,
)