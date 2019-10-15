package com.android.floodfill;

import java.util.ArrayList;

                                    public class Caretaker {

                                        ArrayList<Memento> savedBitmap = new ArrayList<Memento>();

                                        public void addMemento(Memento memento) {
                                            savedBitmap.add(memento);
                                        }

                                        public Memento getMemento(int index) {
                                            return savedBitmap.get(index);
                                        }

                                        public void deleteMemento(int index) {
                                            savedBitmap.remove(index);
                                        }
                                    }
