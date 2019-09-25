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

public class MyView extends View {

    private Path path;
    Bitmap mBitmap;
    ProgressDialog pd;
    final Point p1 = new Point();
    Canvas canvas;
    Paint paint;

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
        paint.setColor(Color.GREEN);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                p1.x = (int) x;
                p1.y = (int) y;
                final int sourceColor = mBitmap.getPixel((int) x, (int) y);
                final int targetColor = paint.getColor();
                new TheTask(mBitmap, p1, sourceColor, targetColor).execute();
                invalidate();
        }
        return true;
    }

    public void clear() {
        path.reset();
        invalidate();
    }

    public int getCurrentPaintColor() {
        return paint.getColor();
    }


    class TheTask extends AsyncTask<Void, Integer, Void> {

        Bitmap bmp;
        Point pt;
        int replacementColor, targetColor;

        public TheTask(Bitmap bm, Point p, int sc, int tc) {
            this.bmp = bm;
            this.pt = p;
            this.replacementColor = tc;
            this.targetColor = sc;
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
            filler.setTolerance(160);
            filler.floodFill(pt.x, pt.y);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pd.dismiss();
            invalidate();
        }
    }
}