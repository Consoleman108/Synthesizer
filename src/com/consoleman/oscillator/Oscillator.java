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
    private double        sample;
    private double        phy;
    private static double incPhy;
    private double        sin[];
    private static int    sampleCount;

    public Oscillator(){
        try {
            audio = new Audio();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        sampleCount = 0;
        phy         = 0;
        incPhy      = 440.0/44100.0;
        sin = new double[SAMPLING_RATE];

        for(int i = 0; i < SAMPLING_RATE; i++){
            sin[i] = Math.sin(2 * Math.PI * phy);
            phy += incPhy;
            if (incPhy > 1) {
                incPhy -= 1;
            }
        }





    }

    public void generateSample(){
        //for (int i = 0; i < SINE_PACKET_SIZE / SAMPLE_SIZE; i++) {
        byteBuffer.clear();
            //sample = Short.MAX_VALUE * Math.sin(2 * Math.PI * phy);

            //System.out.println("Sample: " + sample);

            //byteBuffer.putShort((short)(sample));
        try {
            sample = Short.MAX_VALUE * sin[sampleCount];
        }
        catch (ArrayIndexOutOfBoundsException ae){
            System.out.println(ae);
        }
        byteBuffer.putShort((short)(sample));
        audio.writeToAudioDataLine(byteBuffer.array(),0, byteBuffer.position());
        sampleCount++;
        if(sampleCount > 44100){
                sampleCount = 0;
        }
        System.out.println("sampleCount: " + sampleCount);
    }
        //}

    }

