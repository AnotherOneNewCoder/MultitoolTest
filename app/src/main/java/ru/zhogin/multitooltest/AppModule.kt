package ru.zhogin.multitooltest

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ru.zhogin.app.api.KtorRepository
import ru.zhogin.app.api.KtorRepositoryImpl
import ru.zhogin.app.api.network.AppHttpClient
import ru.zhogin.database.AppDataBase
import ru.zhogin.database.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesHttpClient(httpClient: AppHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    @Singleton
    fun providesKtorRepository(impl: KtorRepositoryImpl) : KtorRepository = impl

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDataBase(context)
    }
}