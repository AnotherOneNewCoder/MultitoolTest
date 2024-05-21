package ru.zhogin.app.features.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import ru.zhogin.app.data.RequestResult
import ru.zhogin.app.features.bottomscreens.ErrorScreen
import ru.zhogin.app.features.bottomscreens.ProgressIndicator
import ru.zhogin.app.features.models.GifUI

@Composable
internal fun RandomGifScreen(
    randomGif: State<RequestResult<GifUI>?>,
    newGif: () -> Unit,
) {
    Column {

        if (randomGif.value is RequestResult.Error) {
            ErrorScreen()
        }
        if (randomGif.value is RequestResult.InProgress) {
            ProgressIndicator()
        }
        if (randomGif.value?.data != null) {
            randomGif.value?.data?.let {
                ShowRandomGif(gifUI = it, newGif = newGif)
            }
        }
    }
}

@Composable
internal fun ShowRandomGif(
    gifUI: GifUI,
    newGif: () -> Unit,
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = rememberAsyncImagePainter(gifUI.image, imageLoader),
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )
        Button(
            onClick = { newGif() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            ) {
             Icon(imageVector = Icons.Filled.Refresh, contentDescription = "new gif", tint = Color.White,
                 modifier = Modifier.size(60.dp))
        }
    }

}
