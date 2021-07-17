package com.manavtamboli.axion.ui.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun interface OnBottomReached {

    fun onBottomReached()

    fun interface Registration {
        fun remove()
    }

    companion object {
        /**
         * @return [RecyclerView.OnScrollListener] for removing manually which is usually not needed.
         * */
        fun RecyclerView.addOnBottomReached(onBottomReachedListener: OnBottomReached) : Registration {
            var isScrolling = false
            val onScroll = object : RecyclerView.OnScrollListener(){

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) isScrolling = true
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    (recyclerView.layoutManager as? LinearLayoutManager?)?.let {
                        if (isScrolling && it.findFirstVisibleItemPosition() + it.childCount == it.itemCount){
                            isScrolling = false
                            onBottomReachedListener.onBottomReached()
                        }
                    }
                }
            }.also { addOnScrollListener(it) }
            return Registration { removeOnScrollListener(onScroll) }
        }

        @JvmStatic
        @BindingAdapter("onBottomReached")
        fun addOnBottomReachedListener(recyclerView: RecyclerView, onBottomReached : OnBottomReached? = null){
            onBottomReached ?: return
            recyclerView.addOnBottomReached(onBottomReached)
        }
    }
}