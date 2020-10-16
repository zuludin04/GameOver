package com.app.zuludin.core.domain.usecase

import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.domain.repository.IGameRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameInteractorTest {

    private lateinit var useCase: GameInteractor

    @Mock
    private lateinit var repository: IGameRepository

    @Before
    fun setup() {
        useCase = GameInteractor(repository)
    }

    @Test
    fun getGameListTest() {
        useCase.getGameList(true)

        verify(repository).getGameList(true)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getPlatformGameListTest() {
        useCase.loadPlatformsList(true)

        verify(repository).getPlatformList(true)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getStoreGameListTest() {
        useCase.loadStoreList(true)

        verify(repository).getStoreList(true)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getGamesByPlatformTest() {
        useCase.loadGamesByPlatform(1)

        verify(repository).getGamesByPlatform(1)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getGamesByStoreTest() {
        useCase.loadGamesByStore(1)

        verify(repository).getGamesByStore(1)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getGameDetailTest() {
        useCase.loadGameDetail(1)

        verify(repository).getGameDetail(1)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun addFavoriteGameTest() {
        val detail = GameDetail()

        useCase.addGameFavorite(detail)

        verify(repository).addFavoriteGame(detail)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getFavoriteGameTest() {
        useCase.loadFavoriteGame()

        verify(repository).getFavoriteGame()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun deleteFavoriteGameTest() {
        useCase.deleteFavoriteGame(1)

        verify(repository).deleteFavoriteGame(1)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun checkFavoriteGameTest() {
        useCase.isGameFavorite(1)

        verify(repository).checkGameFavorite(1)
        verifyNoMoreInteractions(repository)
    }
}