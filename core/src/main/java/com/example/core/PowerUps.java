package com.example.core;

import android.app.Application;


public class PowerUps extends Application {
    public static int bomb;
    public static int half;

    public static int bombId;
    public static int halfId;

    public static void setBomb(int bomb){
        PowerUps.bomb = bomb;}
    public static void setHalf(int half){
        PowerUps.half = half;}
    public static void setBombId(int bombId){
        PowerUps.bombId = bombId;}
    public static void setHalfId(int halfId){
        PowerUps.halfId = halfId;}
}
