package rs.droidtech.githubapi.repository.repository.remote

import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

interface RemoteRepositoryContract {
    suspend fun getGithubUser(owner: String): ResultWrapper<GithubUser>
    suspend fun getGithubUserRepos(owner: String): ResultWrapper<List<GithubUserRepo>>
    suspend fun getGithubCommits(
        owner: String,
        repository: String
    ): ResultWrapper<List<GithubCommit>>
}