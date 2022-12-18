package com.crownedjester.soft.gamesinfoandnews.data.model.dto

import com.google.gson.annotations.SerializedName

data class GameFullData(
    val id: Int,
    val title: String,
    @SerializedName("thumbnail")
    val thumb: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("full_description")
    val fullDescription: String,
    @SerializedName("minimum_system_requirements")
    val requirements: GameRequirements,
    val screenshots: List<Screenshot>

) {

    companion object {
        fun setupLogoWithScreenshots(container: List<Screenshot>, value: Screenshot) =
            with(mutableListOf<Screenshot>()) {

                add(value)

                container.forEach {
                    add(it)
                }

                toList()
            }

    }

}

