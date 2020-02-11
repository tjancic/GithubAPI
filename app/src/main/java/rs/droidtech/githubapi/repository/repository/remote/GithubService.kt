package rs.droidtech.githubapi.repository.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path
import rs.droidtech.githubapi.repository.model.GithubUser

interface GithubService {

    @GET(RemoteApi.USERS_ROUTE + "{userId}")
    suspend fun getGithubUser(@Path(value = "userId") userId: String): GithubUser

}