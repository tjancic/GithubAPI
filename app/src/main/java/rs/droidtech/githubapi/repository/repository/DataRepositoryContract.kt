package rs.droidtech.githubapi.repository.repository

import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

interface DataRepositoryContract {
    suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser>
    suspend fun getGithubUserRepo(userId: String): ResultWrapper<List<GithubUserRepo>>
}