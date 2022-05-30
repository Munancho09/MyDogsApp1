package com.example.mydogsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydogsapp.Model.HistoryItem

@Database(entities = [HistoryItem::class], version = 1)
abstract class DogsDB: RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}