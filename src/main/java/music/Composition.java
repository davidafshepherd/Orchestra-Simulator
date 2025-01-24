package music;

import java.util.List;

/**
 * Interface used to represent a composition
 */
public interface Composition {

    /**
     * Gets the name of this composition
     * @return name of composition
     */
    String getName();

    /**
     * Adds a music score to this composition
     * @param instrumentName instrument playing the music score
     * @param notes notes in the music score
     * @param soft whether the music score should be played softly or not
     */
    void addScore(String instrumentName, List<String> notes, boolean soft);

    /**
     * Gets the music scores in the composition
     * @return music scores
     */
    MusicScore[] getScores();

    /**
     * Gets the length of the composition
     * @return composition length
     */
    int getLength();

    /**
     * Gets the note length in milliseconds
     * @return note length
     */
    int getNoteLength();
}
