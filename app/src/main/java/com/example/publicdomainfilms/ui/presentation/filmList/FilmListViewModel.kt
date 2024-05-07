package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publicdomainfilms.data.Repository
import com.example.publicdomainfilms.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

     var listOfFilms = mutableStateOf(listOf<Film>())

    init {
        getFilms(genreIdentifier = "Comedy_Films")
    }

    fun getFilms(genreIdentifier: String) {
        viewModelScope.launch {

            val filmListFlow =
                repository.getFilms(
                    genre = genreIdentifier,
                    page = 1,
                    sort = "avg_rating"
                )

            filmListFlow.catch { }.collect {
                listOfFilms.value = it?.response?.films ?: emptyList()
            }
        }
    }
}