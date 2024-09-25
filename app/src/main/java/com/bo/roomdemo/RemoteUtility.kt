package com.bo.roomdemo

import android.app.Activity
import com.bo.roomdemo.data.RemotesModel

class RemoteUtility {

    fun getPresetRemotes(
        activity: Activity
    ): RemotesModel? {

        val metadataList = ReadJsonUtility.getObjectData(
            activity = activity,
            targetObject = RemotesModel::class.java,
            fileName = "remotes.json"
        )

        if (metadataList?.irRemoteInfo == null) {
            return null
        }
        return metadataList
    }

    companion object {
        private var instance: RemoteUtility? = null
        fun getInstance(): RemoteUtility {
            if (instance == null) {
                instance = RemoteUtility()
            }
            return instance ?: RemoteUtility()
        }

        fun clearInstance() {
            instance = null
        }
    }

}