package com.quantumwebgarden.miwok

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_family.*
import kotlinx.android.synthetic.main.activity_number.*

class FamilyActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mAudioManager:AudioManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family)
        title = "Family"
        val family:ArrayList<Word> = ArrayList()
        family.add(Word("epa","father",R.drawable.family_father,R.raw.family_father))
        family.add(Word("eta","mother",R.drawable.family_mother,R.raw.family_mother))
        family.add(Word("angsi","son",R.drawable.family_son,R.raw.family_son))
        family.add(Word("tune","daughter",R.drawable.family_daughter,R.raw.family_daughter))
        family.add(Word("taachi","older brother",R.drawable.family_older_brother,R.raw.family_older_brother))
        family.add(Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother))
        family.add(Word("tete","older sister",R.drawable.family_older_sister,R.raw.family_older_sister))
        family.add(Word("kolliti","younger sister",R.drawable.family_younger_sister,R.raw.family_younger_sister))
        family.add(Word("ama","grandmother",R.drawable.family_grandmother,R.raw.family_grandmother))
        family.add(Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather))
        val wordAdapter:WordAdapter = WordAdapter(this, family, R.color.family)
        family_list_view.adapter = wordAdapter
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
        family_list_view.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer?.release()
            mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build()
            if(mAudioManager?.requestAudioFocus(audioFocusRequest) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this,family.get(position).getAudio())
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