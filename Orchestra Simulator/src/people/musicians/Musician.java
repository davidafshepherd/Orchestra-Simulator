package people.musicians;

/**
 * Interface used to represent a musician
 */
public interface Musician {

    /**
     * Allocates this musician a seat in the orchestra
     * @param seat seat number
     */
    void setSeat(int seat);

    /**
     * Reads the score and updates the next note that this musician has to play and how loud they should play it
     */
    void readScore(int[] notes, boolean soft);

    /**
     * Plays the next note that this musician has to play
     */
    void playNextNote();

    /**
     * Gets the ID of this musician's instrument
     * @return instrument ID
     */
    int getInstrumentID();

    /**
     * Checks whether this musician has a note to play or not
     * @return true if musician has a note to play and false if not
     */
    boolean hasNotesToPlay();

    /**
     * Gets the name of this musician
     * @return name of musician
     */
    String getName();

    /**
     * Removes any notes assigned to this musician to play
     */
    void clearScore();
}
