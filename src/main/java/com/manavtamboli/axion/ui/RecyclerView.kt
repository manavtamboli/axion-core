package com.manavtamboli.axion.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, adapter : RecyclerView.Adapter<*>){
    recyclerView.adapter = adapter
}