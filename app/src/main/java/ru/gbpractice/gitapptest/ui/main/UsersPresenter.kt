package ru.gbpractice.gitapptest.ui.main

import ru.gbpractice.gitapptest.domain.entities.UserEntity
import ru.gbpractice.gitapptest.domain.repo.UserListRepo

class UsersPresenter(private val userListRepo: UserListRepo) : UsersContract.Presenter {

    private var view: UsersContract.View? = null
    private var usersList: List<UserEntity>? = null
    private var isLoading: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showLoading(isLoading)
        usersList?.let { view.showUsersList(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshUserList() {
        isLoading = true
        view?.showLoading(isLoading)
        userListRepo.getUserList(
            onSuccess = {
                isLoading = false
                view?.showLoading(isLoading)
                usersList = it
                view?.showUsersList(it)
            },
            onError = {
                isLoading = false
                view?.showLoading(isLoading)
                view?.showError(it)
            }
        )
    }

    override fun onSelectUser(userEntity: UserEntity) {
        view?.showUserDetails(userEntity)
    }

    fun initPresenterData(view: UsersContract.View, usersList: List<UserEntity>){
        this.view = view
        this.usersList = usersList
    }

    fun getPView() = view
    fun getPPresenter() = userListRepo
}