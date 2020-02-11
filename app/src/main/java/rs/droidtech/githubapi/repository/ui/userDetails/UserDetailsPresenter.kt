package rs.droidtech.githubapi.repository.ui.userDetails

import kotlinx.coroutines.launch
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.util.ResultWrapper
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.ui.base.BasePresenterContractImpl
import rs.droidtech.githubapi.repository.ui.base.BaseViewContract

class UserDetailsPresenter(
    view: BaseViewContract<GithubUser>?,
    repository: DataRepositoryContract?
) : BasePresenterContractImpl<String, GithubUser>(view, repository) {

    override fun getData(request: String) {
        launch {
            when (val response = repository?.getGithubUser(request)) {
                is ResultWrapper.Success -> viewContract?.setData(response.value)
                is ResultWrapper.GenericError -> viewContract?.setError(response.error)
                is ResultWrapper.NetworkError -> viewContract?.setError(
                    generateNetworkError()
                )
            }
        }
    }
}