package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.manavtamboli.axion.lifecycle.lifecycleLazy

fun <B : ViewBinding, T> Fragment.AxionAdapter(
    bindingClass: Class<B>,
    diffUtil: ItemCallback<T>,
    block: Creator<B, T>.() -> Unit
) = lifecycleLazy { AxionAdapter.create(bindingClass, diffUtil, block) }

fun <B : ViewBinding, T> ComponentActivity.AxionAdapter(
    bindingClass: Class<B>,
    diffUtil: ItemCallback<T>,
    block: Creator<B, T>.() -> Unit
) = lifecycleLazy { AxionAdapter.create(bindingClass, diffUtil, block) }

class AxionAdapter<B : ViewBinding, T> internal constructor(
    private val bindingClass : Class<B>,
    diffUtil: ItemCallback<T>,
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

    companion object {
        fun <B : ViewBinding, T> create(
            bindingClass: Class<B>,
            diffUtil: ItemCallback<T>,
            block: Creator<B, T>.() -> Unit
        ) : AxionAdapter<B, T> {

            var onCreate : (B.() -> Unit)? = null
            var onBind : ItemEvent<B, T>? = null
            var onItemClick : ItemEvent<B, T>? = null
            var onItemLongClick : ItemEvent<B, T>? = null

            object : Creator<B, T> {
                override fun onCreate(block : B.() -> Unit) { onCreate = block }
                override fun onBind(itemEvent: ItemEvent<B, T>) { onBind = itemEvent }
                override fun onItemClick(itemEvent: ItemEvent<B, T>) { onItemClick = itemEvent }
                override fun onItemLongClick(itemEvent: ItemEvent<B, T>) { onItemLongClick = itemEvent }
            }.apply(block)

            return AxionAdapter(bindingClass, diffUtil, onCreate, onBind, onItemClick, onItemLongClick)
        }
    }
}

fun interface ItemEvent<B : ViewBinding, T> {
    fun B.onItemEvent(item : T)
}

interface Creator<B : ViewBinding, T> {
    fun onCreate(block : B.() -> Unit)
    fun onBind(itemEvent: ItemEvent<B, T>)
    fun onItemClick(itemEvent: ItemEvent<B, T>)
    fun onItemLongClick(itemEvent: ItemEvent<B, T>)
}