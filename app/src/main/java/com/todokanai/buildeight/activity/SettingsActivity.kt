package com.todokanai.buildeight.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.todokanai.buildeight.TrackTool
import com.todokanai.buildeight.databinding.ActivitySettingsBinding
import com.todokanai.buildeight.room.RoomHelper

class SettingsActivity : AppCompatActivity() {

    val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    lateinit var helper: RoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val intentmain = Intent(this,MainActivity::class.java)
        binding.Backbtn.setOnClickListener {startActivity(intentmain)} //Backbtn에 대한 동작

        helper = Room.databaseBuilder(this,RoomHelper::class.java,"room_db")
            .addMigrations(MigrateDatabase.MIGRATE_1_2)
            .allowMainThreadQueries()
            .build()

        val scannedList = TrackTool(this).scanTrackList()
        val size = scannedList.size
        fun scan() {            // 음원파일 스캔 함수
            helper.roomTrackDao().deleteAll()
            for (a in 1..size) {
                helper.roomTrackDao().insert(scannedList[a - 1])
            }
        }
        binding.Scanbtn.setOnClickListener{scan()}
    }
    //onCreate 끝
}

//룸 변경사항 적용하기
object MigrateDatabase {
    val MIGRATE_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val alter = "ALTER table room_track add column new_title text"
            database.execSQL(alter)
        }
    }
}