package rs.droidtech.githubapi.repository.ui.commits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_repository.*
import kotlinx.android.synthetic.main.mvp_loading_error_layout.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.repository.DataRepository
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ErrorResponse
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.repository.remote.util.generateNoDataError
import rs.droidtech.githubapi.repository.ui.commits.adapter.CommitsAdapter
import rs.droidtech.githubapi.repository.util.getExtra
import rs.droidtech.githubapi.repository.util.hide
import rs.droidtech.githubapi.repository.util.isOnline
import rs.droidtech.githubapi.repository.util.show

class CommitsActivity : AppCompatActivity(), CommitsView {

    companion object {
        const val OWNER_EXTRA_KEY = "owner"
        const val REPOSITORY_EXTRA_KEY = "repository"
    }

    private lateinit var presenter: CommitsPresenter
    private lateinit var repository: DataRepositoryContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commits)

        setupRecyclerView()

        setupRepository()
        setupPresenter()

        invokeGetData()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CommitsActivity)
            adapter = CommitsAdapter()
        }
    }

    private fun invokeGetData() {

        val owner = getExtra<String>(OWNER_EXTRA_KEY)
        val repository = getExtra<String>(REPOSITORY_EXTRA_KEY)

        owner?.let {
            repository?.let {
                val request: Map<String, String> =
                    mapOf("owner" to owner, "repository" to repository)
                if (isOnline()) presenter.getData(request) else setError(generateNetworkError())
            }
        }
    }

    private fun setupRepository() {
        val remoteRepository = RemoteRepository()
        repository = DataRepository(remoteRepository)
    }

    private fun setupPresenter() {
        presenter = CommitsPresenter(this, repository)
        lifecycle.addObserver(presenter)
    }

    override fun setData(data: List<GithubCommit>) {
        // Populate UI with data
        if (data.isNotEmpty()) {
            (recyclerView.adapter as CommitsAdapter).updateCommits(data)
            // Handle with UI
            contentGroup.show()
            hideLoading()
        } else {
            generateNoDataError()
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
