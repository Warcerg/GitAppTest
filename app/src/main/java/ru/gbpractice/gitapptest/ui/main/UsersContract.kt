package ru.gbpractice.gitapptest.ui.main

import ru.gbpractice.gitapptest.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsersList(users: List<UserEntity>)
        fun showLoading(isLoading: Boolean)
        fun showError(t: Throwable)
        fun showUserDetails(userEntity: UserEntity)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefreshUserList()
        fun onSelectUser(userEntity: UserEntity)
    }
}