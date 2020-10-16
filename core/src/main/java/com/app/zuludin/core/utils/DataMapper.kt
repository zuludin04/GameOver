package com.app.zuludin.core.utils

import com.app.zuludin.core.data.source.local.entity.FavoriteEntity
import com.app.zuludin.core.data.source.local.entity.GameEntity
import com.app.zuludin.core.data.source.local.entity.PlatformEntity
import com.app.zuludin.core.data.source.local.entity.StoreEntity
import com.app.zuludin.core.data.source.remote.response.GameDetailResponse
import com.app.zuludin.core.data.source.remote.response.ResultsItem
import com.app.zuludin.core.data.source.remote.response.ResultsPlatform
import com.app.zuludin.core.data.source.remote.response.ResultsStore
import com.app.zuludin.core.domain.model.*

object DataMapper {
    fun mapGameResponsesToEntities(input: List<ResultsItem>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id!!,
                name = it.name.toString(),
                rating = it.rating!!,
                playtime = it.playtime!!,
                released = it.released.toString(),
                backgroundImage = it.backgroundImage.toString()
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapGameEntitiesToDomains(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.gameId,
                name = it.name,
                rating = it.rating,
                released = it.released,
                backgroundImage = it.backgroundImage,
                playtime = it.playtime,
            )
        }

    fun mapGameResponsesToDomains(input: List<ResultsItem>): List<Game> {
        val gameList = ArrayList<Game>()
        input.map {
            val game = Game(
                id = it.id!!,
                name = it.name.toString(),
                rating = it.rating!!,
                playtime = it.playtime!!,
                released = it.released.toString(),
                backgroundImage = it.backgroundImage.toString()
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapPlatformResponsesToEntities(input: List<ResultsPlatform>): List<PlatformEntity> {
        val platforms = ArrayList<PlatformEntity>()
        input.map {
            val platform = PlatformEntity(
                id = it.id,
                name = it.name,
                image = it.imageBackground,
                count = it.gamesCount
            )
            platforms.add(platform)
        }
        return platforms
    }

    fun mapPlatformEntitiesToDomains(input: List<PlatformEntity>): List<Platform> =
        input.map {
            Platform(
                id = it.id,
                name = it.name,
                image = it.image,
                count = it.count
            )
        }

    fun mapStoreResponsesToEntities(input: List<ResultsStore>): List<StoreEntity> {
        val platforms = ArrayList<StoreEntity>()
        input.map {
            val platform = StoreEntity(
                id = it.id,
                name = it.name,
                image = it.imageBackground,
                count = it.gamesCount
            )
            platforms.add(platform)
        }
        return platforms
    }

    fun mapStoreEntitiesToDomains(input: List<StoreEntity>): List<Store> =
        input.map {
            Store(
                id = it.id,
                name = it.name,
                image = it.image,
                count = it.count
            )
        }

    fun mapGameDetailResponseToDomain(input: GameDetailResponse): GameDetail {
        val genres = ArrayList<String>()
        val stores = ArrayList<GameStore>()
        val platforms = ArrayList<String>()
        val developers = ArrayList<String>()

        input.genres?.map {
            genres.add(it.name.toString())
        }

        input.stores?.map {
            val store = GameStore(
                name = it.store?.name,
                image = it.store?.imageBackground,
                website = it.url
            )
            stores.add(store)
        }

        input.platforms?.map {
            platforms.add(it.platform?.name.toString())
        }

        input.developers?.map {
            developers.add(it.name.toString())
        }

        return GameDetail(
            id = input.id,
            rating = input.rating,
            released = input.released,
            backgroundImage = input.backgroundImage,
            name = input.name,
            description = input.description,
            genres = genres,
            publisher = input.publishers?.get(0)?.name,
            stores = stores,
            gameClip = input.clip?.clip,
            clipPreview = input.clip?.preview,
            website = input.website,
            ageRating = input.esrbRating?.name,
            developer = developers.toString(),
            platform = platforms.toString()
        )
    }

    fun mapFavoriteEntityToGameDomain(input: List<FavoriteEntity>): List<Game> {
        val games = ArrayList<Game>()

        input.map {
            val favorite = Game(
                id = it.gameId,
                name = it.name,
                rating = it.rating,
                released = it.released,
                backgroundImage = it.backgroundImage
            )
            games.add(favorite)
        }

        return games
    }

    fun mapDetailDomainToFavoriteEntity(input: GameDetail): FavoriteEntity {
        return FavoriteEntity(
            gameId = input.id!!,
            name = input.name.toString(),
            released = input.released.toString(),
            rating = input.rating!!,
            backgroundImage = input.backgroundImage.toString()
        )
    }
}