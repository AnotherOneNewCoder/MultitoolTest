package ru.zhogin.app.features

import ru.zhogin.app.data.DataRepository
import ru.zhogin.app.data.RequestResult
import ru.zhogin.app.data.map
import ru.zhogin.app.data.models.Gif
import ru.zhogin.app.data.models.Insult
import ru.zhogin.app.features.models.GifUI
import ru.zhogin.app.features.models.InsultUI
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val repository: DataRepository
) {
    internal suspend fun getRandomInsult(lang: String) : RequestResult<InsultUI> {
        return repository.getRandomInsult(lang).map { it.toInsultUI() }
    }

    internal suspend fun showAllInsultsFromDb() : RequestResult<List<InsultUI>> {
        return repository.getAllInsultsFromDatabase().map {
            listInsults ->
            listInsults.map { it.toInsultUI() }
        }
    }
    internal suspend fun getRandomGif() : RequestResult<GifUI> {
        return repository.getRandomGif().map { it.toGifUI() }
    }

    internal suspend fun saveInsultToDb(insultUI: InsultUI) {
        repository.saveInsultToDb(insultUI.toInsult())
    }
    internal suspend fun deleteInsultFromDb(insultUI: InsultUI) {
        repository.deleteInsultFromDb(insultUI.toInsult())
    }
}

private fun Insult.toInsultUI() : InsultUI {
    return InsultUI(
        comment = comment,
        created = created,
        createdby = createdby,
        insult = insult,
        language = language,
        number = number,
    )
}
private fun InsultUI.toInsult() : Insult {
    return Insult(
        comment = comment,
        created = created,
        createdby = createdby,
        insult = insult,
        language = language,
        number = number,
    )
}
private fun Gif.toGifUI() : GifUI {
    return GifUI(
        answer = answer,
        image = image
    )
}