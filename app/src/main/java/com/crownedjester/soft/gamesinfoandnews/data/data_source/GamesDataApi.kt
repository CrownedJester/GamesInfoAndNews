package com.crownedjester.soft.gamesinfoandnews.data.data_source

import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameNews
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Giveaway
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GamesDataApi {

    companion object {
        const val BASE_URL = "https://www.mmobomb.com/api1/"
        val PLATFORMS_LIST = listOf("pc", "all", "browser")
        val TAGS_LIST = ("mmorpg, shooter, strategy, moba, racing, sports, social, sandbox, " +
                "open-world, survival, pvp, pve, pixel, voxel, zombie, turn-based, first-person, " +
                "third-Person, top-down, tank, space, sailing, side-scroller, superhero, permadeath, " +
                "card, battle-royale, mmo, mmofps, mmotps, 3d, 2d, anime, fantasy, sci-fi, fighting, " +
                "action-rpg, action, military, martial-arts, flight, low-spec, tower-defense, horror, mmorts")
            .split(", ")
        val SORT_TYPES = listOf("popularity, alphabetical, relevance, release-date")
    }

    @GET("games")
    suspend fun retrieveGamesBaseData(
        @Query("platform") platform: String = PLATFORMS_LIST.first { it == "all" },
        @Query("category") category: String = "", //tag
        @Query("sort-by") sortBy: String = SORT_TYPES.first { it == "alphabetical" },
    ): List<GameBaseData>

    @GET("filter")
    suspend fun retrieveGamesBaseDataByFilter(
        @Query("tag") tag: String = "mmorpg.pvp",
        @Query("platform") platform: String = PLATFORMS_LIST.first { it == "all" }, //tag
        @Query("sort-by") sortBy: String = SORT_TYPES.first { it == "alphabetical" }
    ): List<GameBaseData>

    @GET("game")
    suspend fun getGameFullDataById(
        @Query("id") id: Int
    ): GameFullData

    @GET("latestnews")
    suspend fun retrieveLatestNews(): List<GameNews>

    @GET("giveaways")
    suspend fun retrieveGiveaways(): List<Giveaway>

}


fun createClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}
