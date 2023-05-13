package com.catalin.randomuser.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.catalin.randomuser.data.repository.model.User
import com.catalin.randomuser.databinding.UserListItemBinding

class PagingChatAdapter() : PagingDataAdapter<User, UserItemViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class UserItemViewHolder(
    private val binding: UserListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: User) {
        binding.user = item
    }
}