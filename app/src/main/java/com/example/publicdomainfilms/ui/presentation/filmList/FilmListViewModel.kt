package com.example.publicdomainfilms.ui.presentation.filmList

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publicdomainfilms.data.Repository
import com.example.publicdomainfilms.model.getFilms.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var listOfFilms = mutableStateOf(listOf<Film>())

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        getFilms(genreIdentifier = "Comedy_Films")
    }

    fun getFilms(genreIdentifier: String) {
        viewModelScope.launch {

            _isLoading.value = true

            val filmListFlow =
                repository.getFilms(
                    genre = genreIdentifier,
                    page = 1,
                    sort = "avg_rating"
                )

            filmListFlow.catch { }.collect {
                listOfFilms.value = it?.response?.films ?: emptyList()
                _isLoading.value = false
            }
        }
    }
}