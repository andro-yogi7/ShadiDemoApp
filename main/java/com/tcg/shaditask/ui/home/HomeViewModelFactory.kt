package com.tcg.shaditask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tcg.shaditask.data.repositories.DBModelRepository

class HomeViewModelFactory(
    private val repository: DBModelRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}