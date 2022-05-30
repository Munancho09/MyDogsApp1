package com.example.mydogsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.mydogsapp.Adapter.HistoryAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HistoryActivity : AppCompatActivity() {

    private lateinit var rcv: RecyclerView
    private lateinit var btnBack: FloatingActionButton
    private lateinit var btnClearHistory: Button

    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        init()
    }

    private fun init() {
        rcv = findViewById(R.id.rcv_history)
        btnBack = findViewById(R.id.btn_history_back)
        btnClearHistory = findViewById(R.id.btn_clear_history)

        val historyDao = App.getHistoryDao()
        if (historyDao != null) {
            adapter = HistoryAdapter(context = this, historySearches = historyDao.getAllHistory().toMutableList())
            rcv.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnClearHistory.setOnClickListener {
            historyDao?.clearHistory()
            adapter.clearData()
            adapter.notifyDataSetChanged()
        }
    }
}