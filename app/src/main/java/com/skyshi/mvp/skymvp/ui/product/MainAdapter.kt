package com.skyshi.mvp.skymvp.ui.product

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.skyshi.mvp.skymvp.R
import com.skyshi.skymvp.SkyAdapter
import com.skyshi.skymvp.SkyViewHolder
import com.skyshi.mvp.skymvp.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class MainAdapter(val context: Context) : SkyAdapter<Product, SkyViewHolder<Product>>() {
    override fun getItemResourceLayout(viewType: Int): Int {
        return R.layout.item_product
    }

    override fun getItemViewTypeCustomMessage(data: Product, position: Int): Int {
        return super.superGetItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkyViewHolder<Product> {
        val view = getView(parent, viewType)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : SkyViewHolder<Product>(itemView, itemClickListener) {
        override fun bind(data: Product) {
            itemView.tv_name.text = data.name
            itemView.tv_price.text = "Rp ${data.price}"
            itemView.tv_seller.text = data.seller_name

            if (data.images.isNotEmpty()) {
                Glide.with(context).load(data.images[0])
                        .into(itemView.imageView)
            }

            itemView.setOnClickListener({
                onClick(itemView)
            })
        }

    }

}