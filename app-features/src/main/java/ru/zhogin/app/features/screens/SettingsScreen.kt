package ru.zhogin.app.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.app.uikit.MediumTextWhite

@Composable
internal fun SettingsScreen(
    language: MutableState<String>,
    onChangeLang: (String) -> Unit
) {
    var languageUI by rememberSaveable {
        mutableStateOf(language.value.uppercase())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
            .padding(bottom = 64.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Language: $languageUI", style = MaterialTheme.typography.MediumTextWhite)
            Button(
                onClick = {
                    onChangeLang("ru")
                    languageUI = "RU"
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "RU", fontSize = 14.sp)
            }
            Button(
                onClick = {
                    onChangeLang("en")
                    languageUI = "EN"
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "EN", fontSize = 14.sp)
            }
            Button(
                onClick = {
                    onChangeLang("es")
                    languageUI = "ES"
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(text = "ES", fontSize = 14.sp)
            }
        }
    }
}