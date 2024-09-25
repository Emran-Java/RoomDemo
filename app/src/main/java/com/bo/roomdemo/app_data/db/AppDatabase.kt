package com.bo.roomdemo.app_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bo.roomdemo.app_data.entity.RemoteButtonDbEntity
import com.bo.roomdemo.app_data.dao.RemoteDbDao
import com.bo.roomdemo.app_data.entity.RemoteDbEntity

@Database(entities = [RemoteDbEntity::class, RemoteButtonDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun remoteDao(): RemoteDbDao
}