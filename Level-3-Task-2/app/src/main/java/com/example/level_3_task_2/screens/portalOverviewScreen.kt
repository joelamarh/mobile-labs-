package com.example.level_3_task_2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.level_3_task_2.R
import com.example.level_3_task_2.viewModel.PoralView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortalOverviewScreen(
    navHostController: NavHostController,
    viewModel: PoralView,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = {padding ->
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp , top = 60.dp),
                horizontalAlignment = Alignment.Start,

                ) {
                UrlsList(viewModel)
            }
        },
        floatingActionButton = { UrlListScreenFab(navHostController) }
    )
}

@Composable
private fun UrlsList(viewModel: PoralView) {
    val uriHandler = LocalUriHandler.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        viewModel.portals.forEachIndexed { _, portal ->
            items(1, span = { GridItemSpan(1) }) {
                Card(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 4.dp)
                        .size(150.dp, 70.dp)
                        .clickable {  uriHandler.openUri(portal.portalUrl) }) {
                    Column() {
                        Text(
                            text = portal.portalName,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.size(0.dp, 15.dp))
                        Text(
                            text = portal.portalUrl,
                            fontSize = 10.sp
                        )

                    }

                }
            }
        }
    }
}


@Composable
fun UrlListScreenFab(navController: NavHostController) {
    FloatingActionButton(onClick = {
        navController.navigate(portalScreens.AddPortal.name)
    }) {
        Icon(Icons.Filled.Add, "")
    }
}

