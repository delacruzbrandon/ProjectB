package com.brand.projectb.dataservice

import com.brand.projectb.model.CocktailModel

object CocktailTestData {

    val cocktailAlcoholic: CocktailModel = CocktailModel(
        "17837",
        "Adam's Affair",
        "Alcoholic,Holiday",
        "Ordinary Drink",
        "null",
        "Cocktail glass",
        "https://www.thecocktaildb.com/images/media/drink/v0at4i1582478473.jpg/preview",
        "Alcoholic",
        "In a shaker half-filled with ice cubes, combine all of the ingredients. Shake well. Strain into a cocktail glass."
    )

    val cocktailNonAlcoholic: CocktailModel = CocktailModel(
        "17837",
        "Adam's Affair",
        "Alcoholic,Holiday",
        "Ordinary Drink",
        "null",
        "Cocktail glass",
        "https://www.thecocktaildb.com/images/media/drink/v0at4i1582478473.jpg/preview",
        "Non Alcoholic",
        "In a shaker half-filled with ice cubes, combine all of the ingredients. Shake well. Strain into a cocktail glass."
    )

    val cocktailList: List<CocktailModel> = listOf(
        cocktailAlcoholic,
        cocktailAlcoholic,
        cocktailNonAlcoholic,
        cocktailNonAlcoholic,
        cocktailAlcoholic,
        cocktailNonAlcoholic,
        cocktailAlcoholic,
    )

}