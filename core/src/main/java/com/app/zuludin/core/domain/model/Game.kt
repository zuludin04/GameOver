package com.app.zuludin.core.domain.model

data class Game(
    val rating: Double? = null,

    val playtime: Int? = null,

    val id: Int? = null,

    val released: String? = null,

    val backgroundImage: String? = null,

    val name: String? = null,

    val isFavorite: Boolean = false
)