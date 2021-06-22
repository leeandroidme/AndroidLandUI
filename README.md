# AndroidLandUI
定制开发Android UI网上很多，但有时很难找到自己想要的，有的实现不但达不到自己想要的效果而且代码写的非常复杂，以后自己写的定制开发UI就都放到这里了
1 定制开发苹果的下面tab
![苹果相机](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz_png%2FcibketMByvrZ4MMeVK51mQ7NOQ1A3lfWfiayn4nvj6EXfRFL8GTdkhvjprYaEic7S1wzcZxpPSwricqYSG6icxLJdiaw%2F0%3Fwx_fmt%3Dpng&refer=http%3A%2F%2Fmmbiz.qpic.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626918708&t=3ae7d1f07eaef3f6116ffb78e6010699)
```
val datas = mutableListOf<String>("攝影", "慢動作", "視頻", "照片", "正方形", "全景", "慢動作", "視頻", "照片", "正方形", "全景")
		//居中布局管理器
        var centerLayoutManager = CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //首尾插入间隔
        var centerItemDecoration = CenterItemDecoration()
        var adapter = MenuAdapter(datas)
        adapter.setOnItemClickListener(object :OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                centerRecyclerView.smoothScrollToPosition(position)
            }
        })
        //居中item切换通知
        centerRecyclerView.mOnTargetItemListener= object : CenterRecyclerView.OnTargetItemListener {
            override fun onTargetItem(position: Int, prePosition: Int) {
                
            }
        }
        centerRecyclerView.layoutManager = centerLayoutManager
        centerRecyclerView.addItemDecoration(centerItemDecoration)
        centerRecyclerView.adapter = adapter
```

