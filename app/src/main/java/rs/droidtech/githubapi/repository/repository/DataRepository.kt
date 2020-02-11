package rs.droidtech.githubapi.repository.repository

import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

class DataRepository(private val remoteRepository: RemoteRepository) : DataRepositoryContract {

    override suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser> =
        remoteRepository.getGithubUser(userId)

    override suspend fun getGithubUserRepo(userId: String): ResultWrapper<List<GithubUserRepo>> =
        remoteRepository.getGithubUserRepos(userId)
}