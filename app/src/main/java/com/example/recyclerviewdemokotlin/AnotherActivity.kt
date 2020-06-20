package com.example.recyclerviewdemokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_another.*

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        val image: Int = intent.getIntExtra("image", R.mipmap.ic_launcher)
        val title: String = intent.getStringExtra("title")
        val description: String = intent.getStringExtra("description")
        another_image_view.setImageResource(image)
        another_title.text = title
        another_description.text = description
    }
}