package com.example.publicdomainfilms.routes

import kotlinx.serialization.Serializable

sealed class NavPages {
    @Serializable
    object FilmListPage

    @Serializable
    data class FilmDetailsPage(
        val identifier: String,
        val title: String,
        val creator: String,
        val avgRating: Float,
        val downloads: Int,
        val description: String,
        val year: Int = 1900,
    )

    @Serializable
    data class PlayerPage(
        val contentUriReceiver: String = "",
        val filmName: String = ""
    )

}