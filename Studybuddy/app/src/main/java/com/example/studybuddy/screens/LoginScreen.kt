package com.example.studybuddy.screens

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studybuddy.R
import com.example.studybuddy.viewmodel.StudyBuddyViewModel
import com.example.studybuddy.Utils
import com.example.studybuddy.ui.theme.Screen
import com.example.studybuddy.Utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreen(
    navController: NavHostController,
    mAuth: FirebaseAuth,
    viewModel: StudyBuddyViewModel
) {
    val context = LocalContext.current
    if (Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        SignInScreenLandScape(login = { email, password ->
            if (!Utils.isValidEmail(email)) {
                showToast(context, context.getString(R.string.email_is_not_valid))
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        context as Activity
                    ) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user: FirebaseUser? = mAuth.currentUser
                            user?.uid
                            // Do something with the user object
                            viewModel.user.value = user
                            Toast.makeText(
                                context,
                                context.getString(R.string.login_success),
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.CourseListScreen.route)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                context,
                                context
                                    .getString(R.string.login_failed, task.exception?.message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        },
            registration = {
                navController.popBackStack()
                navController.navigate(Screen.RegistrationScreen.route)
            })
    } else {
        SignInScreen(login = { email, password ->
            if (!Utils.isValidEmail(email)) {
                showToast(context, "Email is not valid")
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        context as Activity
                    ) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user: FirebaseUser? = mAuth.currentUser
                            user?.uid
                            // Do something with the user object
                            viewModel.user.value = user
                            Toast.makeText(
                                context,
                                context.getString(R.string.login_success_2),
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.CourseListScreen.route)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                context,
                                context.getString(
                                    R.string.login_failed_2,
                                    task.exception?.message
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        },
            registration = {
                navController.popBackStack()
                navController.navigate(Screen.RegistrationScreen.route)
            })
    }

}

@Composable
fun SignInScreen(login: (String, String) -> Unit, registration: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.profile_logo),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = context.getString(R.string.welcome),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { login(email, password) }) {
            Text(stringResource(R.string.login_text))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.don_t_have_an_account),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        ClickableText(
            text = AnnotatedString(stringResource(R.string.create_account)),
            onClick = { registration() },
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

@Composable
fun SignInScreenLandScape(login: (String, String) -> Unit, registration: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.profile_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { login(email, password) }) {
                Text(stringResource(R.string.login_text))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.don_t_have_an_account),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            ClickableText(
                text = AnnotatedString(stringResource(R.string.create_account)),
                onClick = { registration() },
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}