package com.iqbaltio.jetpacksubmission.di

import com.iqbaltio.jetpacksubmission.data.GamesRepository

object Injection {
    fun provideRepository(): GamesRepository {
        return GamesRepository.getInstance()
    }
}