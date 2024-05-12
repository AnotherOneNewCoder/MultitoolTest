package ru.zhogin.app.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import ru.zhogin.app.api.models.evilinsult.InsultDTO
import ru.zhogin.app.api.network.INSULT_URL
import javax.inject.Inject

class KtorRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : KtorRepository {
    override suspend fun getInsult(): Resource<InsultDTO> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(INSULT_URL)
                }.body()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}