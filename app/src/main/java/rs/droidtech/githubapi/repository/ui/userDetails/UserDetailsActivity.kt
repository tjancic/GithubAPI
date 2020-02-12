package rs.droidtech.githubapi.repository.ui.userDetails

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.mvp_loading_error_layout.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.model.GithubUser
import rs.droidtech.githubapi.repository.repository.DataRepository
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository
import rs.droidtech.githubapi.repository.repository.remote.util.ErrorResponse
import rs.droidtech.githubapi.repository.repository.remote.util.generateNetworkError
import rs.droidtech.githubapi.repository.ui.userDetails.dialog.UserDialog
import rs.droidtech.githubapi.repository.ui.userRepository.UserRepositoryActivity
import rs.droidtech.githubapi.repository.util.*

class UserDetailsActivity : AppCompatActivity(), UserDetailsView {

    private lateinit var presenter: UserDetailsPresenter
    private lateinit var repository: DataRepositoryContract

    private var user: String? by stringPreference(PreferenceProperty.DEFAULT_USER_KEY, "octocat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        setupRepository()
        setupPresenter()

        invokeGetData()

        title = getString(R.string.user_details_title)
    }

    private fun invokeGetData() = user?.let {
        if (isOnline()) presenter.getData(it) else setError(generateNetworkError())
    }

    private fun setupRepository() {
        val remoteRepository = RemoteRepository()
        repository = DataRepository(remoteRepository)
    }

    private fun setupPresenter() {
        presenter = UserDetailsPresenter(this, repository)
        lifecycle.addObserver(presenter)
    }

    override fun setData(data: GithubUser) {
        // Populate UI with data
        userAvatar.loadImage(data.avatar_url)
        nameValue.text = data.name ?: data.login
        companyValue.text = data.company ?: "No company"

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

    fun showReposClick(view: View) {
        val intent = Intent(this, UserRepositoryActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.set_owner) {
            val dialog = UserDialog()
            dialog.show(supportFragmentManager, UserDialog::class.simpleName)
            dialog.owner = user
            dialog.onSaveClickListener = {
                user = it
                invokeGetData()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
