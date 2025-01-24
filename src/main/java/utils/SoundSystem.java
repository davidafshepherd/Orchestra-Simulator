package utils;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

/**
 * Class used to represent a sound system
 */
public class SoundSystem {

  //Maximum number of instruments.
  public static int MAX_INSTRUMENTS = 16;

  //Synthesizer that will "play" the sounds.
  private Synthesizer synth;

  //Different MIDI channels (at least 16 channels)
  private MidiChannel[] midiChannels;

  //Last note played on the 16 MIDI channels
  private int[] lastNotes;

  //Whether silent mode is on or not, no sounds will be played if on
  private boolean silentMode;

  //Whether text mode is on or not, playing/stopping messages will be printed to the consoles if on.
  private boolean textMode;

  //Instruments set to this sound system. Each instrument is represented by an @code{int}.
  private final int[] instruments = new int[MAX_INSTRUMENTS];

  /**
   * Creates a new synthesizer and opens it ready for playing.
   * Adds in instruments from the default soundbank.
   * @throws MidiUnavailableException if the synthesizer is not available due to resource restrictions or no synthesizer is installed in the system.
   */
  public SoundSystem() throws MidiUnavailableException {
    Synthesizer synth = MidiSystem.getSynthesizer();
    if (synth != null) {
      synth.open();
      midiChannels = synth.getChannels();
      Soundbank soundbank = synth.getDefaultSoundbank();
      if (soundbank != null) {
        Instrument[] instruments = soundbank.getInstruments();
        for (Instrument instrument : instruments) {
          synth.loadInstrument(instrument);
        }
      }
    }

    //Sets the silent mode off
    silentMode = false;
    //Sets text mode off
    textMode = false;

    // Initialises the sound system
    init();
  }

  /**
   * Initialises the sound system.
   */
  public void init() {
    // Stops the current playing
    if (lastNotes != null) {
      for (int i = 0; i < MAX_INSTRUMENTS; i++) {
        if (lastNotes[i] != 0) {
          stopNote(i, lastNotes[i]);
        }
      }
    }
    // Resets the lastNotes
    lastNotes = new int[MAX_INSTRUMENTS];
    for (int i = 0; i < MAX_INSTRUMENTS; i++) {
      lastNotes[i] = 0;
    }
  }

  /**
   * Sets an instrument to the Sound System.
   * @param instrumentPosition position of instrument in the Sound System. This must be between 0 and 15.
   * @param instrument ID of instrument.
   */
  public void setInstrument(int instrumentPosition, int instrument) {
    midiChannels[instrumentPosition].programChange(instrument);
    instruments[instrumentPosition] = instrument;
  }

  /**
   * Plays the input note using the instrument at the given instrumentPosition.
   * If silent mode is enabled then no sounds will be heard.
   * If text mode is enabled then messages will be printed to the console.
   * @param instrumentPosition position of instrument in the Sound System. This must be between 0 and 15.
   * @param note note to be played on the sound system. This is a MIDI note number (between 0 and 127).
   * @param loudness loudness level of note to be played.
   */
  public void playNote(int instrumentPosition, int note, int loudness) {
    //If we have MIDI then stop the previous note, if there was one then play the new one.
    if (!silentMode) {
      if (lastNotes[instrumentPosition] != 0) {
        stopNote(instrumentPosition, lastNotes[instrumentPosition]);
      }
      if (note != 0) {
        midiChannels[instrumentPosition].noteOn(note, loudness);
      }
      lastNotes[instrumentPosition] = note;
    }
    if (textMode) {
      if (note != 0) {
        System.out.println(
            "Playing note " + note + " on instrument " + instruments[instrumentPosition]);
      } else {
        System.out.println(
            "Silent on instrument " + instruments[instrumentPosition]);
      }
    }
  }

  /**
   * Stops playing the input note for the instrument at the input position immediately (if silent mode is off).
   * @param position input position of the instrument.
   * @param note note to be turned off immediately.
   */
  private void stopNote(int position, int note) {
    if (!silentMode) {
      midiChannels[position].noteOff(note, 0);
    }
    if (textMode) {
      System.out.println(
          "Stop note " + note + " on instrument " + instruments[position]);
    }
  }

  /**
   * Sets the silent mode.
   * @param silentMode true to enable the silent mode and false to disable the silent mode.
   */
  public void setSilentMode(boolean silentMode) {
    this.silentMode = silentMode;
  }

  /**
   * Sets the text mode.
   * @param textMode true to enable the text mode and false to disable the text mode.
   */
  public void setTextMode(boolean textMode) {
    this.textMode = textMode;
  }

}