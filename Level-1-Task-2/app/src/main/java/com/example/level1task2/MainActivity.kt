package com.example.level1task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.level1task2.ui.theme.Level1Task2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level1Task2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val places = generatePlaces()
                    PlaceListScreen(places = places)
                }
            }
        }
    }
}
 private fun generatePlaces(): List<Place>{
     return listOf(
         Place("Amsterdam", "Dam", R.drawable.amsterdam_dam),
        Place("Amsterdam", "Weesperplein", R.drawable.amsterdam_weesperplein),
        Place("Rotterdam", "Euromast", R.drawable.rotterdam_euromast),
        Place("Den Haag", "Binnenhof", R.drawable.den_haag_binnenhof),
        Place("Utrecht", "Dom", R.drawable.utrecht_dom),
        Place("Groningen", "Martinitoren", R.drawable.groningen_martinitoren),
        Place("Maastricht", "Vrijthof", R.drawable.maastricht_vrijthof),
        Place("New York", "Vrijheidsbeeld", R.drawable.new_york_vrijheidsbeeld),
        Place("San Francisco", "Golden Gate", R.drawable.san_francisco_golden_gate),
        Place("Yellowstone", "Old Faithful", R.drawable.yellowstone_old_faithful),
        Place("Yosemite", "Half Dome", R.drawable.yosemite_half_dome),
        Place("Washington", "White House", R.drawable.washington_white_house),
        Place("Ottawa", "Parliament Hill", R.drawable.ottawa_parliament_hill),
        Place("Londen", "Tower Bridge", R.drawable.london_tower_bridge),
        Place("Brussel", "Manneken Pis", R.drawable.brussel_manneken_pis),
        Place("Berlijn", "Reichstag", R.drawable.berlijn_reichstag),
        Place("Parijs", "Eiffeltoren", R.drawable.parijs_eiffeltoren),
        Place("Barcelona", "Sagrada Familia", R.drawable.barcelona_sagrada_familia),
        Place("Rome", "Colosseum", R.drawable.rome_colosseum),
        Place("Napels", "Pompeii", R.drawable.pompeii),
        Place("Kopenhagen", "", R.drawable.kopenhagen),
        Place("Oslo", "", R.drawable.oslo),
        Place("Stockholm", "", R.drawable.stockholm),
        Place("Helsinki", "", R.drawable.helsinki),
        Place("Moskou", "Rode Plein", R.drawable.moskou_rode_plein),
        Place("Beijing", "Verboden Stad", R.drawable.beijing_verboden_stad),
        Place("Kaapstad", "Tafelberg", R.drawable.kaapstad_tafelberg),
        Place("Rio de Janeiro", "Copacabana", R.drawable.rio_de_janeiro_copacabana),
        Place("Sydney", "Opera", R.drawable.sydney_opera),
        Place("Hawaii", "Honolulu", R.drawable.hawaii),
        Place("Alaska", "Denali", R.drawable.alaska_denali)
     )
 }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListScreen(places: List<Place>) {
    var searchQueryState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    val matchingPlaces by remember(searchQueryState) {
        derivedStateOf {
            findMatchingCityNames(places, searchQueryState.text)
        }
    }

    Column {
        TextField(
            value = searchQueryState,
            onValueChange = {
                searchQueryState = it
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_text),
                    color = Color.Black
                )
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedLeadingIconColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        if (matchingPlaces.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(matchingPlaces) { place ->
                    ScreenContent(place = place)
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(places) { place ->
                    ScreenContent(place = place)
                }
            }
        }
    }
}

private fun findMatchingCityNames(places: List<Place>, query: String): List<Place> {
    return places.filter { place ->
        place.cityName.contains(query, ignoreCase = true)
    }
}

@Composable
fun ScreenContent(place: Place) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = place.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.cityName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = place.placeName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val places = generatePlaces()
    Level1Task2Theme {
        PlaceListScreen(places = places)
    }
}