package ru.gbpractice.gitapptest.ui.details

import ru.gbpractice.gitapptest.domain.entities.UserDetailsEntity
import ru.gbpractice.gitapptest.domain.entities.UserEntity
import ru.gbpractice.gitapptest.domain.entities.UserRepoEntity

interface DetailsContract {
    interface View {
        fun showUser(userEntity: UserEntity)
        fun showUserDetails(details: UserDetailsEntity)
        fun showRepoList(repos: List<UserRepoEntity>)
        fun showLoading(isLoading: Boolean)
        fun showError(t: Throwable)
    }

    interface Presenter {
        fun attach(view: View, userEntity: UserEntity)
        fun detach()
        fun provideUserData(userEntity: UserEntity)
        fun provideUserDetails(userEntity: UserEntity)
        fun loadRepoList(userEntity: UserEntity)
    }
}