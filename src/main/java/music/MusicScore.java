package music;

/**
 * Class used to represent a music score
 */
public class MusicScore {

    //Name of instrument playing this music score
    String instrumentName;
    //Notes in this music score
    int[] notes;
    //Whether this music score should be played softly or not
    boolean soft;

    /**
     * Instantiates a music score
     * @param enteredInstrumentName name of the instrument playing the music score
     * @param enteredNotes notes in the music score
     * @param enteredSoft whether the music score should be played softly or not
     */
    public MusicScore(String enteredInstrumentName, int[] enteredNotes, boolean enteredSoft) {
        instrumentName = enteredInstrumentName;
        notes = enteredNotes;
        soft = enteredSoft;
    }

    /**
     * Gets the ID of the instrument playing this music score
     * @return instrument ID
     */
    public int getInstrumentID() {
        return switch (instrumentName) {
            case "Piano" -> 1;
            case "Cello" -> 43;
            case "Violin" -> 41;
            default -> 0;
        };
    }

    /**
     * Gets the notes in this music score
     * @return notes
     */
    public int[] getNotes() {
        return notes;
    }

    /**
     * Gets whether the music score should be played softly or not
     * @return true if softly and false if not
     */
    public boolean isSoft() {
        return soft;
    }
}
