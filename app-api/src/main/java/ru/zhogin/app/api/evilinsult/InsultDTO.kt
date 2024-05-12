package ru.zhogin.app.api.models.evilinsult


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InsultDTO(
    @SerialName("active")
    val active: String,
    @SerialName("comment")
    val comment: String,
    @SerialName("created")
    val created: String,
    @SerialName("createdby")
    val createdby: String,
    @SerialName("insult")
    val insult: String,
    @SerialName("language")
    val language: String,
    @SerialName("number")
    val number: String,
    @SerialName("shown")
    val shown: String
)