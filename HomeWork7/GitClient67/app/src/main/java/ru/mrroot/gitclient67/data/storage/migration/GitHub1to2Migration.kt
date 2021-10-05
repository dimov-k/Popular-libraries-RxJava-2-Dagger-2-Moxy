package ru.mrroot.gitclient67.data.storage.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object GitHub1to2Migration : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("delete from github_user")
        database.execSQL("delete from github_repos")

    }

}