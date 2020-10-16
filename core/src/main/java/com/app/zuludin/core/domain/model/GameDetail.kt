package com.app.zuludin.core.domain.model

data class GameDetail(
    val id: Int? = null,
    val rating: Double? = null,
    val released: String? = null,
    val backgroundImage: String? = null,
    val name: String? = null,
    val description: String? = null,
    val genres: List<String>? = null,
    val publisher: String? = null,
    val stores: List<GameStore>? = null,
    val gameClip: String? = null,
    val clipPreview: String? = null,
    val website: String? = null,
    val ageRating: String? = null,
    val developer: String? = null,
    val platform: String? = null
)