package com.example.mydogsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.example.mydogsapp.Adapter.DogsAdapter
import com.example.mydogsapp.Model.DogsApi
import com.example.mydogsapp.Model.HistoryItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<DogsApi>()
    private lateinit var dogsRV: RecyclerView
    private lateinit var dogNameText: EditText
    private lateinit var searchBtn: FloatingActionButton
    private lateinit var btnHistory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.initDB(applicationContext)

        dogsRV = findViewById(R.id.dogsRecyclerView)
        dogNameText = findViewById(R.id.dogsNameET)
        searchBtn = findViewById(R.id.searchBtn)
        btnHistory = findViewById(R.id.btn_history)

        dogsRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        searchBtn.setOnClickListener {
            var name = dogNameText.text.toString()

            searchDogs(name)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun searchDogs(name: String) {
        imageList.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    for (i in 0 until image.length()) {
                        val list = image.get(i)
                        imageList.add(
                            DogsApi(list.toString())
                        )
                    }
                    App.getHistoryDao()?.insertHistorySearch(
                        HistoryItem(
                            searchedString = dogNameText.text.toString(),
                            result = "Успешно"
                        )
                    )
                    dogsRV.adapter = DogsAdapter(this@MainActivity, imageList)
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@MainActivity, "Произошла ошибка", Toast.LENGTH_SHORT).show()
                    App.getHistoryDao()?.insertHistorySearch(
                        HistoryItem(
                            searchedString = dogNameText.text.toString(),
                            result = "Ошибка"
                        )
                    )
                }
            })
    }
}