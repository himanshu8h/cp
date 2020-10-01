package com.quantumwebgarden.miwok

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_color.*
import kotlinx.android.synthetic.main.activity_number.*

class ColorActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager: AudioManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)
        title = "Colors"
        val colors:ArrayList<Word> = ArrayList()
        colors.add(Word("wetetti","red",R.drawable.color_red,R.raw.color_red))
        colors.add(Word("chokokki","green",R.drawable.color_green,R.raw.color_green))
        colors.add(Word("takaakki","brown",R.drawable.color_brown,R.raw.color_brown))
        colors.add(Word("topoppi","gray",R.drawable.color_gray,R.raw.color_gray))
        colors.add(Word("kululli","black",R.drawable.color_black,R.raw.color_black))
        colors.add(Word("kelelli","white",R.drawable.color_white,R.raw.color_white))
        colors.add(Word("topiise","dusty yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow))
        colors.add(Word("chiwiite","mustard yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow))
        val wordAdapter:WordAdapter = WordAdapter(this, colors, R.color.color)
        color_list_view.adapter = wordAdapter
        AudioManager.OnAudioFocusChangeListener {
            if(it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer?.pause()
                mMediaPlayer?.seekTo(0)
            }
            else if(it == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer?.start()
            }
            else {
                mMediaPlayer?.release()
            }
        }
        color_list_view.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer?.release()
            mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build()
            if(mAudioManager?.requestAudioFocus(audioFocusRequest) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this, colors[position].getAudio())
                mMediaPlayer?.start()
                mMediaPlayer?.setOnCompletionListener {
                    mMediaPlayer?.release()
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        mMediaPlayer?.release()
    }
    override fun onStop() {
        super.onStop()
        mMediaPlayer?.release()
    }

}