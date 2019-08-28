package com.consoleman.modulation;

public class Modulation {
    public static double Length(double compressor, double frequency, double position, double length, int sampleRate){
        return Math.exp(((compressor / sampleRate) * frequency * sampleRate * (position / sampleRate)) / (length / sampleRate));
        //return Math.log(1 + 0.5 * (Math.E - 1)) * Math.abs(1 - 0.5) + 0.5;
    }
}
