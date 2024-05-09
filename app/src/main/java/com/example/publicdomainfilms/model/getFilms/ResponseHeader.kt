package com.example.publicdomainfilms.model.getFilms

data class ResponseHeader(
    val QTime: Int,
    val params: Params,
    val status: Int
)