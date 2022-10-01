package com.todokanai.buildeight.room

import android.net.Uri
import android.provider.MediaStore
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_track")
class RoomTrack {      // 행렬의 행 역할
    @PrimaryKey(autoGenerate = true) // no에 값이 없으면 자동증가된 숫자값을 db에 입력
    @ColumnInfo     // 열의 항목들
    var no: Long? = null
    @ColumnInfo
    var id: String? = null
    @ColumnInfo
    var title: String? = null
    @ColumnInfo
    var artist: String? = null
    @ColumnInfo
    var albumId: String? = null
    @ColumnInfo
    var duration: Long? = null


    constructor(id:String?, title:String?, artist:String?, albumId:String?, duration:Long?) {
        this.id = id
        this.title = title
        this.artist = artist
        this.albumId = albumId
        this.duration = duration
    }

    override fun toString(): String {
        return "RoomTrack(no=$no, id=$id, title=$title, artist=$artist, albumId=$albumId, duration=$duration)"
    }

    fun getTrackUri(): Uri {
        return Uri.withAppendedPath(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id     // 음원의 주소
        )
    }          //-----------음원의 Uri 주소 호출하는 함수

    fun getAlbumUri(): Uri {

        return Uri.parse(
            "content://media/external/audio/albumart/$albumId"    //앨범 이미지 주소
        )
    }
}