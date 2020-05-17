package dev.jamile.marvelous.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: String,
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("results")
    val results: List<HeroResult>,
    @SerializedName("total")
    val total: String
)