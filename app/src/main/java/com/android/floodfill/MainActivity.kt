package com.android.floodfill

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


class MainActivity : AppCompatActivity() {


    private var myView: MyView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        myView = MyView(this)
        relative_layout.addView(myView)

        btn_blue.setOnClickListener { myView!!.paint.color = Color.BLUE }
        btn_red.setOnClickListener { myView!!.paint.color = Color.RED }
        btn_yellow.setOnClickListener { myView!!.paint.color = Color.YELLOW }


    }
}
