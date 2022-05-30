package com.example.mydogsapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.mydogsapp.database.DogsDB
import com.example.mydogsapp.database.HistoryDao

class App: Application() {

    companion object {
        private var DB: DogsDB? = null
        private var historyDao: HistoryDao? = null

        fun initDB(context: Context) {
            DB = Room.databaseBuilder(
                context,
                DogsDB::class.java,
                "dogs_database"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getHistoryDao(): HistoryDao? {
            if (historyDao == null) {
                return DB?.getHistoryDao()
            }
            return historyDao
        }
    }
}