package com.compose.testing.auth

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.compose.testing.ui.theme.ComposeTestingTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistrationScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<AuthActivity>()
    lateinit var userNameField: SemanticsNodeInteraction
    lateinit var passwordTextField: SemanticsNodeInteraction
    lateinit var emailTextField: SemanticsNodeInteraction

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeTestingTheme {
                RegistrationScreen()
            }
        }
        userNameField = composeTestRule.onNodeWithTag("userNameTextField")
        passwordTextField = composeTestRule.onNodeWithTag("passwordTextField")
        emailTextField = composeTestRule.onNodeWithTag("emailTextField")
    }

    @Test
    fun testRegistrationInputFieldAreVisible() {

        // Checks All Input Fields does Exists
        userNameField.assertExists()
        passwordTextField.assertExists()
        emailTextField.assertExists()

        userNameField.printToLog("Registration")
        passwordTextField.printToLog("Registration")
        emailTextField.printToLog("Registration")

        // Checks whether All input fields are blank when screen loaded
        assertTrue(userNameField.currentText().isNullOrBlank())
        assertTrue(passwordTextField.currentText().isNullOrBlank())
        assertTrue(emailTextField.currentText().isNullOrBlank())
        assertEquals("Next", userNameField.imeActionValue())

    }

    @Test
    fun testAllRegistrationNodesDoesExistsAndDisplayed() {

        val signInWithGoogleButton = composeTestRule.onNodeWithTag("signInWithGoogle")
        val signInWithFacebookButton = composeTestRule.onNodeWithTag("signInWithFacebook")
        val signInTextButton = composeTestRule.onNodeWithText("Already have an account ? Sign In")
        val signUpWithEmailButton = composeTestRule.onNodeWithText("Sign Up with Email")
        val specialPricingEmailCheckBox =
            composeTestRule.onNodeWithText("Email me about special pricing")
        val createYourAccountText = composeTestRule.onNodeWithTag("create_account_text")

        // Check All Nodes Exists
        createYourAccountText.assertExists()
        specialPricingEmailCheckBox.assertExists()
        signUpWithEmailButton.assertExists()
        signInTextButton.assertExists()
        signInWithFacebookButton.assertExists()
        signInWithGoogleButton.assertExists()

        // Check All Node Nodes are Displayed
        createYourAccountText.assertIsDisplayed()
        specialPricingEmailCheckBox.assertIsDisplayed()
        signUpWithEmailButton.assertIsDisplayed()
        signInTextButton.assertIsDisplayed()
        signInWithFacebookButton.assertIsDisplayed()
        signInWithGoogleButton.assertIsDisplayed()

    }

    @Test
    fun signup_with_the_credentials() {

        userNameField.performTextInput("hassan")
        emailTextField.performTextInput("mhassanuetcs12@gmail.com")
        passwordTextField.performTextInput("123123")

        assertFalse(userNameField.currentText().isNullOrBlank())
        assertFalse(emailTextField.currentText().isNullOrBlank())
        assertFalse(passwordTextField.currentText().isNullOrBlank())
        assertTrue(passwordTextField.currentText()?.length ?: 0 >= 6)

        val signUpButton = composeTestRule.onNodeWithText("Sign Up with Email")
        signUpButton.performClick()
    }
}

