package com.example.publicdomainfilms.model

data class Params(
    val fields: String,
    val qin: String,
    val query: String,
    val rows: String,
    val sort: String,
    val start: Int,
    val wt: String
)