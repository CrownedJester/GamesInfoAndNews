package com.crownedjester.soft.gamesinfoandnews.data.model.dto

import com.google.gson.annotations.SerializedName

data class Giveaway(
    val id: Int,
    val title: String,
    @SerializedName("keys_left")
    val keysLeft: String,
    @SerializedName("thumbnail")
    val thumb: String,
    @SerializedName("main_page")
    val mainPage: String,
    @SerializedName("short_description")
    val description: String,
    @SerializedName("giveaway_url")
    val giveawayUrl: String
)
