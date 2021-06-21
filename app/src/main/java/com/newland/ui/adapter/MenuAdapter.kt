package com.newland.ui.adapter

import com.newland.ui.R

class MenuAdapter(data: MutableList<String>?) :
    SimpleQuickAdapter<String>(R.layout.adapter_menu, data) {
    override fun convert(holder: ViewHolder, item: String) {
        holder.setText(R.id.tv_text,item)
    }
}