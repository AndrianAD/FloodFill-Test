package com.android.floodfill

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.floodfill.adapters.ParentAdapter
import io.navendra.nestedrecycler.models.ChildModel
import io.navendra.nestedrecycler.models.ParentModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


class MainActivity : AppCompatActivity() {


    private var myView: MyView? = null
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var mBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.dada
        ).copy(Bitmap.Config.ARGB_8888, true)

        myView = MyView(this)
        myView!!.paint.color = Color.GREEN
        myView!!.layoutParams = ViewGroup.LayoutParams(mBitmap.width, mBitmap.height)
        relative_layout.addView(myView)

        initRecycler()

    }

    private fun initRecycler() {
        recyclerView = rv_parent
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(
                arrayListOf(
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor()),
                    ParentModel(createRamdomColor())
                ), myView = myView!!
            )
        }
    }

    fun createRamdomColor(): List<ChildModel> {
        val rnd = Random()
        return arrayListOf(
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))),
            ChildModel(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
        )
    }
}
