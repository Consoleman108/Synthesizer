package com.consoleman.oscillator;

import com.consoleman.audio.Audio;

import javax.sound.sampled.LineUnavailableException;
import java.nio.ByteBuffer;

import static com.consoleman.audio.Audio.SAMPLING_RATE;

public class Oscillator{
    public static final int    SAMPLE_SIZE      = 2;
    public static final int    FREQUENCY        = 440;
    public static final double BUFFER_DURATION  = 0.100;
    public static final int    SINE_PACKET_SIZE = (int)(BUFFER_DURATION * SAMPLING_RATE * SAMPLE_SIZE);

    static double cyclePosition = 0;

    private Audio      audio;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(SINE_PACKET_SIZE);
    private double     sample;
    private double        phy;
    private static double incPhy;

    public Oscillator(){
        try {
            audio = new Audio();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        phy     = 0;
        incPhy  = 440.0/44100.0;

    }

    public void generateSample(double phy){
        //for (int i = 0; i < SINE_PACKET_SIZE / SAMPLE_SIZE; i++) {
            byteBuffer.clear();
            sample = Short.MAX_VALUE * Math.sin(2 * Math.PI * phy);

            System.out.println("Sample: " + sample);

            byteBuffer.putShort((short)(sample));
            audio.writeToAudioDataLine(byteBuffer.array(),0, byteBuffer.position());
        //}

    }

}

