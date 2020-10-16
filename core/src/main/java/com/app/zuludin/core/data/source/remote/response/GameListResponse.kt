package com.app.zuludin.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameListResponse(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("user_platforms")
	val userPlatforms: Boolean? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null
)

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ResultsItem(

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("playtime")
	val playtime: Int? = null,

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("rating_top")
	val ratingTop: Int? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("saturated_color")
	val saturatedColor: String? = null,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("stores")
	val stores: List<StoresItem?>? = null,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("tba")
	val tba: Boolean? = null,

	@field:SerializedName("dominant_color")
	val dominantColor: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("clip")
	val clip: Clip? = null,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int? = null
)
