package rs.droidtech.githubapi.repository.repository

import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper

class DataRepository(private val remoteRepository: RemoteRepository) : DataRepositoryContract {

    override suspend fun getGithubUser(owner: String): ResultWrapper<GithubUser> =
        remoteRepository.getGithubUser(owner)

    override suspend fun getGithubUserRepo(owner: String): ResultWrapper<List<GithubUserRepo>> =
        remoteRepository.getGithubUserRepos(owner)

    override suspend fun getGithubCommits(
        owner: String,
        repository: String
    ): ResultWrapper<List<GithubCommit>> =
        remoteRepository.getGithubCommits(owner, repository)

}