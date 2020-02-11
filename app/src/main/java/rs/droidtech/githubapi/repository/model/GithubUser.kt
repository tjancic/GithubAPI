package rs.droidtech.githubapi.repository.model

import com.squareup.moshi.Json

data class GithubUser(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "node_id") val node_id: String,
    @field:Json(name = "avatar_url") val avatar_url: String,
    @field:Json(name = "gravatar_id") val gravatar_id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "html_url") val html_url: String,
    @field:Json(name = "followers_url") val followers_url: String,
    @field:Json(name = "following_url") val following_url: String,
    @field:Json(name = "gists_url") val gists_url: String,
    @field:Json(name = "starred_url") val starred_url: String,
    @field:Json(name = "subscriptions_url") val subscriptions_url: String,
    @field:Json(name = "organizations_url") val organizations_url: String,
    @field:Json(name = "repos_url") val repos_url: String,
    @field:Json(name = "events_url") val events_url: String,
    @field:Json(name = "received_events_url") val received_events_url: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "site_admin") val site_admin: Boolean,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "company") val company: String,
    @field:Json(name = "blog") val blog: String,
    @field:Json(name = "location") val location: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "hireable") val hireable: String,
    @field:Json(name = "bio") val bio: String,
    @field:Json(name = "public_repos") val public_repos: Int,
    @field:Json(name = "public_gists") val public_gists: Int,
    @field:Json(name = "followers") val followers: Int,
    @field:Json(name = "following") val following: Int,
    @field:Json(name = "created_at") val created_at: String,
    @field:Json(name = "updated_at") val updated_at: String
)