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
import androidx.compose.ui.unit.sp
import com.example.compose.createprofile.R
import com.example.compose.createprofile.theme.CreateProfileTheme
import com.example.compose.createprofile.util.supportWideScreen

sealed class SignUpEvent {
    data class SignUp(
        val firstName: String,
        val email: String,
        val password: String,
        val website: String
    ) : SignUpEvent()
}

@Composable
fun SignUp(onNavigationEvent: (SignUpEvent) -> Unit) {
    Scaffold(
        content = {
            SignUpScreen(
                modifier = Modifier.supportWideScreen()
            ) {
                Column {
                    SignUpContent(
                        onSignUpSubmitted = { name, email, password, website ->
                            onNavigationEvent(SignUpEvent.SignUp(name, email, password, website))
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun SignUpContent(
    onSignUpSubmitted: (name: String, email: String, password: String, website: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val firstNameState = remember {
            TextFieldState()
        }
        val emailState = remember { EmailState() }
        val passwordState = remember { PasswordState() }
        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
        val websiteState = remember { WebsiteState() }
        Title(title = R.string.title)
        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.caption,
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        FirstName(firstNameState, onImeAction = { passwordFocusRequest.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))

        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = stringResource(id = R.string.confirm_password),
            passwordState = confirmPasswordState,
            imeAction = ImeAction.Next,
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Website(
            websiteState = websiteState,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onSignUpSubmitted(
                    firstNameState.text,
                    emailState.text, passwordState.text, websiteState.text
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                passwordState.isValid && confirmPasswordState.isValid &&
                firstNameState.isValid && websiteState.isValid
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
