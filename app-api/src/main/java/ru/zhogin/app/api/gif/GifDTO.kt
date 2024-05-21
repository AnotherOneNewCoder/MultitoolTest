package ru.zhogin.app.api.gif


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifDTO(
    @SerialName("answer")
    val answer: String,
    @SerialName("forced")
    val forced: Boolean,
    @SerialName("image")
    val image: String
)