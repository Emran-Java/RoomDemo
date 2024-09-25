package com.bo.roomdemo.app_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    var remoteId: Long = 0,
    @ColumnInfo(name = "remote_name") val remoteName: String?,
    @ColumnInfo(name = "remote_model") val remoteModel: String?,
    @ColumnInfo(name = "remote_note") val remoteNote: String?,
    @ColumnInfo(name = "remote_is_active") val remoteIsActive: Boolean = true,//
    @ColumnInfo(name = "remote_theme") val remoteTheme: Int = 3,//1=Dirk, 2=Light, 3=Default
    @ColumnInfo(name = "remote_access_date_time") val remoteAccessDateTime: String? //store last access date time in "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSS" this format. real value: 2024-10-22T06:11:43.02509436
)
