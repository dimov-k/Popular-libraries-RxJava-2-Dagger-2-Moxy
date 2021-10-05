package ru.mrroot.gitclientv6.data.storage

import android.content.Context
import androidx.room.Room

object GitHubStorageFactory {

    fun create(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            //        .addMigrations(GitHub1to2Migration, GitHub2to3Migration)
            //         .fallbackToDestructiveMigration()
            .build()

    fun createInMemory(context: Context): GitHubStorage =
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
}