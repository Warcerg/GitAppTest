package ru.gbpractice.gitapptest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gbpractice.gitapptest.R
import ru.gbpractice.gitapptest.databinding.ItemUserBinding
import ru.gbpractice.gitapptest.domain.entities.UserEntity

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity, onUserClickListener: UsersAdapter.OnUserClickListener?) {
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
        binding.root.setOnClickListener { onUserClickListener?.onUserItemClick(userEntity) }
    }
}