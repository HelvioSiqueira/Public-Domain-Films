package com.example.publicdomainfilms.model.getFilms

import com.google.gson.annotations.SerializedName

data class Film(

    @SerializedName("avg_rating")
    val avgRating: Double,
    val description: Any,
    val downloads: Int,
    val identifier: String,
    @SerializedName("num_reviews")
    val numReviews: Int,
    val title: String,
    val year: Int?,
    val creator: String?
)