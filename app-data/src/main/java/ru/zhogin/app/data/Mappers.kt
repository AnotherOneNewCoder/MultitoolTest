package ru.zhogin.app.data

import ru.zhogin.app.api.gif.GifDTO
import ru.zhogin.app.api.models.evilinsult.InsultDTO
import ru.zhogin.app.data.models.Gif
import ru.zhogin.app.data.models.Insult
import ru.zhogin.database.insult.InsultDBO

internal fun InsultDBO.toInsult() : Insult {
    return Insult(
        comment = comment,
        created = created,
        createdby = createdby,
        insult = insult,
        language = language,
        number = number.toString(),
    )
}

internal fun InsultDTO.toInsult() : Insult {
    return Insult(
        comment = comment,
        created = created,
        createdby = createdby,
        insult = insult,
        language = language,
        number = number,
    )
}

internal fun Insult.toInsultDBO() : InsultDBO {
    return InsultDBO(
        comment = comment,
        created = created,
        createdby = createdby,
        insult = insult,
        language = language,
        number = number.toLong(),
    )
}

internal fun GifDTO.toGif() : Gif {
    return Gif(
        answer = answer,
        image = image,
    )
}