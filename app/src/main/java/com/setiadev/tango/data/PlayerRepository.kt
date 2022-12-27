package com.setiadev.tango.data

import com.setiadev.tango.model.PlayerDataSource
import com.setiadev.tango.model.PlayerList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlayerRepository {

    private val playerList = mutableListOf<PlayerList>()

    init {
        if (playerList.isEmpty()) {
            PlayerDataSource.dummyPlayer.forEach {
                playerList.add(PlayerList(it))
            }
        }
    }

    fun getAllPlayers(): Flow<List<PlayerList>> {
        return flowOf(playerList)
    }

    fun getPlayer(playerId: Long): PlayerList {
        return playerList.first {
            it.player.id == playerId
        }
    }

    companion object {
        @Volatile
        private var instance: PlayerRepository? = null

        fun getInstance(): PlayerRepository =
            instance ?: synchronized(this) {
                PlayerRepository().apply {
                    instance = this
                }
            }
    }
}