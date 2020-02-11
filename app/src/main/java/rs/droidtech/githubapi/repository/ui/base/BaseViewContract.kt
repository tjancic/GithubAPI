package rs.droidtech.githubapi.repository.ui.base

interface BaseViewContract<in T> {
    fun setData(data: T)
    fun setError(exception: Exception)
    fun showLoading()
    fun hideLoading()
}