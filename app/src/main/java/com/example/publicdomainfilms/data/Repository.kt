package com.example.publicdomainfilms.data

import com.example.publicdomainfilms.data.remote.InternetArchiveApi
import com.example.publicdomainfilms.model.GetFilms
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class Repository(private val internetArchiveApi: InternetArchiveApi) {

    suspend fun getFilms(
        genre: String,
        page: Int,
        sort: String
    ): Flow<GetFilms?> = flow {
        var getFilms: GetFilms? = null

        try {
            val response = internetArchiveApi.getFilms(
                query = "collection:$genre",
                fields = "identifier,downloads,year,avg_rating,title,description,num_reviews,creator",
                rows = 50,
                page = page,
                sort = "$sort desc",
            )

            if (response.isSuccessful) getFilms = response.body()

        } catch (e: Exception) {
            Timber.d(e)
        }

        emit(getFilms)
    }
}