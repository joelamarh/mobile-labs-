package com.example.studybuddy.screens

// Import statements...
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.example.studybuddy.R
import com.example.studybuddy.Utils
import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.studybuddy.viewmodel.StudyBuddyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemberScreen(
    index: Int,
    navController: NavHostController,
    viewModel: StudyBuddyViewModel
) {
    val context = LocalContext.current
    val course = viewModel.courseList.value?.get(index)
    var users by remember { mutableStateOf(course?.users) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_member)) },
                Modifier.background(MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(
                        onClick = {
                            if (users?.isEmpty() == true) {
                                Utils.showToast(
                                    context,
                                    context.getString(R.string.users_cant_be_empty)
                                )
                                return@IconButton
                            } else {
                                if (course != null) {
                                    viewModel.postDataToFirebase(
                                        course.title,
                                        course.location,
                                        course.date,
                                        course.time,
                                        users!!
                                    )
                                }
                                Utils.showToast(
                                    context,
                                    context.getString(R.string.successfully_done)
                                )
                                navController.navigateUp()
                            }
                        },
                        content = {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = stringResource(R.string.add)
                            )
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        if (isLandscape()) {
            AddMemberContentLandscape(users = users ?: "", onUsersChanged = { users = it }, contentPadding = innerPadding)
        } else {
            AddMemberContent(users = users ?: "", onUsersChanged = { users = it }, contentPadding = innerPadding)
        }
    }
}

@Composable
fun isLandscape(): Boolean {
    return LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
}

@Composable
fun AddMemberContent(users: String, onUsersChanged: (String) -> Unit, contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(contentPadding)
            .background(Color.White, shape = RoundedCornerShape(15.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = users,
            onValueChange = { onUsersChanged(it) },
            label = { Text(stringResource(R.string.users)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
            modifier = Modifier
                .padding(5.dp)
                .height(200.dp)
        )
    }
}

@Composable
fun AddMemberContentLandscape(users: String, onUsersChanged: (String) -> Unit, contentPadding: PaddingValues) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = users,
            onValueChange = { onUsersChanged(it) },
            label = { Text(stringResource(R.string.users)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
    }
}


