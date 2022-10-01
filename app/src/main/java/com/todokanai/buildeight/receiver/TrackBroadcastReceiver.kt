package com.todokanai.buildeight.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.todokanai.buildeight.TrackTool
import com.todokanai.buildeight.activity.MainActivity
import com.todokanai.buildeight.service.ForegroundPlayService

class TrackBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
            Log.d("tester", "Receiver Reached")
        if(intent.action == ForegroundPlayService.ACTION_REPLAY) {
            Log.d("tester","Filter: REPEAT")
            TrackTool(null).replay()
        }else if(intent.action == ForegroundPlayService.ACTION_PREV) {
            Log.d("tester","Filter: PREV")
        }else if(intent.action == ForegroundPlayService.ACTION_PAUSE_PLAY) {
            Log.d("tester", "Filter: PAUSE_PLAY")
            TrackTool(null).pauseplay()
            // 최종 도착지점 pauseplay() 호출 성공
        }else if(intent.action == ForegroundPlayService.ACTION_NEXT) {
            Log.d("tester","Filter: NEXT")
        }else if(intent.action == ForegroundPlayService.ACTION_CLOSE) {
            Log.d("tester", "Filter: CLOSE")
        }
    }
}