package com.app.zuludin.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameStoreResponse(

	@field:SerializedName("next")
	val next: Any? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("seo_description")
	val seoDescription: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("seo_h1")
	val seoH1: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsStore>? = null,

	@field:SerializedName("seo_title")
	val seoTitle: String? = null
)

data class ResultsStore(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("domain")
	val domain: String? = null,

	@field:SerializedName("following")
	val following: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
