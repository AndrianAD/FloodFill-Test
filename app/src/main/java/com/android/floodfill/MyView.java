package com.android.floodfill;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class MyView extends View {

    private Path path;
    Bitmap mBitmap;
    ProgressDialog pd;
    final Point p1 = new Point();
    Canvas canvas;
    public Paint paint;
    public Caretaker caretaker = new Caretaker();
    public int savedImage = 0;
    private int[] sourceColorRGB = new int[]{0, 0, 0};
    private int[] targetColorRGB = new int[]{0, 0, 0};


    // Bitmap mutableBitmap ;
    public MyView(Context context) {
        super(context);


        paint = new Paint();
        paint.setAntiAlias(true);
        pd = new ProgressDialog(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.dada).copy(Bitmap.Config.ARGB_8888, true);
        this.path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawBitmap(mBitmap, 0, 0, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            p1.x = (int) x;
            p1.y = (int) y;

            //save Bitmap in history


            int sourceColor = mBitmap.getPixel((int) x, (int) y);
            int targetColor = paint.getColor();

            sourceColorRGB = setColorRGB(sourceColorRGB, sourceColor);
            targetColorRGB = setColorRGB(targetColorRGB, targetColor);
             int[] blackColorRGB= new int[]{0, 0, 0};

            if (Arrays.equals(sourceColorRGB,blackColorRGB)) {
                return false;
            }

            //save to Memento
            caretaker.addMemento(saveInMemento());
            savedImage++;

            new TheTask(mBitmap, p1, sourceColorRGB, targetColor).execute();
            invalidate();
            return true;
        } else
            return true;
    }


    public void clear() {
        path.reset();
        invalidate();
    }

    public int[] setColorRGB(int[] array, int targetColor) {
        array[0] = Color.red(targetColor);
        array[1] = Color.green(targetColor);
        array[2] = Color.blue(targetColor);
        return array;
    }

    public int getCurrentPaintColor() {
        return paint.getColor();
    }


    class TheTask extends AsyncTask<Void, Integer, Void> {

        Bitmap bmp;
        Point pt;
        int replacementColor;
        int[] targetColor;



        public TheTask(Bitmap bm, Point p, int[] selectedColor, int targetColor) {
            this.bmp = bm;
            this.pt = p;
            this.replacementColor = targetColor;
            this.targetColor = selectedColor;
            pd.setMessage("Filling....");
            pd.show();
        }

        @Override
        protected void onPreExecute() {
            pd.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected Void doInBackground(Void... params) {
            QueueLinearFloodFiller filler = new QueueLinearFloodFiller(mBitmap, targetColor, replacementColor);
            filler.setTolerance(150);
            filler.floodFill(pt.x, pt.y);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pd.dismiss();
            invalidate();
        }
    }

    Memento saveInMemento() {
        return new Memento(mBitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    public Bitmap restoreFromMemento(Memento memento) {
        return memento.getBitmap();
    }
}