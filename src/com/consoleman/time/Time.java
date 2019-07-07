package com.consoleman.time;

public class Time {
    // храним значение 1-й секкнды в нанаосекундах (1 с = миллиард наносекунд)
    public static final long SECOND = 1000000000l;

    public static long get(){
        return System.nanoTime();
    }
}
