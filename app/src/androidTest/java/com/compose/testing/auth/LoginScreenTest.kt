package com.compose.testing.auth

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.compose.testing.ui.screens.LoginScreen
import com.compose.testing.ui.theme.ComposeTestingTheme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<AuthActivity>()

    lateinit var passwordTextField: SemanticsNodeInteraction
    lateinit var emailTextField: SemanticsNodeInteraction
    lateinit var loginButton: SemanticsNodeInteraction
    lateinit var forgetPassword: SemanticsNodeInteraction
    lateinit var signUpButton: SemanticsNodeInteraction

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeTestingTheme {
                LoginScreen()
            }
        }
        passwordTextField = composeTestRule.onNodeWithTag("passwordTextField")
        emailTextField = composeTestRule.onNodeWithTag("emailTextField")
        loginButton = composeTestRule.onNodeWithText("Login")
        forgetPassword = composeTestRule.onNodeWithText("Forgot Password ?")
        signUpButton = composeTestRule.onNodeWithText("Don't have an Account ? Sign Up")
    }

    @Test
    fun testAllLoginNodesDoesExistsAndDisplayed() {

        val welcomeText = composeTestRule.onNodeWithText("WELCOME TO MONUMENTAL HABITS")
        val continueWithGoogleButton = composeTestRule.onNodeWithText("Continue with Google")
        val loginWithEmailText = composeTestRule.onNodeWithText("Log In with Email")

        // Check All Nodes Exists
        // Checks All Input Fields does Exists
        passwordTextField.assertExists()
        emailTextField.assertExists()

        welcomeText.assertExists()
        continueWithGoogleButton.assertExists()
        loginWithEmailText.assertExists()
        loginButton.assertExists()
        forgetPassword.assertExists()
        signUpButton.assertExists()

        // Check All Node Nodes are Displayed
        welcomeText.assertIsDisplayed()
        continueWithGoogleButton.assertIsDisplayed()
        loginWithEmailText.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        forgetPassword.assertIsDisplayed()
        signUpButton.assertIsDisplayed()

        loginButton.assertIsEnabled()
        forgetPassword.assertIsEnabled()
        signUpButton.assertIsEnabled()

        // Checks whether All input fields are blank when screen loaded
        assertTrue(passwordTextField.currentText().isNullOrBlank())
        assertTrue(emailTextField.currentText().isNullOrBlank())

        passwordTextField.printToLog("Login")
    }

    @Test
    fun login_with_the_credentials() {

        emailTextField.performTextInput("mhassanuetcs12@gmail.com")
        passwordTextField.performTextInput("123123")

        assertFalse(emailTextField.currentText().isNullOrBlank())
        assertFalse(passwordTextField.currentText().isNullOrBlank())
        assertTrue(passwordTextField.currentText()?.length ?: 0 >= 6)

        loginButton.performClick()
    }
}

