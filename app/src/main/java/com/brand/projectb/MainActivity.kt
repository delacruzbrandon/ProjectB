package com.brand.projectb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.ViewModelProvider
import com.brand.projectb.dataservice.CocktailTestData
import com.brand.projectb.model.CocktailModel
import com.brand.projectb.ui.theme.ProjectBTheme
import com.brand.projectb.viewmodel.cocktail.CocktailViewModel

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    lateinit var cocktails: List<CocktailModel>
    lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ProjectBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    CocktailList(_cocktailList = cocktails)
                    DefaultPreview()
                    cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]
                    cocktailViewModel.cocktailList.observe(this) {
                        Log.d(TAG, "onCreate: $it")
                    }
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

    var isVisible by remember { mutableStateOf(0f) }
    var isExpanded by remember { mutableStateOf(true) }

//    if (cocktailIsAlcoholic(_cocktail.drinkIsAlcoholic)) {
//        isVisible = 100f
//    }

    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .border(
                BorderStroke(
                    1.dp, Brush.horizontalGradient(
                        0.8f to Color.Gray,
                        1.0f to Color.White,
                        startX = 0.0f,
                        endX = 100.0f
                    )
                )
            )
    ) {

        /** View References */
        val (
            columnCocktailDetails,
            imageCocktailPreview,
            imageIsAlcoholicLabel,
        ) = createRefs()

        Image(
            painter = painterResource(id = R.mipmap.testdrink),
            contentDescription = "Drink Thumbnail",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(imageCocktailPreview) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(columnCocktailDetails) {
                    top.linkTo(imageCocktailPreview.top)
                    start.linkTo(imageCocktailPreview.end, margin = 16.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        ) {
            Column(modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .constrainAs(columnCocktailDetails) {
                    end.linkTo(imageIsAlcoholicLabel.start)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }) {
                Text(
                    text = _cocktail.drinkName,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = _cocktail.drinkTags,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.alpha(.5f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = _cocktail.drinkInstructions,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                    modifier = expandBody(isExpanded)
                )
            }
            Image(
                painter = painterResource(id = R.mipmap.nonalcoholic),
                contentDescription = "Alcohol Free Drink",
                modifier = Modifier
                    .size(22.dp)
                    .alpha(isVisible)
                    .constrainAs(imageIsAlcoholicLabel) {
                        end.linkTo(parent.end)
                        top.linkTo(columnCocktailDetails.top)
                    }
            )
        }

    }


}

private fun bodyVisible(_isAlcoholic: String): Float {
    return if (alcoholicDrink(_isAlcoholic)) {
         100f
    } else 0f
}

private fun alcoholicDrink(_alcoholicDrink: String): Boolean {
    return _alcoholicDrink != "Alcoholic"
}

private fun expandBody(bodyClicked: Boolean): Modifier {
    return if(!bodyClicked) {
        Modifier
            .animateContentSize()
            .height(0.dp)
    } else Modifier
        .animateContentSize()
        .wrapContentHeight()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectBTheme {
//        CocktailList(_cocktailList = CocktailTestData.cocktailList)
        Cocktail(_cocktail = CocktailTestData.cocktailNonAlcoholic)
    }
}