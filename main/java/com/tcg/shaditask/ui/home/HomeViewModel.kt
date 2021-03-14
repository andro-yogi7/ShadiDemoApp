package com.tcg.shaditask.ui.home

import androidx.lifecycle.ViewModel
import com.tcg.garageapplication.util.lazyDeferred
import com.tcg.shaditask.data.db.entities.DBModel
import com.tcg.shaditask.data.repositories.DBModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repository: DBModelRepository
) : ViewModel(){

    suspend fun getAllData() = withContext(Dispatchers.IO){
        repository.getAllData()
    }

    suspend fun updateDBModel(dbModel: DBModel) = withContext(Dispatchers.IO) {
        repository.updateDBModel(dbModel)
    }

    val dbList by lazyDeferred {
        repository.getDBList()
    }
}