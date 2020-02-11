package rs.droidtech.githubapi.repository.repository.remote

import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.doInBackground

class RemoteRepository : RemoteRepositoryContract {

    private val githubService = RemoteApi.githubService

    override suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser> {
        return doInBackground {
            githubService.getGithubUser(userId)
        }
    }

    override suspend fun getGithubUserRepos(userId: String): ResultWrapper<List<GithubUserRepo>> {
        return doInBackground {
            githubService.getGithubUserRepos(userId)
        }
    }
}