package rs.droidtech.githubapi.repository.repository.remote

import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

interface RemoteRepositoryContract {

    suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser>
}