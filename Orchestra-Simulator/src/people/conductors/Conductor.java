package people.conductors;

import music.Composition;
import music.MusicScore;
import orchestra.Orchestra;
import people.Person;
import people.musicians.Musician;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used to represent a conductor
 */
public class Conductor extends Person {

    //Orchestra this conductor is conducting
    Orchestra orchestra = new Orchestra();
    //Sound system of the orchestra
    SoundSystem soundSystem;
    //Musicians in the orchestra
    List<Musician> setOfMusicians = new ArrayList<>();

    /**
     * Instantiates a conductor
     * @param enteredName name of the conductor
     * @param enteredSoundSystem sound system of the conductor
     */
    public Conductor(String enteredName, SoundSystem enteredSoundSystem) {
        super(enteredName);
        soundSystem = enteredSoundSystem;
    }

    /**
     * Adds a musician to the orchestra
     * @param musician musician
     */
    public void registerMusician(Musician musician) {
        setOfMusicians.add(musician);
    }

    /**
     * This method plays a composition
     * @param composition composition
     */
    public void playComposition(Composition composition) {
        //This part gets the music scores of this composition
        List<MusicScore> scores = new ArrayList<>(Arrays.asList(composition.getScores()));
        //This part assigns each score to a musician in the array list of musicians
        for (Musician i : setOfMusicians) {
            boolean loop = true;
            int count = 0;
            while (loop) {
                if (count == scores.size()) {
                    loop = false;
                }
                else if (scores.get(count).getInstrumentID() == i.getInstrumentID()) {
                    i.readScore(scores.get(count).getNotes(), scores.get(count).isSoft());
                    scores.remove(count);
                    loop = false;
                }
                else {
                    count = count + 1;
                }
            }
        }
        //This part seats each not-seated musician who has a next note to play
        //If there is no seat left for a musician to sit in, then this part also removes any notes they had to play
        //This part also stands up any seated musician who doesn't have a next note to play
        for (Musician i: setOfMusicians) {
            if (i.hasNotesToPlay()) {
                if(!orchestra.isSeated(i)) {
                    int returnedInt = orchestra.sitDown(i);
                    if (returnedInt == 1) {
                        System.out.println("There are no free seats for " + i.getName() + " to play.");
                        i.clearScore();
                    }
                }
            }
            else if (orchestra.isSeated(i)) {
                orchestra.standUp(i);
            }
        }
        //This part plays each musician in the orchestra's next note for the set note length until the end of the composition
        for(int i = 0; i < composition.getLength(); i++) {
            orchestra.playNextNote();
            try {
                Thread.sleep(composition.getNoteLength());
            }
            catch( InterruptedException ignored) {
            }
        }
        //This part stops sound from playing
        soundSystem.init();
    }

    /**
     * Gets the musicians registered with this conductor
     * @return musicians
     */
    public List<Musician> getRegisteredMusicians() {
        return setOfMusicians;
    }

    /**
     * Removes a musician from the orchestra
     * @param musician musician
     */
    public void leaveBand(Musician musician) {
        orchestra.standUp(musician);
        setOfMusicians.remove(musician);
    }
}
