package rs.droidtech.githubapi.repository.ui.userRepository

import kotlinx.coroutines.launch
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.ui.base.BasePresenterContractImpl

class UserRepositoryPresenter(
    view: UserRepositoryView?,
    repository: DataRepositoryContract?
) : BasePresenterContractImpl<String, List<GithubUserRepo>>(view, repository) {

    override fun getData(request: String) {
        launch {
            when (val response = repository?.getGithubUserRepo(request)) {
                is ResultWrapper.Success -> viewContract?.setData(response.value)
                is ResultWrapper.GenericError -> viewContract?.setError(response.error)
                is ResultWrapper.NetworkError -> viewContract?.setError(
                    generateNetworkError()
                )
            }
        }
    }
}