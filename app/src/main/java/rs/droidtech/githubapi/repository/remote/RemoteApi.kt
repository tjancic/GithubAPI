package rs.droidtech.githubapi.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteApi {
    const val BASE_URL = "https://api.github.com/"
    const val USERS_ROUTE = "users/"

    val githubApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubService::class.java)

    // todo: add interceptor!
}