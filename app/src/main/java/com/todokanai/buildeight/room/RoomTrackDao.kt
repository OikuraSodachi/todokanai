package com.todokanai.buildeight.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomTrackDao {

    @Query("select * from room_track")
    fun getAll() : List<RoomTrack>

    @Insert(onConflict = REPLACE)
    fun insert(roomTrack: RoomTrack)
    @Delete
    fun delete(roomTrack: RoomTrack)
    @Query("Delete from room_track")
    fun deleteAll()

}