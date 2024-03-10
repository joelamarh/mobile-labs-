package com.example.level_3_task_2.screens

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.level_3_task_2.model.Portal
import com.example.level_3_task_2.viewModel.PoralView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addPortal(
    navHostController: NavHostController,
    viewModel: PoralView,
) {
    val context = LocalContext.current
    var portalName by remember { mutableStateOf("") }
    var portalUrl by remember { mutableStateOf("https://") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = com.example.level_3_task_2.R.string.app_name)) }
            )
        },
        content = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 60.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp , top = 50.dp),
                    value = portalName,

                    label = { Text(text = stringResource(id = com.example.level_3_task_2.R.string.new_portal_url)) },
                    onValueChange = { portalName = it },
                )
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start= 8.dp ,top = 50.dp),
                    value = portalUrl,
                    label = { Text(text = stringResource(id = com.example.level_3_task_2.R.string.new_portal_Name)) },
                    onValueChange = { portalUrl = it },
                    placeholder = {
                        Text(text = stringResource(id = com.example.level_3_task_2.R.string.new_portal_url))
                    })
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        onClick = {
                            if (portalName.isNotBlank() && portalUrl.isNotBlank() && URLUtil.isValidUrl(portalUrl)) {
                                viewModel.addPortal(Portal(portalName, portalUrl))
                                informUser(context, com.example.level_3_task_2.R.string.url_added)
                                navHostController.navigate(portalScreens. portalOverview.name)
                            } else {
                                informUser(context, com.example.level_3_task_2.R.string.empty_name)
                            }
                        }) {
                        Text(text = stringResource(id = com.example.level_3_task_2.R.string.url_added))
                    }
                }
            }
        },
    )
}

fun informUser(context: Context, msg: Int) {

    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}