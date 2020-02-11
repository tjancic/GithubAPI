package rs.droidtech.githubapi.repository.repository.remote

import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.doInBackground

class RemoteRepository : RemoteRepositoryContract {

    private val githubService = RemoteApi.githubService

    override suspend fun getGithubUser(owner: String): ResultWrapper<GithubUser> {
        return doInBackground {
            githubService.getGithubUser(owner)
        }
    }

    override suspend fun getGithubUserRepos(owner: String): ResultWrapper<List<GithubUserRepo>> {
        return doInBackground {
            githubService.getGithubUserRepos(owner)
        }
    }

    override suspend fun getGithubCommits(
        owner: String,
        repository: String
    ): ResultWrapper<List<GithubCommit>> {
        return doInBackground {
            githubService.getGithubCommits(owner, repository)
        }
    }
}