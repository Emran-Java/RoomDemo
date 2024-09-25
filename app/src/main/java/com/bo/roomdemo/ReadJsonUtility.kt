package com.bo.roomdemo

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import java.io.IOException

object ReadJsonUtility {

    /**
     * @param activity = <your current activity>;
     * @param targetObject = <your targeted Model class> like MobileNetworkDataList::class.java ;
     * @param fileName = <jason file name> like mcc-mnc-list.json
     *
     * @return T = <your targeted Model object>
     *
     * */
    fun <T> getObjectData(
        activity: Activity,
        targetObject: Class<T>,
        fileName: String
    ): T? {

        val gson = Gson()
        val rawMetaData = readJsonFromAssets(activity, fileName)
        if (rawMetaData != null) {

            return gson.fromJson(rawMetaData, targetObject)

        } else return null
    }


    fun readJsonFromAssets(
        activity: Activity,
        fileName: String
    ): String? {
        var jsonString: String? = null
        try {
            jsonString = activity.assets.open(fileName).bufferedReader().use { it.readText() }
            Log.d("redJson", "$jsonString")
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.d("redJson", "${ex.message}")
        }
        return jsonString
    }


}