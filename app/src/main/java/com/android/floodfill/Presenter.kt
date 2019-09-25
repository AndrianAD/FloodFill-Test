package com.android.floodfill

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*




class Presenter {

    private var model = FloodFillModel()

     fun executeFloodFilling(view: ImageView, event: MotionEvent): Bitmap? {
        val viewCoords = IntArray(2)
        view.getLocationOnScreen(viewCoords)

        val absX = event.rawX
        val absY = event.rawY

        val imgX = absX - viewCoords[0]
        val imgY = absY - viewCoords[1]

        val maxImgX = view.width
        val maxImgY = view.height

        var bitmap= (view.drawable as BitmapDrawable).bitmap

        val maxX = bitmap.width
        val maxY = bitmap.height

        val x = (maxX * imgX / maxImgX.toFloat()).toInt()
        val y = (maxY * imgY / maxImgY.toFloat()).toInt()

        val color = bitmap.getPixel(x, y)

        if (color == Color.BLACK)
        {
            Log.d("xxx", "Black")
        }


        val replacementColor = Color.BLUE
        var newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        model.useImage(newBitmap)
        model.queueLinearFloodFill(x, y, color, replacementColor)
        return newBitmap

    }

    private fun ClosedRange<Int>.random() = Random().nextInt((endInclusive + 1) - start) + start

    companion object {
        private const val TAG = "FloodFillPresenter"
    }
}