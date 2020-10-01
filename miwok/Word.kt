package com.quantumwebgarden.miwok

class Word(private val mWord: String, private val eWord: String,private val img:Int,private val audio:Int) {
    fun getWord(): String {
        return mWord
    }
    fun getEnglish(): String {
        return eWord
    }
    fun getImg():Int {
        return img
    }
    fun getAudio():Int {
        return audio
    }
}