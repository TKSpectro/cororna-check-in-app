package de.fhe.ai.pmc.acat.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import de.fhe.ai.pmc.acat.app.ui.components.Heading

@Composable
fun LoginScreen(vm: LoginScreenViewModel) {
    val context = LocalContext.current
    // TODO: Remove default values after implementing actual login
    var email by remember { mutableStateOf("admin@ethereal.com") }
    var password by remember { mutableStateOf("123123123") }

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
