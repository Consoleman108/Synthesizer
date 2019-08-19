package com.consoleman.oscillator;

import com.consoleman.audio.Audio;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;

import static com.consoleman.audio.Audio.SAMPLING_RATE;

public class Oscillator extends JPanel {
    public static final int    SAMPLE_SIZE      = 2;
    public static final double FREQUENCY        = 440;
    public static final double BUFFER_DURATION  = 0.100;
    public static final int    SINE_PACKET_SIZE = (int)(BUFFER_DURATION * SAMPLING_RATE * SAMPLE_SIZE);

    static double cyclePosition = 0;

    private Audio      audio;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(SINE_PACKET_SIZE);
    private double sample;
    private double phy;
    private static double incPhy;
    private double sin[];
    private static int sampleCount;

    public Oscillator(double Frequency){
        try {
            audio = new Audio();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        sampleCount = 0;
        phy = 0;
        //incPhy = FREQUENCY/SAMPLING_RATE;
        incPhy = Frequency/SAMPLING_RATE;
        sin = new double[SAMPLING_RATE];
    }

    public void sampleInit(){
        for(int i = 0; i < SAMPLING_RATE; i++){
            sin[i] = Math.sin(2 * Math.PI * phy);
            phy += incPhy;
            if (incPhy > 1) {
                incPhy -= 1;
            }
        }
    }

    public void generateSample(){
        byteBuffer.clear();
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

    public JPanel addOcillatorToFrame(){
        //Temp
        JPanel jPanel = new JPanel();
        jPanel.setSize(279,100);
        //jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        //jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //jPanel.setLayout(null);
        return jPanel;
        //Temp end
    }
}

