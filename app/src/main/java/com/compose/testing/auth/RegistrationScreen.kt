package com.compose.testing.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.testing.R
import com.compose.testing.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen() {

    var checkBoxOne by remember { mutableStateOf(true) }
    var checkBoxTwo by remember { mutableStateOf(true) }

    val userNameState = rememberTextInputFieldState()
    val emailState = rememberTextInputFieldState()
    val passwordState = rememberTextInputFieldState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val userNameFocus = FocusRequester()
    val emailFocus = FocusRequester()
    val passwordFocus = FocusRequester()

    DisposableEffect(key1 = Unit) {
        userNameFocus.requestFocus()
        onDispose { }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {

        RegistrationScreenHeader(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        RegistrationInputFields(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            userName = userNameState,
            keyboardController = keyboardController,
            userNameFocus = userNameFocus,
            emailFocus = emailFocus,
            passwordFocus = passwordFocus
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 22.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkBoxTwo, onCheckedChange = { checkBoxTwo = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryColor,
                    uncheckedColor = SecondaryColor,
                    checkmarkColor = SecondaryColor
                ),
                modifier = Modifier.clip(shape = Shapes.medium)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Email me about special pricing",
                fontFamily = Poppins,
                color = SecondaryColor,
                fontSize = 12.sp
            )
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            ),
            shape = Shapes.medium
        ) {
            Text(
                text = "Sign Up with Email",
                fontFamily = Poppins,
                color = SecondaryColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SocialMediaSignInButtons()

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Already have an account ? Sign In",
                fontFamily = Poppins,
                color = SecondaryColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun RegistrationScreenHeader(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.ic_register_img),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "CREATE YOUR ACCOUNT",
            textAlign = TextAlign.Center,
            fontFamily = Poppins,
            color = SecondaryColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .testTag("create_account_text"),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationInputFields(
    modifier: Modifier,
    userName: InputFieldState = rememberTextInputFieldState(),
    email: InputFieldState = rememberTextInputFieldState(),
    password: InputFieldState = rememberTextInputFieldState(),
    keyboardController : SoftwareKeyboardController?,
    userNameFocus: FocusRequester,
    emailFocus: FocusRequester,
    passwordFocus: FocusRequester
) {

    TextField(
        value = userName.text, onValueChange = { userName.text = it },
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .testTag("userNameTextField")
            .focusRequester(userNameFocus),
        colors = TextFieldDefaults.textFieldColors(
            textColor = PrimaryColor,
            backgroundColor = Color.White,
            cursorColor = PrimaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = InputBoxShape.medium,
        singleLine = true,
        leadingIcon = {
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user), contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(BackgroundColor)
                )
            }
        },
        placeholder = {
            Text(text = "Username", color = PlaceholderColor)
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {
            emailFocus.requestFocus()
        })
    )

    TextField(
        value = email.text, onValueChange = { email.text = it },
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .testTag("emailTextField")
            .focusRequester(focusRequester = emailFocus),
        colors = TextFieldDefaults.textFieldColors(
            textColor = PrimaryColor,
            backgroundColor = Color.White,
            cursorColor = PrimaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = InputBoxShape.medium,
        singleLine = true,
        leadingIcon = {
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email_outline),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(BackgroundColor)
                )
            }
        },
        placeholder = {
            Text(text = "Email Address", color = PlaceholderColor)
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                passwordFocus.requestFocus()
            }
        )
    )

    TextField(
        value = password.text, onValueChange = { password.text = it },
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .testTag("passwordTextField")
            .focusRequester(passwordFocus),
        colors = TextFieldDefaults.textFieldColors(
            textColor = PrimaryColor,
            backgroundColor = Color.White,
            cursorColor = PrimaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = InputBoxShape.medium,
        singleLine = true,
        leadingIcon = {
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock), contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(BackgroundColor)
                )
            }
        },
        placeholder = {
            Text(text = "Password", color = PlaceholderColor)
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        })
    )
}

@Composable
fun SocialMediaSignInButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier
                .clip(shape = Shapes.large)
                .testTag("signInWithGoogle"),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Google",
                    fontFamily = Poppins,
                    color = SecondaryColor
                )
            }
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier
                .clip(shape = Shapes.large)
                .testTag("signInWithFacebook"),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Facebook",
                    fontFamily = Poppins,
                    color = SecondaryColor
                )
            }
        }
    }
}