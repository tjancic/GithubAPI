package rs.droidtech.githubapi.repository.ui.userDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.user_details_activity.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.repository.DataRepository
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ErrorResponse
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.util.*

class UserDetailsActivity : AppCompatActivity(), UserDetailsView {

    private lateinit var presenter: UserDetailsPresenter
    private lateinit var repository: DataRepositoryContract

    private val user: String? by stringPreference(PreferenceProperty.DEFAULT_USER_KEY, "octocat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details_activity)

        setupRepository()
        setupPresenter()

        invokeGetData()

        // todo: handle configuration changes.
        // todo: add settings menu option to get data from another user
        // todo: add local repository
    }

    private fun invokeGetData() = user?.let {
        if (isOnline()) presenter.getData(it) else setError(generateNetworkError())
    }

    private fun setupRepository() {
        val remoteRepository = RemoteRepository(applicationContext)
        repository = DataRepository(remoteRepository)
    }

    private fun setupPresenter() {
        presenter = UserDetailsPresenter(this, repository)
        lifecycle.addObserver(presenter)
    }

    override fun setData(data: GithubUser) {
        // Populate UI with data
        loadImage(data.avatar_url, userAvatar)
        nameValue.text = data.name
        companyValue.text = data.company

        // Handle with UI
        contentGroup.show()
        hideLoading()
    }

    override fun setError(error: ErrorResponse?) {
        hideLoading()
        error?.let {
            errorView.text = it.error_description
        }
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

}
