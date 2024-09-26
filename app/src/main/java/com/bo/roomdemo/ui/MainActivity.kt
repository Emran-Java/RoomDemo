package com.bo.roomdemo.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bo.roomdemo.R
import com.bo.roomdemo.RemoteUtility
import com.bo.roomdemo.app_data.dao.RemoteButtonDbDao
import com.bo.roomdemo.app_data.dao.RemoteDbDao
import com.bo.roomdemo.app_data.db.AppDatabase
import com.bo.roomdemo.app_data.entity.RemoteDbEntity
import com.bo.roomdemo.data.RemotesModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    var remoteDbDao: RemoteDbDao? = null
    var remoteButtonDbDao: RemoteButtonDbDao? = null
    var btnInsert: Button? = null
    var btnGetAll: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initView()

        initClickListener()

        initRoomDb()

        getPresetAllRemote()

        insertRemoteData(getPresetAllRemote())
    }

    private fun insertRemoteData(presetAllRemote: RemotesModel?) {

        if (presetAllRemote?.irRemoteInfo == null) return

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("remoteInsert", "remotes size: ${presetAllRemote.irRemoteInfo.size}")

            for (remoteObj in presetAllRemote.irRemoteInfo) {

                if((remoteDbDao?.doesRemoteExist(remoteObj.remoteId) ?: 0) >= 1){
                    Log.d("remoteInsert", "Exist remote with id ${remoteObj.remoteId}")
                    continue
                }

                val remoteEntity = presetAllRemote.remotesModelToRemoteDbEntity(remoteObj)

                remoteDbDao?.insert(remoteEntity).let {

                    Log.d("remoteInsert", "buttons size: ${remoteObj.remoteButtons.size}")

                    for (btnObj in remoteObj.remoteButtons) {
                        val buttonEntity =
                            presetAllRemote.remotesButtonToRemoteButtonDbEntity(btnObj)
                        remoteButtonDbDao?.insert(buttonEntity)
                        Thread.sleep(100)
                    }
                }
                Thread.sleep(100)
            }
        }
    }

    private fun getPresetAllRemote(): RemotesModel? {
        val remotes = RemoteUtility.getInstance().getPresetRemotes(this)
        Log.d("roomDb", "${remotes?.irRemoteInfo?.size}")
        return remotes
    }

    private fun initView() {
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        btnInsert = findViewById<Button>(R.id.btnInsert)
        btnGetAll = findViewById<Button>(R.id.btnGetAll)

    }

    private fun initClickListener() {

        btnInsert?.setOnClickListener {
           findByRemoteId(2)
        }

        btnGetAll?.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val allRemotes = remoteDbDao?.getAll()
                Log.d("remoteInsert", "number of remotes: ${allRemotes?.size}")

                val allRemoteButtons = remoteButtonDbDao?.getAll()
                Log.d("remoteInsert", "number of buttons: ${allRemoteButtons?.size}")
            }
        }
    }

    private fun findByRemoteId(remoteId: Long) {
        lifecycleScope.launch(Dispatchers.IO) {
            val allButtons = remoteButtonDbDao?.findRemoteButtonsByRemoteId(remoteId)
            Log.d("remoteInsert", "number of remotes: ${allButtons?.size}")

            Log.d("remoteInsert", "number of buttons: ${Gson().toJson(allButtons)}")
        }
    }

    private fun getAllUser() {
        lifecycleScope.launch(Dispatchers.IO) {
            val remoteDbEntities: List<RemoteDbEntity> = remoteDbDao?.getAll() ?: arrayListOf()
            Log.d("roomDb", "${remoteDbEntities.size}")

            for (data in remoteDbEntities) {
                Log.d("roomDb", "${data.remoteAccessDateTime}")
            }

        }
    }

    private fun insertUser(insertRemoteDbEntity: RemoteDbEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            remoteDbDao?.insertAll(insertRemoteDbEntity)
        }
    }

    private fun initRoomDb() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "database-ir-remote"
            ).build()
            remoteDbDao = db.remoteDao()
            remoteButtonDbDao = db.remoteButtonDao()
        }

    }

    fun generateTimestamp(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDate = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSS")
            return currentDate.format(formatter)
        } else {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSS", Locale.getDefault())
            return dateFormat.format(calendar.time)
        }
    }

}