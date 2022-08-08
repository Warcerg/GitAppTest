package ru.gbpractice.gitapptest.domain.repo

import ru.gbpractice.gitapptest.domain.entities.UserEntity

interface UserListRepo {

    fun getUserList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}