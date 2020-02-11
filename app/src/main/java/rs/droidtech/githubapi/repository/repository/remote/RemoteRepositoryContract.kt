package rs.droidtech.githubapi.repository.repository.remote

import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

interface RemoteRepositoryContract {
    suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser>
    suspend fun getGithubUserRepos(userId: String): ResultWrapper<List<GithubUserRepo>>
}