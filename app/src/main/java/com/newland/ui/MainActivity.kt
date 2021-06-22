package com.newland.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.newland.ui.adapter.MenuAdapter
import com.newland.ui.recycle.CenterItemDecoration
import com.newland.ui.recycle.CenterLayoutManager
import com.newland.ui.recycle.CenterRecyclerView

class MainActivity : AppCompatActivity() {
    private val centerRecyclerView: CenterRecyclerView by lazy { findViewById(R.id.centerRecyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datas = mutableListOf<String>("攝影", "慢動作", "視頻", "照片", "正方形", "全景")
        var centerLayoutManager = CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var centerItemDecoration = CenterItemDecoration()
        var adapter = MenuAdapter(datas)
        adapter.setOnItemClickListener(object :OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                centerRecyclerView.smoothScrollToPosition(position)
            }
        })
        centerRecyclerView.mOnTargetItemListener= object : CenterRecyclerView.OnTargetItemListener {
            override fun onTargetItem(position: Int, prePosition: Int) {
                
            }
        }
        centerRecyclerView.layoutManager = centerLayoutManager
        centerRecyclerView.addItemDecoration(centerItemDecoration)
        centerRecyclerView.adapter = adapter
//        centerRecyclerView.setInitPosition(5)
    }
}