package com.example.publicdomainfilms.model.getMetadata

import com.google.gson.annotations.SerializedName

data class GetMetadata(
    val created: Int,
    val d1: String,
    val d2: String,
    val dir: String,
    val files: List<File>,
    val server: String,
    @SerializedName("workable_servers")
    val workableServers: List<String>
)