package com.example.publicdomainfilms.ui.presentation.filmDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publicdomainfilms.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val filmUrl = mutableStateOf("")

    fun getFilmUrl(identifier: String) {
        viewModelScope.launch {
            val stringBuilder = StringBuilder()

            val getMetadataFlow = repository.getMetadata(identifier)

            getMetadataFlow.collect {
                if (it != null) {
                    val getMetadata = it

                    val filmMp4 = getMetadata.files.find { file ->
                        file.name.contains(".mp4")
                    }

                    try {
                        stringBuilder.append("https://")
                        stringBuilder.append(getMetadata.workableServers.first())
                        stringBuilder.append(getMetadata.dir)
                        stringBuilder.append("/")
                        stringBuilder.append(filmMp4!!.name)
                    } catch (e: Exception) {
                        stringBuilder.append("https://ia902706.us.archive.org/1/items/ShrimpsForADay1934/Produce_50_512kb.mp4")
                    }
                    Timber.d(stringBuilder.toString())
                    filmUrl.value = stringBuilder.toString()
                }
            }
        }
    }
}