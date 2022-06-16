package de.fhe.ai.pmc.acat.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import de.fhe.ai.pmc.acat.app.ui.components.Heading

@Composable
fun LoginScreen(vm: LoginScreenViewModel) {
    val context = LocalContext.current
    // TODO: Remove default values after implementing actual login
    var email by remember { mutableStateOf("admin@ethereal.com") }
    var password by remember { mutableStateOf("CoronaCheckIn1!") }
    var passwordVisible by remember { mutableStateOf(false) }

    // https://regex101.com/library/fX8dY0?orderBy=MOST_POINTS&search=password
    //    val passwordRegex = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S\$".toRegex()
    val passwordRegex = "(.*)".toRegex()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Heading("Login")

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "Email") },
            singleLine = true,
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "password") },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    // TODO: Get better icons eye and crossed through eye
                    Icons.Filled.CheckCircle
                else Icons.Filled.AddCircle

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )

        Button(onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    vm.showEmailErrorToast(context = context)
                } else if (!passwordRegex.matches(password)) {
                    vm.showPasswordErrorToast(context = context)
                } else {
                    vm.login(email, password, context = context)
                }
            } else {
                vm.showInputErrorToast(context = context)
            }
        }) {
            Text("Login")
        }
    }
}
