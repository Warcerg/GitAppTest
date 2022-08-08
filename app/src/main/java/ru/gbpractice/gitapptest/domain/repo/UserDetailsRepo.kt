package ru.gbpractice.gitapptest.domain.repo

import ru.gbpractice.gitapptest.domain.entities.UserDetailsEntity
import ru.gbpractice.gitapptest.domain.entities.UserRepoEntity

interface UserDetailsRepo {

    fun getUserDetails(
        login: String,
        onSuccess: (UserDetailsEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    )

    fun getUserRepoList(
        login: String,
        onSuccess: (List<UserRepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}