package com.todokanai.buildeight.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RoomTrack::class,RoomPlayer::class], version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomTrackDao():RoomTrackDao
    abstract fun roomPlayerDao():RoomPlayerDao
}