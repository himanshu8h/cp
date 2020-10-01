package com.quantumwebgarden.miwok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getColor

class WordAdapter(context: Context, private val Words: ArrayList<Word>, private val layoutColor: Int):ArrayAdapter<Word>(context,R.layout.list_item,Words) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        val word: Word = Words.get(position)
        val mTextView:TextView = view.findViewById(R.id.mWord_text_view)
        mTextView.text = word.getWord()
        val eTextView:TextView = view.findViewById(R.id.eWord_text_view)
        eTextView.text = word.getEnglish()
        val imgView:ImageView = view.findViewById(R.id.image_view)
        val img:Int = word.getImg()
        if(img == -1) imgView.visibility = View.GONE
        else {
            imgView.setImageResource(img)
            imgView.setBackgroundColor(getColor(context,R.color.white))
        }
        val layout:LinearLayout = view.findViewById(R.id.linear)
        val color = getColor(context, layoutColor)
        layout.setBackgroundColor(color)
        return view
    }
}