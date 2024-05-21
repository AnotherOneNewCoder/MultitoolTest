package ru.zhogin.app.api

import ru.zhogin.app.api.gif.GifDTO
import ru.zhogin.app.api.models.evilinsult.InsultDTO

interface KtorRepository {

    suspend fun changeInsultLanguage(lang: String) : String

    suspend fun getInsult(lang: String) : Resource<InsultDTO>

    suspend fun getGif() : Resource<GifDTO>
}