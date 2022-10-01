package com.todokanai.buildeight

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.todokanai.buildeight.room.RoomTrack
import com.todokanai.buildeight.service.ForegroundPlayService

class TrackTool (context: Context?){
    var context: Context? = null
    var mediaPlayer = ForegroundPlayService.mediaPlayer
    val isPlaying : Boolean?
    get(){return ForegroundPlayService.mediaPlayer?.isPlaying}
    val isLooping : Boolean?
    get(){return ForegroundPlayService.mediaPlayer?.isLooping}

    fun scanTrackList(): List<RoomTrack> {
        // 1. 음원 정보 주소
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI    // URI 값을 주면 나머지 데이터 모아옴
        // 2. 음원 정보 자료형 정의
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        // 3. 컨텐트리졸버의 쿼리에 주소와 컬럼을 입력하면 커서형태로 반환받는다
        val cursor = context?.contentResolver?.query(listUrl, proj, null, null, null)
        val trackList = mutableListOf<RoomTrack>()
        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)

            val track = RoomTrack(id, title, artist, albumId, duration)
            trackList.add(track)
        }
        cursor?.close()
        trackList.sortByDescending { it.title }       // 제목기준으로 정렬
        return trackList    // track 전체 반환
    }

    fun replay() {
        mediaPlayer!!.isLooping = !mediaPlayer!!.isLooping      // 반복재생 Toggle
        Log.d("tester","isLooping: ${mediaPlayer!!.isLooping}")
        Log.d("tester","TrackTool:: repeat Button activated")
    }       // 반복재생

    fun prev(){
        if(mediaPlayer != null) {       //이미 재생되고있는게 있으면
            mediaPlayer?.release()      //재생 중단후
            mediaPlayer = null          //재생중이 아닌상태로 초기화, //재생중인 동일곡 클릭시 중단안하고 지속하는 코드 만들기 //

        }
        Log.d("tester","TrackTool:: prev Button activated")
    }       // 이전곡

    fun pauseplay(){
        if(isPlaying == true){
            mediaPlayer?.pause()
        }else{
            mediaPlayer?.start()
        }
        Log.d("tested","TrackTool:: isPlaying:$isPlaying")
    }       // 일시정지,재생

    fun next(){
        Log.d("tester","TrackTool:: next Button activated")
    }       // 다음곡

    fun close() {
        mediaPlayer?.release()
        Log.d("tester","TrackTool:: close Button activated")
    }       // 종료

    fun start() {
        if(mediaPlayer !=null) {
            mediaPlayer?.start()
            Log.d("tested","TrackTool:: isPlaying: $isPlaying")
        }
    }
    //---------------------


    fun iconset():Int {
        if(isPlaying ==false)
            return R.drawable.ic_baseline_play_arrow_24
        else return R.drawable.ic_baseline_pause_24
    }

    fun repeatIconset():Int {
        if(isLooping == false)
            return R.drawable.ic_baseline_repeat_24
        else return R.drawable.ic_baseline_repeat_one_24
    }


}