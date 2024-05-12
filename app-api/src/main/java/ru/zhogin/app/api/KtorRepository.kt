package ru.zhogin.app.api

import ru.zhogin.app.api.models.evilinsult.InsultDTO

interface KtorRepository {
    suspend fun getInsult() : Resource<InsultDTO>
}