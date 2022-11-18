package com.crownedjester.soft.gamesinfoandnews.data.model.dto

import com.google.gson.annotations.SerializedName

data class GameNews(
    val id: Int,
    val title: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("thumbnail")
    val thumb: String,
    @SerializedName("main_image")
    val originImage: String,
    @SerializedName("article_content")
    val articleContent: String,
    @SerializedName("article_url")
    val url: String
)
