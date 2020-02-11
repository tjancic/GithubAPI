package rs.droidtech.githubapi.repository.ui.commits.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.commit_item.view.*
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.GithubAPIApplication.Companion.applicationContext
import rs.droidtech.githubapi.repository.model.GithubCommit
import rs.droidtech.githubapi.repository.util.inflate
import rs.droidtech.githubapi.repository.util.loadImage
import rs.droidtech.githubapi.repository.util.toDate

class CommitsAdapter : RecyclerView.Adapter<CommitsAdapter.CommitsViewHolder>() {

    private var commits: List<GithubCommit> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder =
        CommitsViewHolder(parent.inflate(R.layout.commit_item))

    override fun getItemCount(): Int =
        commits.size

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
        val commit = commits[position]

        holder.itemView.message.text = commit.commit.message
        holder.itemView.avatar.loadImage(commit.committer?.avatar_url)
        holder.itemView.name.text = commit.commit.committer.name
        holder.itemView.date.text = String.format(
            applicationContext().getString(R.string.committed_on),
            commit.commit.committer.date.toDate()
        )
    }

    fun updateCommits(commits: List<GithubCommit>) {
        this.commits = commits
        notifyDataSetChanged()
    }

    class CommitsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
