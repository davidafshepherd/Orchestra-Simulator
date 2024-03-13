package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class used to represent a violinist
 */
public class Violinist extends Person implements Musician {

    //Instrument ID of the violin
    int instrumentID;
    //Notes that this violinist has to play
    List<Integer> notes = new ArrayList<>();
    //Next note that this violinist has to play
    Iterator<Integer> nextNote = notes.iterator();
    //Sound system of the orchestra
    SoundSystem soundSystem;
    //Seat number that the violinist is sitting in
    int seat;
    //How loud the violinist should play their violin
    int loudness;


    /**
     * Instantiates a violinist
     * @param enteredName name of violinist
     * @param enteredSoundSystem sound system of the orchestra the violinist is playing in
     */
    public Violinist(String enteredName, SoundSystem enteredSoundSystem) {
        super(enteredName);
        soundSystem = enteredSoundSystem;
        instrumentID = 41;
    }

    /**
     * Allocates this violinist a seat in the orchestra
     * @param enteredSeat seat number
     */
    public void setSeat(int enteredSeat) {
        seat = enteredSeat;
        soundSystem.setInstrument(seat, instrumentID);
    }

    /**
     * Reads the music score and updates the next note that this violinist has to play and how loud they should play it
     * @param enteredNotes notes in the music score
     * @param soft whether the music should be played softly or not
     */
    public void readScore(int[] enteredNotes, boolean soft) {
        notes.clear();
        for (int i: enteredNotes) {
            notes.add(i);
        }
        if (soft) {
            loudness = 50;
        } else {
            loudness = 100;
        }
        nextNote = notes.iterator();
    }

    /**
     * Plays the next note that this violinist has to play
     */
    public void playNextNote() {
        if(nextNote.hasNext()) {
            soundSystem.playNote(seat, nextNote.next(), loudness);
        }
    }

    /**
     * Gets the ID of the violin
     * @return violin ID
     */
    public int getInstrumentID() {
        return instrumentID;
    }

    /**
     * Checks whether this violinist has a note to play or not
     * @return true if violinist has a note to play and false if not
     */
    public boolean hasNotesToPlay() {
        return nextNote.hasNext();
    }

    /**
     * Removes any notes assigned to this violinist to play
     */
    public void clearScore() {
        notes.clear();
    }
}
