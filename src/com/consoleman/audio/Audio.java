package com.consoleman.audio;

import javax.sound.sampled.*;

public class Audio{
	public static final int     SAMPLING_RATE       = 44100;
	public static final int     SAMPLE_SIZE_IN_BITS = 16;
	public static final int     CHANNELS            = 1;
	public static final boolean SIGNED              = true;
	public static final boolean BIG_ENDIAN          = true;

	private SourceDataLine sourceDataLine;

	public Audio() throws LineUnavailableException{
		AudioFormat   audioFormat  = new AudioFormat(SAMPLING_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

		if (!AudioSystem.isLineSupported(dataLineInfo)){
		System.out.println("Line matching " + dataLineInfo + " is not supported.");
		throw new LineUnavailableException();
	}

		sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
	}

	/*public void initAudioDataLine() throws LineUnavailableException{
	AudioFormat   audioFormat  = new AudioFormat(SAMPLING_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
	DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

		if (!AudioSystem.isLineSupported(dataLineInfo)){
		System.out.println("Line matching " + dataLineInfo + " is not supported.");
		throw new LineUnavailableException();
	}

	sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
	}*/

	public void writeToAudioDataLine(byte byteBufferArray[],int off, int len){
		sourceDataLine.write(byteBufferArray, off, len);
	}

	public void closeAudioDataLine(){
		sourceDataLine.drain();
		sourceDataLine.close();
	}
}



