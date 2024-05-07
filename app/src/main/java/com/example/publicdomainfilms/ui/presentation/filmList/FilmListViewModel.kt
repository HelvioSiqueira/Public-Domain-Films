package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publicdomainfilms.data.Repository
import com.example.publicdomainfilms.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var listOfFilms = listOf<Film>()
    val filmListState = mutableStateOf(FilmListState(filmList = emptyList()))

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
                listOfFilms = it?.response?.films ?: emptyList()
                filmListState.value = filmListState.value.copy(filmList = listOfFilms)
            }
        }
    }
}