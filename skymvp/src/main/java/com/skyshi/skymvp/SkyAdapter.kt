package com.skyshi.skymvp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by yusufaw on 3/27/18.
 */

abstract class SkyAdapter<T, Holder : SkyViewHolder<T>> : RecyclerView.Adapter<Holder>() {

    var itemClickListener: OnItemClickListener? = null

    var data: MutableList<T> = ArrayList()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    abstract fun getItemResourceLayout(viewType: Int): Int
    abstract fun getItemViewTypeCustomMessage(data: T, position: Int): Int


    fun getView(parent: ViewGroup?, viewType: Int): View = LayoutInflater.from(parent?.context).inflate(getItemResourceLayout(viewType), parent, false)


    fun add(item: T) = data.add(item)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(data[position])

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun add(items: List<T>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: T) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<T>) {
        val size = items.size
        for (i in 0 until size) {
            val item = items[i]
            val x = data.indexOf(item)
            if (x >= 0) {
                data[x] = item
            } else {
                data.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun remove(item: T) {
        val position = data.indexOf(item)
        Log.d("xxx", "position : " + position)
        remove(position)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeCustomMessage(data[position], position)
    }

    fun superGetItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}