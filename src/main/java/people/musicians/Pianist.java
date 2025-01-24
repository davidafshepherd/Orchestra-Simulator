package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class used to represent a pianist
 */
public class Pianist extends Person implements Musician{

    //Instrument ID of the piano
    int instrumentID;
    //Notes that this pianist has to play
    List<Integer> notes = new ArrayList<>();
    //Next note that this pianist has to play
    Iterator<Integer> nextNote = notes.iterator();
    //Sound system of the orchestra
    SoundSystem soundSystem;
    //Seat number that the pianist is sitting in
    int seat;
    //How loud the pianist should play their piano
    int loudness;

    /**
     * Instantiates a pianist
     * @param enteredName name of pianist
     * @param enteredSoundSystem sound system of the orchestra the pianist is playing in
     */
    public Pianist(String enteredName, SoundSystem enteredSoundSystem) {
        super(enteredName);
        soundSystem = enteredSoundSystem;
        instrumentID = 1;
    }

    /**
     * Allocates this pianist a seat in the orchestra
     * @param enteredSeat seat number
     */
    public void setSeat(int enteredSeat) {
        seat = enteredSeat;
        soundSystem.setInstrument(seat, instrumentID);
    }

    /**
     * Reads the music score and updates the next note that this pianist has to play and how loud they should play it
     * @param enteredNotes notes in the music score
     * @param soft whether the music should be played softly or not
     */
    public void readScore(int[] enteredNotes, boolean soft) {
        notes.clear();
        for (int i: enteredNotes) {
            notes.add(i);
        }
        if (soft) {
            loudness = 75;
        }
        else {
            loudness = 150;
        }
        nextNote = notes.iterator();
    }

    /**
     * Plays the next note that this pianist has to play
     */
    public void playNextNote() {
        if(nextNote.hasNext()) {
            soundSystem.playNote(seat, nextNote.next(), loudness);
        }
    }

    /**
     * Gets the ID of the piano
     * @return piano ID
     */
    public int getInstrumentID() {
        return instrumentID;
    }

    /**
     * Checks whether this pianist has a note to play or not
     * @return true if pianist has a note to play and false if not
     */
    public boolean hasNotesToPlay() {
        return nextNote.hasNext();
    }

    /**
     * Removes any notes assigned to this pianist to play
     */
    public void clearScore() {
        notes.clear();
    }
}
