package rs.droidtech.githubapi.repository.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path
import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo

interface GithubService {

    @GET(RemoteApi.USERS_ROUTE + "/{owner}")
    suspend fun getGithubUser(@Path(value = "owner") owner: String): GithubUser

    @GET(RemoteApi.USERS_ROUTE + "/{owner}" + "/${RemoteApi.REPOS_ROUTE}")
    suspend fun getGithubUserRepos(@Path(value = "owner") owner: String): List<GithubUserRepo>

    @GET(RemoteApi.REPOS_ROUTE + "/{owner}" + "/{repository}" + "/${RemoteApi.COMMITS_ROUTE}")
    suspend fun getGithubCommits(@Path(value = "owner") owner: String, @Path(value = "repository") repository: String): List<GithubCommit>

}