package com.example.mydogsapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
//import kotlinx.android.synthetic.main.dogs_rv_layout.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.mydogsapp.Model.DogsApi
import com.example.mydogsapp.Model.HistoryItem
import com.example.mydogsapp.R
import com.squareup.picasso.Picasso
import java.util.concurrent.locks.Lock

class HistoryAdapter (val context: Context?, private val historySearches: MutableList<HistoryItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("BRUH", "$historySearches")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = historySearches[position]
        (holder as ViewHolder).apply {
            historySearchText?.text = item.searchedString
            historyResultText?.text = item.result
            if (item.result == "Ошибка") {
                historyResultText?.setTextColor(ContextCompat.getColor(context!!, R.color.red))
            } else {
                historyResultText?.setTextColor(ContextCompat.getColor(context!!, R.color.green))
            }
        }
    }

    override fun getItemCount(): Int {
        return historySearches.size
    }

    fun clearData() {
        historySearches.clear()
    }

    class ViewHolder(private val v: View?) : RecyclerView.ViewHolder(v!!){

        val historySearchText: TextView? = v?.findViewById(R.id.item_history_search)
        val historyResultText: TextView? = v?.findViewById(R.id.item_history_result)
    }
}