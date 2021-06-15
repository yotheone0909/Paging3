package com.example.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.network.CharacterData
import com.example.paging3.network.RetroInstance
import com.example.paging3.network.RetroService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel : ViewModel() {
    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 34),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow
            .cachedIn(viewModelScope)
    }
}