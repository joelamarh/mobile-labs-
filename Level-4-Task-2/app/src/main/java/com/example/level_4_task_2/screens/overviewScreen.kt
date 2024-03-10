package com.example.level_4_task_2.screens


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_2.R
import com.example.level_4_task_2.data.model.Movie
import com.example.level_4_task_2.viewmodel.MoviesViewModel


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OverviewScreen(viewModel: MoviesViewModel, navController: NavHostController) {
    val moviesResource: Resource<List<Movie>?>? by viewModel.moviesResource.observeAsState()
    val onMovieClicked: (Movie) -> Unit = { movie ->
        run {
            viewModel.selectedMovie = movie
            navController.navigate(MovieScreens.DetialScreen.route)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
            ) {
                SearchView(viewModel)
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {


            when (moviesResource) {
                is Resource.Success -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(
                            start = 0.dp,
                            end = 0.dp,
                        ),
                        horizontalArrangement = Arrangement.spacedBy(0.dp, CenterHorizontally),
                    ) {
                        val movies = (moviesResource as Resource.Success<List<Movie>?>).data;
                        items(items = movies!!, itemContent = { movie: Movie ->
                            MovieContent(movie, Modifier
                                .height(320.dp)
                                .padding(vertical = 8.dp, horizontal = 2.dp),
                                onMovieClicked)
                        })
                    }
                }
                is Resource.Error -> {
                    if ((moviesResource as Resource.Error<List<Movie>?>).message != null) {
                        Text(text = moviesResource?.message!!)
                    }
                }
                is Resource.Loading<*> -> {
                    CircularProgressIndicator()
                }
                else -> {
                    Text(text = moviesResource!!::class.java.simpleName)
                }
            }
        }

    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieContent(movie: Movie, modifier: Modifier = Modifier, onMovieClicked: (Movie) -> Unit = {}) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 12.dp),
            shape = RoundedCornerShape(size = 8.dp),
            elevation = 8.dp,
            onClick = { onMovieClicked(movie) }
        ) {
            val path = "https://image.tmdb.org/t/p/w780/"+movie.poster;
            MoviePoster(path, movie.title, Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MoviePoster(url:String, name:String, modifier: Modifier) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url)
            .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                crossfade(true)
                memoryCachePolicy(CachePolicy.ENABLED)
            }).build()
    )
    Image(
        contentScale = FillBounds,
        painter = painter,
        modifier = modifier,
        contentDescription = null
    )

    AsyncImage(
        contentScale = FillBounds,
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = name
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(viewModel: MoviesViewModel) {
    val searchQueryState = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchQueryState.value,
        onValueChange = { value ->
            searchQueryState.value = value

        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            IconButton(onClick = {
                viewModel.getMovies(searchQueryState.value.text);
                keyboardController?.hide()
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )
            }
        },
        trailingIcon = {
            if (searchQueryState.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        searchQueryState.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_movie_hint),
                color = Color.White
            )
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}




