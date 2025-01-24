import music.MusicScore;
import music.MusicSheet;
import people.conductors.Conductor;
import people.musicians.Cellist;
import people.musicians.Musician;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.SoundSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.Integer.parseInt;

/**
 * Class used to make an orchestra perform
 */
public class EcsBandAid {

    /**
     * Makes an orchestra perform
     * @param args text file containing musicians, text file containing compositions and how many years the orchestra should play for
     */
    public static void main(String[] args) {
        //Creates a sound system for the orchestra
        SoundSystem soundSystem;
        try {
            soundSystem = new SoundSystem();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException("Synthesiser for sound system is not installed in the system.");
        }

        //Creates musicians, music sheets and a conductor for the orchestra
        List<Musician> musicians = readMusiciansFile(args[0], soundSystem);
        List <MusicSheet> compositions = readCompositionsFile(args[1]);
        Conductor conductor = new Conductor("Carlos Kleiber", soundSystem);

        //Makes the orchestra perform
        for (int i = 0; i < parseInt(args[2]); i++) {
            performForAYear(musicians, compositions, conductor);
        }
    }

    /**
     * Makes an orchestra perform for a year
     * @param musicians musicians in the orchestra
     * @param compositions compositions that the orchestra can play
     * @param conductor conductor of the orchestra
     */
    public static void performForAYear(List<Musician> musicians, List<MusicSheet> compositions, Conductor conductor) {
        //This chooses 3 random compositions from the compositions array list and outputs which ones
        List<MusicSheet> chosenCompositions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            chosenCompositions.add(compositions.get((int) (Math.random() * (compositions.size()))));
        }
        System.out.println(chosenCompositions.get(0).getName() + ", " + chosenCompositions.get(1).getName() + " and " + chosenCompositions.get(2).getName() + " have been chosen.");

        //This part checks how many and which type of musicians are needed to play the scores in all three compositions
        int pianistsNeeded = 0;
        int cellistsNeeded = 0;
        int violinistsNeeded = 0;
        for (MusicSheet i : chosenCompositions) {
            int pianists = 0;
            int cellists = 0;
            int violinists = 0;
            for (MusicScore j : i.getScores()) {
                if (j.getInstrumentID() == 1) {
                    pianists = pianists + 1;
                } else if (j.getInstrumentID() == 43) {
                    cellists = cellists + 1;
                } else if (j.getInstrumentID() == 41) {
                    violinists = violinists + 1;
                }
            }
            if (pianists > pianistsNeeded) {
                pianistsNeeded = pianists;
            } if (cellists > cellistsNeeded) {
                cellistsNeeded = cellists;
            } if (violinists > violinistsNeeded) {
                violinistsNeeded = violinists;
            }
        }

        //This part checks if any more musicians are needed to play the scores in the next three compositions than the ones registered with the conductor
        for (Musician i : conductor.getRegisteredMusicians()) {
            if (i.getInstrumentID() == 1 && pianistsNeeded != 0) {
                pianistsNeeded = pianistsNeeded - 1;
            } else if (i.getInstrumentID() == 43 && cellistsNeeded != 0) {
                cellistsNeeded = cellistsNeeded - 1;
            } else if (i.getInstrumentID() == 41 && violinistsNeeded != 0) {
                violinistsNeeded = violinistsNeeded - 1;
            }
        }

        //This part registers any musicians that are still needed to play the scores in the next three compositions with the conductor and outputs their names if necessary
        for (Musician i : musicians) {
            if (i.getInstrumentID() == 1 && pianistsNeeded != 0) {
                if (!conductor.getRegisteredMusicians().contains(i)) {
                    conductor.registerMusician(i);
                    System.out.println(i.getName() + " has been invited to the band.");
                    pianistsNeeded = pianistsNeeded - 1;
                }
            } else if (i.getInstrumentID() == 43 && cellistsNeeded != 0) {
                if (!conductor.getRegisteredMusicians().contains(i)) {
                    conductor.registerMusician(i);
                    System.out.println(i.getName() + " has been invited to the band.");
                    cellistsNeeded = cellistsNeeded - 1;
                }
            } else if (i.getInstrumentID() == 41 && violinistsNeeded != 0) {
                if (!conductor.getRegisteredMusicians().contains(i)) {
                    conductor.registerMusician(i);
                    System.out.println(i.getName() + " has been invited to the band.");
                    violinistsNeeded = violinistsNeeded - 1;
                }
            }
        }

