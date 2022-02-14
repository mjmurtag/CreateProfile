package com.example.compose.createprofile.confirmation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.createprofile.signup.SignUpEvent
import com.example.compose.createprofile.signup.SignUpViewModel
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
fun Confirm(onNavigationEvent: (SignUpEvent) -> Unit) {
    Scaffold(
        content = {
            ConfirmationScreen(
                modifier = Modifier.supportWideScreen()
            ) {
                Column {
                    ConfirmationContent()
                }
            }
        }
    )
}

@Composable
fun ConfirmationContent(){
    val viewModel: SignUpViewModel = viewModel()
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(viewmo)
    }
}
