package ru.zhogin.app.features

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.zhogin.app.data.RequestResult
import ru.zhogin.app.features.models.GifUI
import ru.zhogin.app.features.models.InsultUI
import javax.inject.Inject

@HiltViewModel
class InsultsViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase
) : ViewModel() {
    private val _randomInsult = MutableStateFlow<RequestResult<InsultUI>?>(null)
    val randomInsult : StateFlow<RequestResult<InsultUI>?> = _randomInsult

    private val _randomGif = MutableStateFlow<RequestResult<GifUI>?>(null)
    val randomGifUI : StateFlow<RequestResult<GifUI>?> = _randomGif

    private val _insultsFromDb = MutableStateFlow<RequestResult<List<InsultUI>>?>(null)
    val insultsFromDb : StateFlow<RequestResult<List<InsultUI>>?> = _insultsFromDb

    val lang = mutableStateOf("en")

    init {
        getRandomInsult()
        getAllInsultsFromDb()
        getRandomGifUI()
    }

    fun getRandomInsult() {
        viewModelScope.launch {
            _randomInsult.value = getAllUseCase.getRandomInsult(lang.value)
        }
    }

    fun getRandomGifUI() {
        viewModelScope.launch {
            _randomGif.value = getAllUseCase.getRandomGif()
        }
    }

    private fun getAllInsultsFromDb() {
        viewModelScope.launch {
            _insultsFromDb.value = getAllUseCase.showAllInsultsFromDb()
        }
    }
    suspend fun addInsultUIToDb(insultUI: InsultUI) {
        getAllUseCase.saveInsultToDb(insultUI)
        getAllInsultsFromDb()
    }

    suspend fun deleteInsultFromDb(insult: InsultUI) {
        getAllUseCase.deleteInsultFromDb(insult)
        getAllInsultsFromDb()
    }


}