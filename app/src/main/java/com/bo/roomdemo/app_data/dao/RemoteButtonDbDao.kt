package com.bo.roomdemo.app_data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bo.roomdemo.app_data.entity.RemoteButtonDbEntity
import com.bo.roomdemo.app_data.entity.RemoteDbEntity

@Dao
interface RemoteButtonDbDao {

    @Query("SELECT * FROM remotebuttondbentity")
    fun getAll(): List<RemoteButtonDbEntity>

    @Query("SELECT * FROM remotebuttondbentity WHERE remote_id IN (:remoteIds)")
    fun loadAllByIds(remoteIds: LongArray): List<RemoteButtonDbEntity>

    @Query("SELECT * FROM remotebuttondbentity WHERE remote_id == :remoteId")
    fun findRemoteButtonsByRemoteId(remoteId: Long): List<RemoteButtonDbEntity>

    @Query("SELECT * FROM remotebuttondbentity WHERE remote_id == :remoteId LIMIT 1")
    fun findByRemoteId(remoteId: Long): RemoteButtonDbEntity

    @Insert
    fun insert(remoteButtonDbEntity: RemoteButtonDbEntity)

    @Insert
    fun insertAll(vararg remoteButtonDbEntity: RemoteButtonDbEntity)

    @Delete
    fun delete(remoteButtonDbEntity: RemoteButtonDbEntity)
}