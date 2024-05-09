package com.example.publicdomainfilms.data.remote

import com.example.publicdomainfilms.model.getFilms.GetFilms
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InternetArchiveApi {

    @GET("advancedsearch.php?output=json")
    suspend fun getFilms(
        @Query("q") query: String,
        @Query("fl") fields: String,
        @Query("rows") rows: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String,
    ): Response<GetFilms>

}