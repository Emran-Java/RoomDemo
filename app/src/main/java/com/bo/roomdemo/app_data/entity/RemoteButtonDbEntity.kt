package com.bo.roomdemo.app_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteButtonDbEntity(
    @PrimaryKey(autoGenerate = true)
    var remoteButtonId: Long = 0,
    @ColumnInfo(name = "remote_id") val remoteId: String?,
    @ColumnInfo(name = "button_title") val buttonTitle: String?,
    @ColumnInfo(name = "button_value") val buttonValue: String?, //store HAX data
    @ColumnInfo(name = "button_color") val buttonColor: String?, //store button color code
    @ColumnInfo(name = "button_shep") val buttonShep: String?, //SQUIRE, CIRCLE, OLIVE, SQUIRE_ROUND_CORNER
    @ColumnInfo(name = "button_symbol") val buttonSymbol: String?,//store resource ID (intId)
    @ColumnInfo(name = "button_is_show_symbol") val buttonIsShowSymbol: Boolean=false,//store true false
    @ColumnInfo(name = "button_is_enable") val buttonIsEnable: Boolean=false,//store true false
    )
