package com.example.core;

import android.app.Application;

public class Counter extends Application {
    public static int counter;
    public static void setCounter(int counter){
        Counter.counter = counter;
    }

    public static int counterCorrect=0;
    public static void setCounterCorrect(int counterCorrect){
        Counter.counterCorrect = counterCorrect;}
}
