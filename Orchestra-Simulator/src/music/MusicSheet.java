package music;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent a music sheet, i.e. a collection of music scores
 */
public class MusicSheet implements Composition{

    //Name of this music sheet
    String name;
    //Tempo at which each note in each music score in this music sheet should be played at
    String tempo;
    //Number of notes per music score in this music sheet
    int length;
    //List of music scores in this music sheet
    List<MusicScore> musicScoreList = new ArrayList<>();

    /**
     * Instantiates a musics sheet
     * @param enteredName name of the music sheet
     * @param enteredTempo tempo of the music sheet
     * @param enteredLength number of notes per music score
     */
    public MusicSheet(String enteredName, String enteredTempo, int enteredLength) {
        name = enteredName;
        tempo = enteredTempo;
        length = enteredLength;
    }

    /**
     * Gets the name of this music sheet
     * @return name of music sheet
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a music score to this music sheet
     * @param instrumentName instrument playing the music score
     * @param nameNotes notes in the music score
     * @param soft whether the music score should be played softly or not
     */
    public void addScore(String instrumentName, List<String> nameNotes, boolean soft) {
        int[] midiNotes = new int[nameNotes.size()];
        int count = 0;
        for (String i: nameNotes) {
            switch (i) {
                case "C3" -> {
                    midiNotes[count] = 48;
                    count = count + 1;
                }
                case "C#3", "Db3" -> {
                    midiNotes[count] = 49;
                    count = count + 1;
                }
                case "D3" -> {
                    midiNotes[count] = 50;
                    count = count + 1;
                }
                case "D#3", "Eb3" -> {
                    midiNotes[count] = 51;
                    count = count + 1;
                }
                case "E3" -> {
                    midiNotes[count] = 52;
                    count = count + 1;
                }
                case "F3" -> {
                    midiNotes[count] = 53;
                    count = count + 1;
                }
                case "F#3", "Gb3" -> {
                    midiNotes[count] = 54;
                    count = count + 1;
                }
                case "G3" -> {
                    midiNotes[count] = 55;
                    count = count + 1;
                }
                case "G#3", "Ab3" -> {
                    midiNotes[count] = 56;
                    count = count + 1;
                }
                 case "A3" -> {
                    midiNotes[count] = 57;
                    count = count + 1;
                }
                 case "A#3", "Bb3" -> {
                    midiNotes[count] = 58;
                    count = count + 1;
                }
                case "B3" -> {
                    midiNotes[count] = 59;
                    count = count + 1;
                }
                case "C4" -> {
                    midiNotes[count] = 60;
                    count = count + 1;
                }
                case "C#4", "Db4" -> {
                    midiNotes[count] = 61;
                    count = count + 1;
                }
                case "D4" -> {
                    midiNotes[count] = 62;
                    count = count + 1;
                }
                case "D#4", "Eb4" -> {
                    midiNotes[count] = 63;
                    count = count + 1;
                }
                case "E4" -> {
                    midiNotes[count] = 64;
                    count = count + 1;
                }
                case "F4" -> {
                    midiNotes[count] = 65;
                    count = count + 1;
                }
                case "F#4", "Gb4" -> {
                    midiNotes[count] = 66;
                    count = count + 1;
                }
                case "G4" -> {
                    midiNotes[count] = 67;
                    count = count + 1;
                }
                case "G#4", "Ab4" -> {
                    midiNotes[count] = 68;
                    count = count + 1;
                }
                case "A4" -> {
                    midiNotes[count] = 69;
                    count = count + 1;
                }
                case "A#4", "Bb4" -> {
                    midiNotes[count] = 70;
                    count = count + 1;
                }
                case "B4" -> {
                    midiNotes[count] = 71;
                    count = count + 1;
                }
                case "C5" -> {
                    midiNotes[count] = 72;
                    count = count + 1;
                }
                case "C#5", "Db5" -> {
                    midiNotes[count] = 73;
                    count = count + 1;
                }
                case "D5" -> {
                    midiNotes[count] = 74;
                    count = count + 1;
                }
                case "D#5", "Eb5" -> {
                    midiNotes[count] = 75;
                    count = count + 1;
                }
                case "E5" -> {
                    midiNotes[count] = 76;
                    count = count + 1;
                }
                case "F5" -> {
                    midiNotes[count] = 77;
                    count = count + 1;
                }
                case "F#5", "Gb5" -> {
                    midiNotes[count] = 78;
                    count = count + 1;
                }
                case "G5" -> {
                    midiNotes[count] = 79;
                    count = count + 1;
                }
                case "G#5", "Ab5" -> {
                    midiNotes[count] = 80;
                    count = count + 1;
                }
                case "A5" -> {
                    midiNotes[count] = 81;
                    count = count + 1;
                }
                case "A#5", "Bb5" -> {
                    midiNotes[count] = 82;
                    count = count + 1;
                }
                case "B5" -> {
                    midiNotes[count] = 83;
                    count = count + 1;
                }
                case "C6" -> {
                    midiNotes[count] = 84;
                    count = count + 1;
                }
                case "none"  -> {
                    midiNotes[count] = 0;
                    count = count + 1;
                }
            }
        }
        musicScoreList.add(new MusicScore(instrumentName, midiNotes, soft));
    }

    /**
     * Gets the music scores in this music sheet
     * @return music scores
     */
    public MusicScore[] getScores() {
        MusicScore[] musicScore = new MusicScore[musicScoreList.size()];
        int count = 0;
        for (MusicScore i: musicScoreList) {
            musicScore[count] = i;
            count = count + 1;
        }
        return musicScore;
    }

    /**
     * Gets the length of this music sheet
     * @return music sheet length
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the tempo of the music sheet and converts it into a note length
     * @return note length
     */
    public int getNoteLength() {
        return switch (tempo) {
            case "Larghissimo" -> 1500;
            case "Lento" -> 1000;
            case "Andante" -> 500;
            case "Moderato" -> 300;
            case "Allegro" -> 175;
            case "Presto" -> 150;
            default -> 0;
        };
    }
}
