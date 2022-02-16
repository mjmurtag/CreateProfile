/*
 * Copyright 2022 The Android Open Source Project
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

package com.example.compose.createprofile.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.createprofile.R
import com.example.compose.createprofile.signup.SignUpViewModel
import com.example.compose.createprofile.signup.User
import com.example.compose.createprofile.util.supportWideScreen

@Composable
fun ConfirmationScreen(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Confirm() {
    var viewModel: SignUpViewModel = viewModel()
    var user: User = viewModel.getUser()
    when (user) {
        is User.LoggedInUser -> {
            Scaffold(
                content = {
                    ConfirmationScreen(
                        modifier = Modifier.supportWideScreen()
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.hello) + " " +
                                    user.name + "!",
                                fontSize = 35.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = stringResource(id = R.string.confirmation))
                            Spacer(modifier = Modifier.height(16.dp))
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                        append(
                                            AnnotatedString(
                                                text = user.website,
                                                spanStyle =
                                                SpanStyle(Color.Blue)
                                            )
                                        )
                                    },
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = user.name, textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = user.email)
                                Spacer(modifier = Modifier.height(300.dp))
                                Button(
                                    onClick = {
                                    },

                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Text(text = stringResource(id = R.string.sign_in))
                                }
                            }
                        }
                    }
                }
            )
        }
        is User.NoUserLoggedIn -> {
            Scaffold(
                content = {
                    ConfirmationScreen(
                        modifier = Modifier.supportWideScreen()
                    ) {
                        Column {
                            Text(text = "Error!")
                        }
                    }
                }
            )
        }
    }
}
