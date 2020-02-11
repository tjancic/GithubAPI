package rs.droidtech.githubapi.repository.ui.base

interface BasePresenterContract<in T> {
    fun getData(request: T)
    fun cleanup()
}