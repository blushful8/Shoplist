package com.help.app.shoplist.data.repository

import com.help.app.shoplist.data.database.HistoryItemDao
import com.help.app.shoplist.data.mapper.HistoryItemMapper
import com.help.app.shoplist.domain.HistoryRepository
import com.help.app.shoplist.domain.model.HistoryItemInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyItemDao: HistoryItemDao,
    private val mapper: HistoryItemMapper
) : HistoryRepository {
    override fun getAllHistoryItems(): Flow<List<HistoryItemInfo>> = historyItemDao.getAll()
        .map { list -> list.map { mapper.mapDatabaseModelToDomain(it) } }

    override suspend fun addHistoryItem(item: HistoryItemInfo) =
        historyItemDao.insert(mapper.mapDomainModelToDatabase(item))
}