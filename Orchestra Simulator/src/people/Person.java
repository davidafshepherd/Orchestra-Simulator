package people;

/**
 * Class used to represent a person
 */
public class Person {

    //Name of this person
    String name;

    /**
     * Instantiates a person
     * @param enteredName name of the person
     */
    public Person(String enteredName) {
        name = enteredName;
    }

    /**
     * Gets the name of this person
     * @return name of person
     */
    public String getName() {
        return name;
    }
}
