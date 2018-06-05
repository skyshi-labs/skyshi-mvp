package com.skyshi.mvp.skymvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.skyshi.mvp.skymvp.base.BaseApp
import com.skyshi.mvp.skymvp.service.Service
import com.skyshi.mvp.skymvp.ui.product.ListProductPresenter
import com.skyshi.mvp.skymvp.ui.product.MainAdapter
import com.skyshi.skymvp.SkyAdapter
import com.skyshi.skymvp.SkyRecyclerListener
import com.skyshi.mvp.skymvp.model.ListProductResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseApp(), ListProductPresenter.ListProductView {
    override fun onListProductSuccess(listProductResponse: ListProductResponse) {
        adapter.addOrUpdate(listProductResponse.products)
    }

    override fun onFailure(appErrorMessage: String) {

    }

    override fun removeWait() {

    }

    override fun showWait() {

    }

    @Inject
    lateinit var service: Service
    @Inject
    lateinit var presenter: ListProductPresenter
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deps.inject(this)
        presenter.attachView(this)

        adapter = MainAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recycler_view.addOnScrollListener(object : SkyRecyclerListener(recycler_view.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                presenter.getLisProduct(currentPage + 1, "")
            }
        })


        presenter.getLisProduct(1, "")

        adapter.itemClickListener = (object : SkyAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "Product " + adapter.data[position].name, Toast.LENGTH_LONG).show()
            }
        })
    }
}
