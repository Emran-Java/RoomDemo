package com.bo.roomdemo.data

import com.bo.roomdemo.app_data.entity.RemoteButtonDbEntity
import com.bo.roomdemo.app_data.entity.RemoteDbEntity


data class RemotesModel(
    val irRemoteInfo: List<IrRemoteInfo>
) {
    fun remotesModelToRemoteDbEntity(irRemoteInfo: IrRemoteInfo): RemoteDbEntity {

        var themeId = 3
        try {
            themeId = irRemoteInfo.remoteTheme.toInt()
        } catch (ex: Exception) {

        }

        val obj = RemoteDbEntity(
            remoteId = irRemoteInfo.remoteId,
            remoteName = irRemoteInfo.remoteName,
            remoteModel = irRemoteInfo.remoteModel,
            remoteNote = irRemoteInfo.remoteNote,
            remoteIsActive = irRemoteInfo.remoteIsActive,
            remoteTheme = themeId,
            remoteAccessDateTime = irRemoteInfo.remoteAccessDateTime
        )
        return obj
    }

    fun remotesButtonToRemoteButtonDbEntity(remoteButton: RemoteButton): RemoteButtonDbEntity {

        val rtnObj = RemoteButtonDbEntity(
            remoteId = remoteButton.remoteId,
            buttonTitle = remoteButton.buttonTitle,
            buttonValue = remoteButton.buttonValue,
            buttonColor = remoteButton.buttonColor,
            buttonTextColor = remoteButton.buttonTextColor,
            buttonShep = remoteButton.buttonShep,
            buttonSymbol = remoteButton.buttonSymbol,
            buttonIsShowSymbol = remoteButton.buttonIsShowSymbol,
            buttonIsShowText = remoteButton.buttonIsShowText,
            buttonIsEnable = remoteButton.buttonIsEnable
        )
        return rtnObj
    }
}