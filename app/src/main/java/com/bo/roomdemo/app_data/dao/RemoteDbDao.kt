package com.bo.roomdemo.app_data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bo.roomdemo.app_data.entity.RemoteDbEntity

@Dao
interface RemoteDbDao {

    @Query("SELECT COUNT(*) FROM remotedbentity WHERE remote_id = :remoteId")
    fun doesRemoteExist(remoteId: Long): Int

    @Query("SELECT * FROM remotedbentity")
    fun getAll(): List<RemoteDbEntity>

    @Query("SELECT * FROM remotedbentity WHERE remote_name IN (:remoteIds)")
    fun loadAllByIds(remoteIds: IntArray): List<RemoteDbEntity>

    @Query("SELECT * FROM RemoteDbEntity WHERE remote_name LIKE :rmtNm AND " +
            "remote_model LIKE :rmtMdlNm LIMIT 1")
    fun findByName(rmtNm: String, rmtMdlNm: String): RemoteDbEntity

    @Insert
    fun insert(remoteDbEntity: RemoteDbEntity)

    @Insert
    fun insertAll(vararg remoteDbEntities: RemoteDbEntity)

    @Delete
    fun delete(remoteDbEntity: RemoteDbEntity)
}