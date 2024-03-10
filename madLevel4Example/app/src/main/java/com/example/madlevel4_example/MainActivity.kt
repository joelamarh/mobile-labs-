package com.example.madlevel4_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madlevel4_example.Data.Numbers
import com.example.madlevel4_example.Data.api.util.Resource
import com.example.madlevel4_example.ui.theme.MADLevel4ExampleTheme
import com.example.madlevel4_example.viewmodel.NumbersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADLevel4ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumbersApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { innerPadding -> ScreenContent(Modifier.padding(innerPadding)) }
    )

}


@Composable
fun ScreenContent(
    modifier: Modifier,
    viewModel: NumbersViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    // Make sure to import :
    // import androidx.compose.runtime.getValue
    // import androidx.compose.runtime.livedata.observeAsState
    val numberResource: Resource<Numbers>? by viewModel.numberResource.observeAsState()

    /*
       Determine in which state what to show
       You could expand this in the future by for example also adding a loading indicator
     */
    val numberText = when (numberResource) {
        is Resource.Success -> numberResource?.data?.text
        is Resource.Error -> numberResource?.message
        is Resource.Loading -> stringResource(R.string.loading_text)
        is Resource.Empty -> stringResource(id = R.string.empty_number_placeholder)
        else -> stringResource(R.string.something_wrong_state)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.trivia),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 60.dp, horizontal = 16.dp)
        )

        Text(
            text = numberText ?: stringResource(id = R.string.empty_number_placeholder),
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp)
                .heightIn(min = 96.dp)
        )

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.get_new_number)) },
            onClick = { viewModel.getNumber() },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}
