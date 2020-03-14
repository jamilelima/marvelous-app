package dev.jamile.marvelous.data.model


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: String,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<StoriesItems>,
    @SerializedName("returned")
    val returned: String
)