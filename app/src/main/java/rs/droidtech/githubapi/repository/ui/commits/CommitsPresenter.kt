package rs.droidtech.githubapi.repository.ui.commits

import kotlinx.coroutines.launch
import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.ui.base.BasePresenterContractImpl

class CommitsPresenter(
    view: CommitsView?,
    repository: DataRepositoryContract?
) : BasePresenterContractImpl<Map<String, String>, List<GithubCommit>>(view, repository) {


    override fun getData(request: Map<String, String>) {
        val owner = request["owner"]
        val repo = request["repository"]

        if (owner != null && repo != null) {
            launch {
                when (val response = repository?.getGithubCommits(owner, repo)) {
                    is ResultWrapper.Success -> viewContract?.setData(response.value)
                    is ResultWrapper.GenericError -> viewContract?.setError(response.error)
                    is ResultWrapper.NetworkError -> viewContract?.setError(
                        generateNetworkError()
                    )
                }
            }
        } else {
            viewContract?.let {
                (viewContract as CommitsView).showInvalidDataError()
            }
        }
    }
}