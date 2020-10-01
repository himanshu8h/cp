package com.quantumwebgarden.miwok

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_number.*
import kotlinx.android.synthetic.main.activity_phrase.*

class PhraseActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager:AudioManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrase)
        title = "Phrases"
        val phrases:ArrayList<Word> = ArrayList()
        phrases.add(Word("minto wuksus","Where are you going?",-1,R.raw.phrase_where_are_you_going))
        phrases.add(Word("tinne oyaase'ne","What is your name?",-1,R.raw.phrase_what_is_your_name))
        phrases.add(Word("oyaaset...","My name is...",-1,R.raw.phrase_my_name_is))
        phrases.add(Word("michekses","How are you feeling?",-1,R.raw.phrase_how_are_you_feeling))
        phrases.add(Word("kuchi achit","I'm feeling good.",-1,R.raw.phrase_im_feeling_good))
        phrases.add(Word("eenes'aa?","Are you coming?",-1,R.raw.phrase_are_you_coming))
        phrases.add(Word("hee'eenem","Yes, I'm coming.",-1,R.raw.phrase_yes_im_coming))
        phrases.add(Word("eenem","I'm coming.",-1,R.raw.phrase_im_coming))
        phrases.add(Word("yoowutis","Let's go.",-1,R.raw.phrase_lets_go))
        phrases.add(Word("enni'nem","Come here.",-1,R.raw.phrase_come_here))
        val wordAdapter:WordAdapter = WordAdapter(this, phrases, R.color.phrase)
        phrase_list_view.adapter = wordAdapter
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
        phrase_list_view.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer?.release()
            mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build()
            if(mAudioManager?.requestAudioFocus(audioFocusRequest) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this,phrases.get(position).getAudio())
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