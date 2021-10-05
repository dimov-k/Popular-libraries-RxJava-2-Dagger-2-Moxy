package ru.mrroot.gitclient67.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrroot.gitclient67.data.storage.user.GitHubUserDao
import ru.mrroot.gitclient67.data.user.GitHubUser
import ru.mrroot.gitclient67.data.user.GitHubUserRepos

@Database(exportSchema = false, entities = [GitHubUser::class, GitHubUserRepos::class], version = 2)
abstract class GitHubStorage : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubUserDao

}

