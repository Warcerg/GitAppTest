package ru.gbpractice.gitapptest.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gbpractice.gitapptest.R
import ru.gbpractice.gitapptest.databinding.ItemGitRepositoryBinding
import ru.gbpractice.gitapptest.domain.entities.UserRepoEntity

class UserRepoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_git_repository, parent, false)
) {
    private val binding = ItemGitRepositoryBinding.bind(itemView)

    fun bind(userRepoEntity: UserRepoEntity) {
        binding.repositoryName.text = userRepoEntity.name
        binding.repositoryDescription.text = userRepoEntity.description
        binding.repositoryLanguage.text = userRepoEntity.language
    }
}