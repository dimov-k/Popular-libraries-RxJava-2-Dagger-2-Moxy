package ru.mrroot.gitclient67.data.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mrroot.gitclient67.data.di.InMemory
import ru.mrroot.gitclient67.data.di.Persisted
import ru.mrroot.gitclient67.data.storage.GitHubStorage
import ru.mrroot.gitclient67.data.storage.migration.GitHub1to2Migration

@Module
class StorageModule {

    @Persisted
    @Provides
    fun provideGitHubDatabaseStorage(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            .addMigrations(GitHub1to2Migration)
            //        .fallbackToDestructiveMigration()
            .build()

    @InMemory
    @Provides
    fun provideGitHubInMemoryStorage(context: Context): GitHubStorage =
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
}