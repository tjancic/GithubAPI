package rs.droidtech.githubapi.repository.ui.userRepository.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_repository_item.view.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.GithubAPIApplication.Companion.applicationContext
import rs.droidtech.githubapi.repository.model.GithubUserRepo
import rs.droidtech.githubapi.repository.util.inflate

class UserRepositoryAdapter :
    RecyclerView.Adapter<UserRepositoryAdapter.UserRepositoryViewHolder>() {

    private var repos: List<GithubUserRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryViewHolder =
        UserRepositoryViewHolder(parent.inflate(R.layout.user_repository_item))

    override fun getItemCount(): Int =
        repos.size

    override fun onBindViewHolder(holder: UserRepositoryViewHolder, position: Int) {
        val repo = repos[position]

        holder.itemView.repositoryName.text =
            String.format(applicationContext().getString(R.string.repository_name), repo.name)
        holder.itemView.openedIssues.text = String.format(
            applicationContext().getString(R.string.opened_issues), repo.open_issues_count.toString()
        )
    }

    fun updateRepo(repos: List<GithubUserRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    class UserRepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view)
}