package com.setiadev.tango.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setiadev.tango.data.PlayerRepository
import com.setiadev.tango.ui.screen.detail.DetailViewModel
import com.setiadev.tango.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PlayerRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class not found: " + modelClass.name)
    }
}