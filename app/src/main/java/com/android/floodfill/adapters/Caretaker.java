package com.android.floodfill.adapters;

import com.android.floodfill.Memento;

import java.util.ArrayList;

public class Caretaker {

    ArrayList<Memento> savedBitmap = new ArrayList<Memento>();

    public void addMemento(Memento memento) {
        savedBitmap.add(memento);
    }

    public Memento getMemento (int index) {
        return savedBitmap.get(index);
    }
}
