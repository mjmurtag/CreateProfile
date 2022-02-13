/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.createprofile.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.createprofile.R
import com.example.compose.createprofile.theme.CreateProfileTheme
import com.example.compose.createprofile.util.supportWideScreen

sealed class SignUpEvent {
    data class SignUp(val email: String, val password: String) : SignUpEvent()
}

@Composable
fun SignUp(onNavigationEvent: (SignUpEvent) -> Unit) {
    Scaffold(
        topBar = {
            SignUpTopAppBar(
                topAppBarText = stringResource(id = R.string.title),
            )
        },
        content = {
            SignUpScreen(
                modifier = Modifier.supportWideScreen()
            ) {
                Column {
                    SignUpContent(
                        onSignUpSubmitted = { email, password ->
                            onNavigationEvent(SignUpEvent.SignUp(email, password))
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun SignUpContent(
    onSignUpSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val firstNameState = remember {
            TextFieldState()
        }
        val emailState = remember { EmailState() }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.caption
            )
        }
        FirstName(firstNameState, onImeAction = { passwordFocusRequest.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))

        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordState() }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
        Password(
            label = stringResource(id = R.string.confirm_password),
            passwordState = confirmPasswordState,
            imeAction = ImeAction.Next,
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val websiteState = remember { WebsiteState() }
        Website(
            websiteState = websiteState,
            imeAction = ImeAction.Next,
            onImeAction = { onSignUpSubmitted(emailState.text, passwordState.text) },
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSignUpSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = stringResource(id = R.string.create_account))
        }
    }
}

@Preview(widthDp = 1024)
@Composable
fun SignUpPreview() {
    CreateProfileTheme {
        SignUp {}
    }
}
