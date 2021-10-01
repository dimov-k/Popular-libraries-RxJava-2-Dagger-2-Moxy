package ru.mrroot.gitclientv6.data.storage.user

import androidx.room.*
import io.reactivex.rxjava3.core.Completable

import io.reactivex.rxjava3.core.Single

import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

@Dao
interface GitHubUserDao {
    @Query("SELECT * FROM github_user")
    fun fetchUsers(): Single<List<GitHubUser>>

    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun fetchUserByLogin(login: String): Single<GitHubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<GitHubUser>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: GitHubUser): Completable

    @Query("SELECT * FROM github_repos WHERE repos LIKE :repos")
    fun fetchUserRepos(repos: String): Single<List<GitHubUserRepos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retainRepos(repos: List<GitHubUserRepos>): Completable
}