        //This part calls the conductor's playComposition method to play all three compositions and outputs which one is playing at a given time
        for (MusicSheet i : chosenCompositions) {
            System.out.println(i.getName() + " is the composition being performed at the moment.");
            conductor.playComposition(i);
        }

        //This gives each musician a 50% chance to leave the band/orchestra and outputs the name of those who did leave
        boolean loop = true;
        int count = 0;
        while (loop) {
            if (count == conductor.getRegisteredMusicians().size()) {
                loop = false;
            } else {
                int randomizer = (int) (Math.random() * 2);
                if (randomizer == 0) {
                    System.out.println(conductor.getRegisteredMusicians().get(count).getName() + " has left the band.");
                    conductor.leaveBand(conductor.getRegisteredMusicians().get(count));
                } else {
                    count = count + 1;
                }
            }
        }
    }

    /**
     * Reads a text file containing musicians and creates musicians using them
     * @param fileName musicians text file
     * @return musicians
     */
    public static List<Musician> readMusiciansFile(String fileName, SoundSystem soundSystem) {
        //Reads each line of the musicians file until there are no more lines to read
        //If the file can't be found, then it returns an error message saying so
        List<Musician> givenMusicians = new ArrayList<>();
        FilenameReader musiciansReader = new FilenameReader(fileName);

        while(musiciansReader.fileIsReady()) {
            String line = musiciansReader.getLine();
            //Checks if each line read matches the specific format
            //If they don't, then this part returns an error with a message saying that the file isn't in the correct format
            //If they do, then this part creates a musician according to the information given by each line and adds them to an array list called givenMusicians
            String[] musicianDescription = line.split("[()]");
            if (musicianDescription.length != 2) {
                throw new RuntimeException("Input of the musicians file does not conform to the specified file format, each line should be in the format 'Name(Instrument)'");
            } else if (musicianDescription[1].equals("Piano")) {
                givenMusicians.add(new Pianist(musicianDescription[0], soundSystem));
            } else if (musicianDescription[1].equals("Cello")) {
                givenMusicians.add(new Cellist(musicianDescription[0], soundSystem));
            } else if (musicianDescription[1].equals("Violin")) {
                givenMusicians.add(new Violinist(musicianDescription[0], soundSystem));
            } else {
                throw new RuntimeException("Input of the musicians file does not conform to the specified file format, the instrument '" + musicianDescription[1] + "' is not valid in this simulator");
            }
        }

        //Returns the musicians
        return givenMusicians;
    }

    /**
     * Reads a text file containing compositions and creates a music sheet using them
     * @param fileName compositions text file
     * @return music sheet
     */
    public static List<MusicSheet> readCompositionsFile(String fileName) {
        //This part reads the first line of the compositions file
        //If the file can't be found, then this part returns an error message saying so
        //If there is no line to be read, then this part returns an error message saying so
        List<MusicSheet> givenCompositions = new ArrayList<>();
        String [] lineDescription;
        String name;
        String tempo;
        String length;
        int count = 0;
        FilenameReader compositionsReader = new FilenameReader(fileName);
        String line = compositionsReader.getLine();

        //This loop reads each line of the musicians file until there are no more lines to read
        while(compositionsReader.fileIsReady()) {
            //This part checks if the last line read (which should describe the name of the composition) matches the specific format "Name: name"
            //If it doesn't, then this part returns an error with a message saying that the file isn't in the correct format
            lineDescription = line.split(": ");
            if (lineDescription.length != 2) {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the first line should describe the name of the composition and be in the format 'Name: name'");
            } else if (lineDescription[0].equals("Name")) {
                name = lineDescription[1];
            } else {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the first line should describe the name of the composition and be in the format 'Name: name'");
            }

            //This part checks if the last line read (which should describe the tempo of the composition) matches the specific format "Tempo: tempo"
            //If it doesn't, then this part returns an error with a message saying that the file isn't in the correct format
            line = compositionsReader.getLine();
            lineDescription = line.split(": ");
            if (lineDescription.length != 2) {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the second line should describe the tempo of the composition and be in the format 'Tempo: tempo'");
            } else if (lineDescription[0].equals("Tempo")) {
                tempo = lineDescription[1];
            } else {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the second line should describe the tempo of the composition and be in the format 'Tempo: tempo'");
            }

            //This part checks if the last line read (which should describe the length of the composition) matches the specific format "Length: length"
            //If it doesn't, then this part returns an error with a message saying that the file isn't in the correct format
            line = compositionsReader.getLine();
            lineDescription = line.split(": ");
            if (lineDescription.length != 2) {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the third line should describe the length of the composition and be in the format 'Length: number'");
            } else if (lineDescription[0].equals("Length")) {
                length = lineDescription[1];
            } else {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the third line should describe the length of the composition and be in the format 'Length: number'");
            }

            //This part checks if the entered length of the composition is an integer
            //If it isn't, then this part returns an error with a message saying that the file isn't in the correct format
            int intLength;
            try {
                intLength = parseInt(length);
            } catch (Exception NumberFormatException) {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the third line should describe the length of the composition and be in the format 'Length: number' where number is a number");
            }

            //This part creates a composition according to the information given by the previous three lines and adds it to an array list called givenCompositions
            givenCompositions.add(new MusicSheet(name, tempo, intLength));

            //This part reads the next line (which should describe a music score in the last composition added to the array list givenCompositions)
            //It then checks if the line contains the number of pieces of information that it should
            //If it doesn't, then this part returns an error with a message saying that the file isn't in the correct format
            //If it does, then the line should describe a music score
            line = compositionsReader.getLine();
            line = line.trim();
            lineDescription = line.split("[, {}]+");
            if (lineDescription.length == (2 + intLength)) {
                //This loop runs while the last read line contains the number of pieces of information that it should (i.e. the line is potentially describing a music score in the composition) and the notesLoop variable is true
                //The notesLoop variable is true while there is a next line to be read from the file
                boolean notesLoop = true;
                while (notesLoop && lineDescription.length == (2 + intLength)) {
                    //This part checks if the last read line matches the specific format "Instrument, softness, {note, note, ...}"
                    //If it doesn't, then this part returns an error with a message saying that the file isn't in the correct format
                    //If it does, then this part creates a music score according to the information given by each line and adds it to the last composition added to the givenCompositions array list
                    if (lineDescription[0].equals("Piano") || lineDescription[0].equals("Cello") || lineDescription[0].equals("Violin")) {
                        if (lineDescription[1].equals("soft")) {
                            List<String> notes = new ArrayList<>(Arrays.asList(lineDescription).subList(2, lineDescription.length));
                            givenCompositions.get(count).addScore(lineDescription[0], notes, true);
                        } else if (lineDescription[1].equals("loud")) {
                            List<String> notes = new ArrayList<>(Arrays.asList(lineDescription).subList(2, lineDescription.length));
                            givenCompositions.get(count).addScore(lineDescription[0], notes, false);
                        } else {
                            throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the softness '" + lineDescription[1] + "' is not valid in this simulator, it should either be 'soft' or 'loud'");
                        }
                    } else {
                        throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the instrument '" + lineDescription[0] + "' is not valid in this simulator");
                    }
                    //This part checks if there is a next line to be read from the file
                    //If so, it reads the next line
                    //If not, it sets the notesLoop variable as false
                    if (compositionsReader.fileIsReady()) {
                        line = compositionsReader.getLine();
                        line = line.trim();
                        lineDescription = line.split("[, {}]+");
                    } else {
                        notesLoop = false;
                    }
                }
                count = count + 1;
            }
            else {
                throw new RuntimeException("Input of the compositions file does not conform to the specified file format, the next line should describe a music score in the composition and be in the format 'Instrument, softness, {note, note, ...}'");
            }
        }

        //Returns the music sheet
        return givenCompositions;
    }
}