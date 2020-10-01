package com.quantumwebgarden.miwok

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_number.*

class NumberActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager:AudioManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        title = "Numbers"
        val numbers:ArrayList<Word> = ArrayList()
        numbers.add(Word("lutti","one",R.drawable.number_one,R.raw.number_one))
        numbers.add(Word("otiiko","two",R.drawable.number_two,R.raw.number_two))
        numbers.add(Word("tolookosu","three",R.drawable.number_three,R.raw.number_three))
        numbers.add(Word("oyyisa","four",R.drawable.number_four,R.raw.number_four))
        numbers.add(Word("massokka","five",R.drawable.number_five,R.raw.number_five))
        numbers.add(Word("temmokka","six",R.drawable.number_six,R.raw.number_six))
        numbers.add(Word("kenekaku","seven",R.drawable.number_seven,R.raw.number_seven))
        numbers.add(Word("kawinta","eight",R.drawable.number_eight,R.raw.number_eight))
        numbers.add(Word("wo'e","nine",R.drawable.number_nine,R.raw.number_nine))
        numbers.add(Word("na'aacha","ten",R.drawable.number_ten,R.raw.number_ten))
        val wordAdapter:WordAdapter = WordAdapter(this,numbers,R.color.number)
        number_list_view.adapter = wordAdapter
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
        number_list_view.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer?.release()
            mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build()
            if(mAudioManager?.requestAudioFocus(audioFocusRequest) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this, numbers[position].getAudio())
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