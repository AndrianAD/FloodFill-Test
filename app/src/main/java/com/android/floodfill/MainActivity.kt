package com.android.floodfill

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


class MainActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap
    var presenter = Presenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.dada)
        image.setImageBitmap(bitmap)


        restart.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }


    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
            bitmap = presenter.executeFloodFilling(image, event)!!
            image.setImageBitmap(bitmap)
        return false
    }


}

