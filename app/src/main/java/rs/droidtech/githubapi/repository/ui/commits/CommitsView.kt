package rs.droidtech.githubapi.repository.ui.commits

import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.ui.base.BaseViewContract

interface CommitsView : BaseViewContract<List<GithubCommit>> {
    fun showInvalidDataError()
}