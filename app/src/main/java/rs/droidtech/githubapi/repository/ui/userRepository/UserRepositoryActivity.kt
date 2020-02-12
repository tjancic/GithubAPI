package rs.droidtech.githubapi.repository.ui.userRepository

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_repository.*
import kotlinx.android.synthetic.main.mvp_loading_error_layout.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.repository.DataRepository
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ErrorResponse
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.repository.remote.util.generateNoDataError
import rs.droidtech.githubapi.repository.ui.commits.CommitsActivity
import rs.droidtech.githubapi.repository.ui.userRepository.adapter.UserRepositoryAdapter
import rs.droidtech.githubapi.repository.util.*

class UserRepositoryActivity : AppCompatActivity(), UserRepositoryView {

    private lateinit var presenter: UserRepositoryPresenter
    private lateinit var repository: DataRepositoryContract

    private val user: String? by stringPreference(PreferenceProperty.DEFAULT_USER_KEY, "octocat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_repository)

        setupRecyclerView()

        setupRepository()
        setupPresenter()

        invokeGetData()

        title = getString(R.string.user_repos_title)
    }

    private fun setupRecyclerView() {

        val repoAdapter = UserRepositoryAdapter {
            val extras = mapOf(
                CommitsActivity.OWNER_EXTRA_KEY to it.owner.login,
                CommitsActivity.REPOSITORY_EXTRA_KEY to it.name
            )
            gotoActivity(
                CommitsActivity::class,
                extras = extras
            )
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UserRepositoryActivity)
            adapter = repoAdapter
        }
    }

    private fun invokeGetData() = user?.let {
        if (isOnline()) presenter.getData(it) else setError(generateNetworkError())
    }

    private fun setupRepository() {
        val remoteRepository = RemoteRepository()
        repository = DataRepository(remoteRepository)
    }

    private fun setupPresenter() {
        presenter = UserRepositoryPresenter(this, repository)
        lifecycle.addObserver(presenter)
    }

    override fun setData(data: List<GithubUserRepo>) {
        // Populate UI with data
        if (data.isNotEmpty()) {
            (recyclerView.adapter as UserRepositoryAdapter).updateRepo(data)
            // Handle with UI
            contentGroup.show()
            hideLoading()
        } else {
            setError(generateNoDataError())
        }
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
