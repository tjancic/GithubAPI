package rs.droidtech.githubapi.repository.ui.userRepository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.databinding.UserRepositoryItemBinding
import rs.droidtech.githubapi.repository.model.GithubUserRepo

class UserRepositoryAdapter :
    RecyclerView.Adapter<UserRepositoryAdapter.UserRepositoryViewHolder>() {

    private var repos: List<GithubUserRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<UserRepositoryItemBinding>(
            layoutInflater,
            R.layout.user_repository_item,
            parent,
            false
        )
        return UserRepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int =
        repos.size

    override fun onBindViewHolder(holder: UserRepositoryViewHolder, position: Int) {
        val repo = repos[position]
        holder.binding.repository = repo
    }

    fun updateRepo(repos: List<GithubUserRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    class UserRepositoryViewHolder(val binding: UserRepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}