package com.android.floodfill;

import android.graphics.Bitmap;

public class Memento {
    private Bitmap bitmap;

    public Memento(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
