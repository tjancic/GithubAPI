package rs.droidtech.githubapi.repository.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo

interface GithubService {

    @GET(RemoteApi.USERS_ROUTE + "/{userId}")
    suspend fun getGithubUser(@Path(value = "userId") userId: String): GithubUser

    @GET(RemoteApi.USERS_ROUTE + "/{userId}" + "/${RemoteApi.REPOS_ROUTE}")
    suspend fun getGithubUserRepos(@Path(value = "userId") userId: String): List<GithubUserRepo>

}