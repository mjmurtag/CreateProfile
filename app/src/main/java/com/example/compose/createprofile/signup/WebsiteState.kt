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

import java.util.regex.Pattern

private const val WEBSITE_VALIDATION_REGEX = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*\$"
class WebsiteState :
    TextFieldState(validator = ::isWebsiteValid, errorFor = ::websiteValidationError)

private fun isWebsiteValid(website: String): Boolean {
    return Pattern.matches(WEBSITE_VALIDATION_REGEX, website)
}

@Suppress("UNUSED_PARAMETER")
private fun websiteValidationError(password: String): String {
    return "Invalid website"
}
