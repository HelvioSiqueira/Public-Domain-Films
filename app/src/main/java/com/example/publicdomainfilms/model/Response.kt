package com.example.publicdomainfilms.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("docs")
    val films: List<Film>,
    val numFound: Int,
    val start: Int
)