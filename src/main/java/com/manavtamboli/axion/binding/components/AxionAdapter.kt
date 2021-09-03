package com.manavtamboli.axion.binding.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.manavtamboli.axion.binding.Binder.Companion.inflate
import com.manavtamboli.axion.lifecycle.LifecycleLazy.Companion.lifecycleLazy
import com.manavtamboli.axion.lifecycle.LifecycleLazy.Initialization.OnStart
import com.manavtamboli.axion.lifecycle.doOnStart

class AxionAdapter<B : ViewBinding, T> private constructor(
    private val bindingClass : Class<B>,
    diffUtil: DiffUtil.ItemCallback<T>,
    private val onCreate : (B.() -> Unit)?,
    private val onBind : ItemEvent<B, T>?,
    private val onItemClick : ItemEvent<B, T>?,
    private val onItemLongClick : ItemEvent<B, T>?
) : ListAdapter<T, AxionAdapter.ViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        val binding = bindingClass.inflate(LayoutInflater.from(parent.context), parent, false)
        onCreate?.invoke(binding)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            onBind?.run { onItemEvent(item) }
            onItemClick?.run { root.setOnClickListener { onItemEvent(item) } } ?: root.setOnClickListener(null)
            onItemLongClick?.run { root.setOnLongClickListener { onItemEvent(item) ; true } } ?: root.setOnLongClickListener(null)
            if (this is ViewDataBinding) executePendingBindings()
        }
    }

    class ViewHolder<V : ViewBinding>(val binding : V) : RecyclerView.ViewHolder(binding.root)

    fun interface ItemEvent<B : ViewBinding, T> {
        fun B.onItemEvent(item : T)
    }

    interface Creator<B : ViewBinding, T> {
        fun onCreate(block : B.() -> Unit)
        fun onBind(itemEvent: ItemEvent<B, T>)
        fun onItemClick(itemEvent: ItemEvent<B, T>)
        fun onItemLongClick(itemEvent: ItemEvent<B, T>)
        fun attachTo(recyclerViewProvider : () -> RecyclerView)
    }

    companion object {
        // FixMe : DoOnStart
        @Suppress("FunctionName")
        fun <B : ViewBinding, T> LifecycleOwner.AxionAdapters(bindingClass: Class<B>, diffUtil: DiffUtil.ItemCallback<T>, block : Creator<B, T>.() -> Unit): Lazy<AxionAdapter<B, T>> {
            return lifecycleLazy(OnStart) {
                var onCreate : (B.() -> Unit)? = null
                var onBind : ItemEvent<B, T>? = null
                var onItemClick : ItemEvent<B, T>? = null
                var onItemLongClick : ItemEvent<B, T>? = null
                var rViewProvider : (() -> RecyclerView)? = null

                object : Creator<B, T> {
                    override fun onCreate(block : B.() -> Unit) { onCreate = block }
                    override fun onBind(itemEvent: ItemEvent<B, T>) { onBind = itemEvent }
                    override fun onItemClick(itemEvent: ItemEvent<B, T>) { onItemClick = itemEvent }
                    override fun onItemLongClick(itemEvent: ItemEvent<B, T>) { onItemLongClick = itemEvent }
                    override fun attachTo(recyclerViewProvider :() -> RecyclerView) { rViewProvider = recyclerViewProvider }
                }.apply(block)

                AxionAdapter(bindingClass, diffUtil, onCreate, onBind, onItemClick, onItemLongClick)
                    .also {
                        doOnStart {
                            rViewProvider?.invoke()?.let { r -> r.adapter = it }
                        }
                    }
            }
        }
    }
}