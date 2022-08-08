package ru.gbpractice.gitapptest.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gbpractice.gitapptest.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.gitapptest.domain.entities.UserDetailsEntity
import ru.gbpractice.gitapptest.domain.entities.UserRepoEntity

interface GitHubAPI {

    @GET("users")
    fun getUsersList(): Call<List<UserEntityDTO>>

    @GET("users/{login}/repos")
    fun getUserRepoList(
        @Path("login") login: String
    ): Call<List<UserRepoEntity>>


    @GET("users/{login}")
    fun getUserInfo(
        @Path("login") login: String
    ): Call<UserDetailsEntity>
}