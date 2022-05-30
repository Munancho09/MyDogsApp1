package com.example.mydogsapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val searchedString: String,
    val result: String
)