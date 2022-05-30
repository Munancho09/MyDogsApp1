package com.example.mydogsapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
//import kotlinx.android.synthetic.main.dogs_rv_layout.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.mydogsapp.Model.DogsApi
import com.example.mydogsapp.R
import com.squareup.picasso.Picasso
import java.util.concurrent.locks.Lock

class DogsAdapter (val context: Context?, private val dogsImages: ArrayList<DogsApi>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(dogsImages[position].message).into((holder as ViewHolder).dogImage)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

    class ViewHolder(private val v: View?) : RecyclerView.ViewHolder(v!!){

        val dogImage:ImageView? = v?.findViewById(R.id.dogImage)
    }
}