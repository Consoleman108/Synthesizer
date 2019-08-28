package com.consoleman.envelope;

import static java.lang.Math.*;

public class Envelope {
    // ADSR
    enum EnvelopeState {
        ENVELOPE_STATE_OFF,
        ENVELOPE_STATE_ATTACK,
        ENVELOPE_STATE_DECAY,
        ENVELOPE_STATE_SUSTAIN,
        ENVELOPE_STATE_RELEASE,
    }
    EnvelopeState currentEnvelopeState;
    double minAttackTime;
    double minDecayTime;
    double minSustainAmplitude;
    double minReleaseTime;

    public Envelope(){
        minAttackTime  = 0.01; // Attack Time ms
        minDecayTime   = 0.01; // Decay Time ms
        minReleaseTime = 0.01; // Release Time ms

    }


    public double getCurrentEnvelopeState(EnvelopeState currentState){
        switch (currentState) {
            case ENVELOPE_STATE_OFF:

                break;

            case ENVELOPE_STATE_ATTACK:
                break;

            case ENVELOPE_STATE_DECAY:
                break;

            case ENVELOPE_STATE_SUSTAIN:
                break;

            case ENVELOPE_STATE_RELEASE:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentState);

        }
        return 0;

    }
}
