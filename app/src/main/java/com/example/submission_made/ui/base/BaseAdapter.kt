package com.example.submission_made.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder, D> : RecyclerView.Adapter<T>() {

    abstract fun setData(data: ArrayList<D>)
}