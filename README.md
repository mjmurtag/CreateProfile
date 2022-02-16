# CreateProfile
[![Android CI](https://github.com/mjmurtag/CreateProfile/actions/workflows/android.yml/badge.svg?branch=main)](https://github.com/mjmurtag/CreateProfile/actions/workflows/android.yml)

> Note: **Spotless Kotlin** is enforced via Github Actions on this repo
>
> Automatically resolve lint errors by running 
>
> `./gradlew spotlessapply`

## Overview
* Single Activity Architecture 
* Jetpack Compose
* Jetpack Navigation
* `Viewmodel` class allows user data to survive configuration changes like screen rotation
   * Enforces **Seperation of Concerns**
* Conforms to Material Design UX
* Primary colors have adequate contrast for low-vision users

## Showcase Skills
* Spotless kotlin enforcement on PRs into main
* Github action `gradlew build` enforced on PRs into main
* Utilization of modern Jetpack Android libraries yielded a UI that is
    * Responsive
    * Accessible 
    * Extensible 
    * Maintainable
    * Testable via automation

## Deviations From Assigned Task
* Form validation for each data type : `First Name`, `Email`, `Password`, `Website`
* Secret toggles used for password fields
* Secondary Color Accents which help the app 'feel' native to the OS
* Used Material default font to maintain OS consistency 
* Dark mode support

## TODO
* Unit test each class in isolation
* Add e2e integration tests to the PR Github Action
* Add dependency injection with `Hilt` or `Dagger`

### App scaffolding

Package [`com.example.compose.createprofile`]

This package contains 2 screens:
* Sign up
* Confirmation

### Data

The data in the sample is static, held in the `*Repository` classes.

Screenshots
-----------
## Light Mode
<p float="left">
    <img src="/screenshots/profile_create_light.png" width="250" height="500" />
    <img src="/screenshots/profile_create_light_filled.png" width="250" height="500" />
    <img src="/screenshots/confirmation_light.png" width="250" height="500" />    
</p>

## Form Validation and Secret Toggle
<p float="top">
    <img src="/screenshots/functional_security.png" width="250" height="100" />
    <img src="/screenshots/Invalid_Form.png" width="300" height="550" />
</p>

## Dark Mode

<p float="left">
    <img src="/screenshots/profile_create_dark.png" width="250" height="500" />
    <img src="/screenshots/profile_create_dark_filled.png" width="250" height="500" />
    <img src="/screenshots/confirmation_dark.png" width="250" height="500" />
</p>

### Built From Template
[Click here](https://github.com/android/compose-samples/) for instructions on how to
setup compose samples. 

## License

```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
