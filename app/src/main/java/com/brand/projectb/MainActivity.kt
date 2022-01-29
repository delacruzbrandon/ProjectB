package com.brand.projectb

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.brand.projectb.dataservice.CocktailTestData
import com.brand.projectb.model.CocktailModel
import com.brand.projectb.ui.theme.ProjectBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}

@Composable
fun CocktailList(_cocktailList: List<CocktailModel>) {
    LazyColumn {
        items(_cocktailList) { _cocktail ->
            Cocktail(_cocktail = _cocktail)
        }
    }
}

@Composable
fun Cocktail(_cocktail: CocktailModel) {

    var isAlcoholic by remember { mutableStateOf(0f) }

    if (CocktailIsAlcoholic(_cocktail.drinkIsAlcoholic)) {
        isAlcoholic = 100f
    }

    ConstraintLayout(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(1f)
    ) {

        /** View References */
        val (
            columnCocktailDetails,
            textCocktailName,
            imageCocktailPreview,
            imageIsAlcoholicLabel,
        ) = createRefs()

        Image(
            painter = painterResource(id = R.mipmap.testdrink),
            contentDescription = "Drink Thumbnail",
            modifier = Modifier.constrainAs(imageCocktailPreview) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        Column(modifier = Modifier.constrainAs(columnCocktailDetails) {
            start.linkTo(imageCocktailPreview.end, margin = 16.dp)
            top.linkTo(imageCocktailPreview.top)
        }) {
            ConstraintLayout() {
                Text(text = _cocktail.drinkName,
                    modifier = Modifier
                        .constrainAs(textCocktailName) {
                            top.linkTo(parent.top)
                        }
                )
                Image(
                    painter = painterResource(id = R.mipmap.nonalcoholic),
                    contentDescription = "Alcohol Free Drink",
                    modifier = Modifier
                        .alpha(isAlcoholic)
                        .constrainAs(imageIsAlcoholicLabel) {
                            end.linkTo(parent.end)
                            top.linkTo(textCocktailName.top)
                            bottom.linkTo(textCocktailName.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.wrapContent
                        }
                )
            }
        }

    }


}

fun CocktailIsAlcoholic(_alcoholicDrink: String): Boolean {
    return _alcoholicDrink != "Alcoholic"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectBTheme {
        CocktailList(_cocktailList = CocktailTestData.cocktailList)
    }
}