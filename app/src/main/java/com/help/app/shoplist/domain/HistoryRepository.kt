package com.help.app.shoplist.domain

import com.help.app.shoplist.domain.model.HistoryItemInfo
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getAllHistoryItems(): Flow<List<HistoryItemInfo>>
    suspend fun addHistoryItem(item: HistoryItemInfo)
}