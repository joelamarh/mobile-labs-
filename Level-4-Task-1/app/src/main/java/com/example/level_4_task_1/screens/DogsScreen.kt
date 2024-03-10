package com.example.level_4_task_1.screens

import Dog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.level_4_task_1.R
import com.example.level_4_task_1.data.api.utils.Resource

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val dogResource: Resource<Dog>? by viewModel.dogResource.observeAsState()

    when (dogResource) {
        is Resource.Success -> (dogResource as Resource.Success<Dog>).data?.randomDogPictureUrl
        is Resource.Error -> (dogResource as Resource.Error<Dog>).message
        is Resource.Loading -> stringResource(id = R.string.loading_text)
        is Resource.Empty -> stringResource(id = R.string.empty_placeholder, "Dog")
        else -> stringResource(R.string.something_wrong_state)
    }
    val imageUrl = dogResource?.data?.randomDogPictureUrl
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Text(
                text = "Dogs image",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp),
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (imageUrl != null) {
                    Text(
                        text = stringResource(
                            id = R.string.breed, imageUrl.substringAfter("/breeds/").substringBefore(
                                "/"
                            )
                        )
                    )
                }

                Text(text = stringResource(id = R.string.click_below, stringResource(R.string.dog)))
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = "State"
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.get, stringResource(R.string.dog))) },
                onClick = { viewModel.getDog() },
                icon = { Icon(Icons.Filled.Refresh, "") },
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
            )
        }


    }
}