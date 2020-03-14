package dev.jamile.marvelous.data.model


import com.google.gson.annotations.SerializedName

data class StoriesItems(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String
)