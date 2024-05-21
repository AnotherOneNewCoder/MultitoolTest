package ru.zhogin.app.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.zhogin.app.data.RequestResult
import ru.zhogin.app.features.models.InsultUI
import ru.zhogin.app.features.bottomscreens.ErrorScreen
import ru.zhogin.app.features.bottomscreens.ProgressIndicator
import ru.zhogin.app.uikit.MediumTextWhite
import ru.zhogin.app.uikit.SmallItalicTextWhite

@Composable
internal fun DataBaseScreen(

    deleteInsultFromDb: (InsultUI) -> Unit,
    insultsFromDb: State<RequestResult<List<InsultUI>>?>,
) {
    Column {
        if (insultsFromDb.value is RequestResult.Error) {
            ErrorScreen()
        }
        if (insultsFromDb.value is RequestResult.InProgress) {
            ProgressIndicator()
        }
        if (!insultsFromDb.value?.data.isNullOrEmpty()) {
            insultsFromDb.value?.data?.let { insultUIList ->
                ListInsultsInDb(
                    insultUIList,
                    deleteInsultFromDb = {
                        deleteInsultFromDb(it)
                    }
                )
            }
        }
    }
}

@Composable
internal fun ListInsultsInDb(
    insults: List<InsultUI>,
    deleteInsultFromDb: (InsultUI) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
            .padding(bottom = 64.dp),
    )
    {
        LazyColumn {
            items(insults.size) { count ->
                InsultInDb(
                    insults[count],
                    deleteInsultFromDb = {
                        deleteInsultFromDb(it)
                    }
                )
            }
        }
    }
}

@Composable
fun InsultInDb(
    insultUI: InsultUI,
    deleteInsultFromDb: (InsultUI) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B2A2A)
        )
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = insultUI.insult,
                    style = MaterialTheme.typography.MediumTextWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (insultUI.comment.isNotEmpty()) {
                    Text(
                        text = insultUI.comment,
                        style = MaterialTheme.typography.SmallItalicTextWhite
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Text(text = insultUI.created, style = MaterialTheme.typography.SmallItalicTextWhite)
                Spacer(modifier = Modifier.height(4.dp))

            }

            Button(
                onClick = { deleteInsultFromDb(insultUI) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete",
                    tint = Color.White
                )
            }
        }

    }
}