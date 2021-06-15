package com.example.paging3

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.network.CharacterData
import com.example.paging3.network.RetroService

class CharacterPagingSource(val apiService: RetroService):  PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage : Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromAPI(nextPage)
            var nextPageNumber: Int? = null
            if (response.info.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toIntOrNull()
            }
            LoadResult.Page(data = response.results,
            prevKey = null,
            nextKey = nextPageNumber)
        } catch (err : Exception) {
            LoadResult.Error(err)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}