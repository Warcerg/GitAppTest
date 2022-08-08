package ru.gbpractice.gitapptest

import android.app.Application
import android.content.Context
import ru.gbpractice.gitapptest.data.retrofit.RetrofitUserDetailsRepoImpl
import ru.gbpractice.gitapptest.data.retrofit.RetrofitUserListRepoImpl
import ru.gbpractice.gitapptest.domain.repo.UserDetailsRepo
import ru.gbpractice.gitapptest.domain.repo.UserListRepo

class App : Application() {

    val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl() }
    val userDetailsRepo: UserDetailsRepo by lazy { RetrofitUserDetailsRepoImpl() }


    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

