package com.example.publicdomainfilms.model

data class Response(
    val films: List<Film>,
    val numFound: Int,
    val start: Int
)