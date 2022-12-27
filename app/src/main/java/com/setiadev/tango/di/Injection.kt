package com.setiadev.tango.di

import com.setiadev.tango.data.PlayerRepository

object Injection {
    fun provideRepository(): PlayerRepository {
        return PlayerRepository.getInstance()
    }
}