package com.example.publicdomainfilms.model

data class ResponseHeader(
    val QTime: Int,
    val params: Params,
    val status: Int
)