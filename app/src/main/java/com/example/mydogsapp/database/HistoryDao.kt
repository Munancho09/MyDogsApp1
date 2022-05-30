package com.example.mydogsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mydogsapp.Model.HistoryItem

@Dao
interface HistoryDao {

    @Insert
    fun insertHistorySearch(historyItem: HistoryItem)

    @Query("SELECT * FROM HistoryItem")
    fun getAllHistory(): List<HistoryItem>

    @Query("DELETE FROM HistoryItem")
    fun clearHistory()
}