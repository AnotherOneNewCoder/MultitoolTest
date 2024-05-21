package ru.zhogin.app.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import ru.zhogin.app.api.gif.GifDTO
import ru.zhogin.app.api.models.evilinsult.InsultDTO
import ru.zhogin.app.api.network.GIFURL

import javax.inject.Inject

class KtorRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : KtorRepository {


    override suspend fun changeInsultLanguage(lang: String): String {
        return "https://evilinsult.com/generate_insult.php?lang=$lang&type=json"

    }


    override suspend fun getInsult(lang: String): Resource<InsultDTO> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(changeInsultLanguage(lang))
                }.body()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(null)
        }
    }

    override suspend fun getGif(): Resource<GifDTO> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(GIFURL)
                }.body()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(null)
        }
    }
}