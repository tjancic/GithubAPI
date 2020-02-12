package rs.droidtech.githubapi.repository.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteApi {
    const val BASE_URL = "https://api.github.com/"

    const val USERS_ROUTE = "users"
    const val REPOS_ROUTE = "repos"
    const val COMMITS_ROUTE = "commits"

    val githubService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(GithubService::class.java)
}