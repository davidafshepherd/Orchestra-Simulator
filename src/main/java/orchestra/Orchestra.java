package orchestra;

import people.musicians.Musician;

import java.util.HashMap;

/**
 * Class used to represent an orchestra
 */
public class Orchestra {

    //Map of seat numbers to the musicians seated in them
    HashMap<Integer, Musician> seating;

    /**
     * Initialises an orchestra
     */
    public Orchestra() {
        seating = new HashMap<>();
    }

    /**
     * Allocates a musician a seat in the orchestra if a seat is available
     * @param musician musician
     * @return 2 if musician is already seated, 0 if musician was allocated a seat and 1 if no seats were available
     */
    public int sitDown(Musician musician) {
        for (int i: seating.keySet()) {
            if (seating.get(i) == musician) {
                return 2;
            }
        }
        for (int i = 0; i < 16; i++) {
            if(!seating.containsKey(i)) {
                musician.setSeat(i);
                seating.put(i, musician);
                return 0;
            }
        }
        return 1;
    }

    /**
     * Checks if a musician is seated
     * @param musician musician
     * @return true if seated and false if not
     */
    public boolean isSeated(Musician musician) {
        for (int i: seating.keySet()) {
            if (seating.get(i) == musician) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a musician stand up
     * @param musician musician
     */
    public void standUp(Musician musician) {
        int index = -1;
        for (int i : seating.keySet()) {
            if (seating.get(i) == musician) {
                index = i;
            }
        }
        if (index != -1) {
            seating.remove(index);
        }
    }

    /**
     * Makes all seated musicians in this orchestra play their next note
     */
    public void playNextNote() {
        for (int i: seating.keySet()) {
            seating.get(i).playNextNote();
        }
    }
}

