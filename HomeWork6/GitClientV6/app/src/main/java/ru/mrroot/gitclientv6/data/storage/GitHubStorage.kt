package ru.mrroot.gitclientv6.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrroot.gitclientv6.data.storage.user.GitHubUserDao
import ru.mrroot.gitclientv6.data.user.GitHubUser
import ru.mrroot.gitclientv6.data.user.GitHubUserRepos

@Database(exportSchema = false, entities = [GitHubUser::class, GitHubUserRepos::class], version = 1)
abstract class GitHubStorage : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubUserDao

}

