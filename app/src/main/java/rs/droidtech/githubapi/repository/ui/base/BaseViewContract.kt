package rs.droidtech.githubapi.repository.ui.base

import rs.droidtech.githubapi.repository.repository.remote.util.ErrorResponse

interface BaseViewContract<in T> {
    fun setData(data: T)
    fun setError(error: ErrorResponse?)
    fun showLoading()
    fun hideLoading()
}