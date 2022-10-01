package com.todokanai.buildeight.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.todokanai.buildeight.TrackTool
import com.todokanai.buildeight.databinding.FragmentPlayingBinding

import com.todokanai.buildeight.service.ForegroundPlayService

class PlayingFragment : Fragment() {

    val binding by lazy { FragmentPlayingBinding.inflate(layoutInflater) }

    var mediaPlayer = ForegroundPlayService.mediaPlayer

    val mIconSet : Int
    get(){return TrackTool(null).iconset()}

    val mRepeatSet : Int
    get(){return TrackTool(null).repeatIconset()}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.songCurrentProgress.text = "$progress"
                if(fromUser)
                    mediaPlayer?.seekTo(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.repeatButton.setOnClickListener{ TrackTool(activity).replay() ;binding.repeatButton.setImageResource(mRepeatSet)}
        binding.previousButton.setOnClickListener{ TrackTool(activity).prev() }
        binding.playPauseButton.setOnClickListener{ TrackTool(activity).pauseplay();binding.playPauseButton.setImageResource(mIconSet); Log.d("tested","$mIconSet") }
        binding.nextButton.setOnClickListener{TrackTool(activity).next() }

        return binding.root
    }
}