package com.newland.ui.recycle

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView

/**
 * @author: leellun
 * @data: 21/6/2021.
 *
 */
class CenterRecyclerView : RecyclerView {
    private var mVelocityTracker: VelocityTracker? = null
    private var mLastTouchX = 0
    private var mLastTouchY = 0
    private var mDown = false
    private var mTouchSlop = 0
    private var mScrollPointerId = 0
    private var mPosition = 0
    private var mInterceptor = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        mPosition = 0
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker?.addMovement(e)
        val action = e!!.actionMasked
        val actionIndex = e!!.actionIndex
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mDown = true
                mScrollPointerId = e.getPointerId(actionIndex)
                mLastTouchX = (e!!.x + 0.5f).toInt()
                mLastTouchY = (e!!.y + 0.5f).toInt()
                mInterceptor = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (!mDown) return false
                handleMoveEvent(e)

            }
            MotionEvent.ACTION_UP -> {
                mDown = false
            }
            MotionEvent.ACTION_CANCEL -> {
                mDown = false
            }
        }
        return mInterceptor
    }

    /**
     * 重写touch 使其原有滑动不生效
     */
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        //当itemView没有设置点击事件或者onTouchEvent没有消费event，会抛向parent处理
        if (e?.action == MotionEvent.ACTION_MOVE) {
            if (mDown && !mInterceptor) {
                handleMoveEvent(e)
            }
        }
        return true
    }

    /**
     * move事件处理
     */
    private fun handleMoveEvent(e: MotionEvent?) {
        val vc = ViewConfiguration.get(context)
        mTouchSlop = vc.scaledTouchSlop
        val index = e!!.findPointerIndex(mScrollPointerId)
        if (index < 0) {
            return
        }
        val x = (e!!.getX(index) + 0.5f).toInt()
        val y = (e!!.getY(index) + 0.5f).toInt()
        val dx = x - mLastTouchX
        val dy = y - mLastTouchY
        if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > mTouchSlop) {
            if (dx < 0 && mPosition < adapter?.itemCount ?: 0 - 1) {
                mPosition++
                smoothScrollToPosition(mPosition)
            } else if (dx > 0 && mPosition > 0) {
                mPosition--
                if (mPosition >= adapter?.itemCount ?: 0) {
                    mPosition = adapter?.itemCount ?: 0
                }
                smoothScrollToPosition(mPosition)
            }
            mDown = false
            mInterceptor = true
        }
    }

    fun setInitPosition(position: Int) {
        scrollToPosition(position)
    }

    override fun scrollToPosition(position: Int) {
        mPosition = position
        super.scrollToPosition(position)
    }

    override fun smoothScrollToPosition(position: Int) {
        super.smoothScrollToPosition(position)
        mPosition = position
    }
}