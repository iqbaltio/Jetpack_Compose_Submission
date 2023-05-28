package com.iqbaltio.jetpacksubmission.data

import com.iqbaltio.jetpacksubmission.model.ListGame
import com.iqbaltio.jetpacksubmission.model.GamesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GamesRepository {

    private val listGame = mutableListOf<ListGame>()

    init {
        if (listGame.isEmpty()) {
            GamesData.games.forEach {
                listGame.add(ListGame(it))
            }
        }
    }

    fun getAllGames(): Flow<List<ListGame>> {
        return flowOf(listGame)
    }

    fun getFavoriteGameById(gameId: Long): ListGame {
        return listGame.first {
            it.game.id == gameId
        }
    }

    companion object {
        @Volatile
        private var instance: GamesRepository? = null

        fun getInstance(): GamesRepository = instance ?: synchronized(this) {
            GamesRepository().apply {
                instance = this
            }
        }
    }
}