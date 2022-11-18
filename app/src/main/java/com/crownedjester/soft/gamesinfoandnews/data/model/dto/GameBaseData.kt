package com.crownedjester.soft.gamesinfoandnews.data.model.dto

import com.google.gson.annotations.SerializedName

data class GameBaseData(
    val id: Int,
    val title: String,
    @SerializedName("thumbnail")
    val thumb: String,
    @SerializedName("short_description")
    val description: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("profile_url")
    val profileUrl: String
)
