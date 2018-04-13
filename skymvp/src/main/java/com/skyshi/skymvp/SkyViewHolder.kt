package com.skyshi.skymvp

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by yusufaw on 3/27/18.
 */

abstract class SkyViewHolder<in T>(itemView: View, private val onClickListener: SkyAdapter.OnItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    abstract fun bind(data: T)

    override fun onClick(v: View) {
        onClickListener?.onItemClick(v, adapterPosition)
    }
}