package ru.zhogin.app.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.api.KtorRepository
import ru.zhogin.app.api.map
import ru.zhogin.app.data.models.Gif
import ru.zhogin.app.data.models.Insult
import ru.zhogin.database.AppDatabase
import ru.zhogin.database.insult.InsultDBO
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val database: AppDatabase,
    private val api: KtorRepository
) {
    suspend fun getRandomInsult(lang: String) : RequestResult<Insult> {
        return api.getInsult(lang).map {
            insultDTO ->
            insultDTO.toInsult()
        }.toRequestResult()
    }

    suspend fun getRandomGif() : RequestResult<Gif> {
        return api.getGif().map {
            gifDTO ->
            gifDTO.toGif()
        }.toRequestResult()
    }

//    fun getAllInsultsFromDatabase() : Flow<RequestResult<List<Insult>>> {
//        return database.insultsDao.observeAllInsults().map<List<InsultDBO>, RequestResult<List<InsultDBO>>> { RequestResult.Success(it) }.map {
//            result ->
//            result.map {
//                listDbos ->
//                listDbos.map {
//                    it.toInsult()
//                }
//            }
//        }
//    }

    suspend fun getAllInsultsFromDatabase() : RequestResult<List<Insult>> {
        val db = database.insultsDao.getAllInsults()
        return RequestResult.Success(db).map {
            insultDbos ->
            insultDbos.map {
                it.toInsult()
            }
        }

    }

    suspend fun saveInsultToDb(insult: Insult) {
        val dbo = insult.toInsultDBO()
        database.insultsDao.insertInsult(dbo)
    }

    suspend fun deleteInsultFromDb(insult: Insult) {
        val dbo = insult.toInsultDBO()
        database.insultsDao.deleteInsult(dbo)
    }

    suspend fun clearInsultsDb() {
        database.insultsDao.clearAllInsults()
    }

}