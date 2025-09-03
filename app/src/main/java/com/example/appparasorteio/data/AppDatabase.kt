package com.example.appparasorteio.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FilmeEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDao
}

