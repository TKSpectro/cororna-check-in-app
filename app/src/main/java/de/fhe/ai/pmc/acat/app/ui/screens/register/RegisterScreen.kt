package de.fhe.ai.pmc.acat.app.ui.screens.register

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fhe.ai.pmc.acat.app.ui.components.Heading


@Composable
fun RegisterScreen(vm: RegisterScreenViewModel) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
        Heading("Register")

        val modifier = Modifier.padding(vertical = 8.dp)
        val shape = RoundedCornerShape(30.dp)


        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = { Text(text = "Max") },
            label = { Text(text = "Firstname") },
            singleLine = true,
            modifier= modifier,
            shape = shape
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholder = { Text(text = "Muster") },
            label = { Text(text = "lastName") },
            singleLine = true,
            modifier= modifier,
            shape = shape
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "Email") },
            singleLine = true,
            modifier= modifier,
            shape = shape
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "password") },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier= modifier,
            shape = shape,
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

        Button(modifier= modifier, shape = shape ,onClick = {
            if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                vm.showEmailErrorToast(context = context)
            } else if (!passwordRegex.matches(password)) {
                vm.showPasswordErrorToast(context = context)
            } else {
                vm.register(firstName, lastName, email, password, context = context)
            }
        } else {
            vm.showInputErrorToast(context = context)
        }

        }) {
            Text("Register")
        }

        Row(){
            Text("Already have an account? ", )
            Text("Sign in", modifier = Modifier.clickable { vm.navigateToLoginScreen() }, MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        

    }

}