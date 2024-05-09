package com.example.publicdomainfilms.model.getFilms

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("docs")
    val films: List<Film>,
    val numFound: Int,
    val start: Int
)