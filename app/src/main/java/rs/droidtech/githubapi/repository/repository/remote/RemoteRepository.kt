package rs.droidtech.githubapi.repository.repository.remote

import android.content.Context
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.doInBackground

class RemoteRepository(val context: Context) : RemoteRepositoryContract {

    private val githubService = RemoteApi.githubService

    override suspend fun getGithubUser(userId: String): ResultWrapper<GithubUser> {
        return doInBackground {
            githubService.getGithubUser(userId)
        }
    }
}