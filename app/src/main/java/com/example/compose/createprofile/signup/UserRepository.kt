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

import androidx.compose.runtime.Immutable

sealed class User {
    @Immutable
    data class LoggedInUser(
        val name: String,
        val email: String,
        val password: String,
        val website: String
    ) : User()
    object NoUserLoggedIn : User()
}

/**
 * Repository that holds the logged in user.
 *
 * In a production app, this class would also handle the communication with the backend for
 * sign in and sign up.
 */
object UserRepository {
    private var _user: User = User.NoUserLoggedIn
    val user: User
        get() = _user
    @Suppress("UNUSED_PARAMETER")
    fun signUp(name: String, email: String, password: String, website: String) {
        _user = User.LoggedInUser(name, email, password, website)
    }
}
