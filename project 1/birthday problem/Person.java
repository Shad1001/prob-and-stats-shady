import java.util.Random;

/**
 * The Person class represents an individual with a randomly assigned birthday.
 * Each Person's birthday is represented as an integer corresponding to a day of the year.
 */
public class Person {
    // The day of the year representing the birthday 
    private int dayOfBirth;
    // Random number generator for assigning a birthday.
    Random rng = new Random();

    /**
     * Constructor for Person.
     * It initializes the birthday to a random day between 0 and 364.
     */
    public Person() {
        dayOfBirth = rng.nextInt(365); // Generates a random integer between 0 and 365 
    }

    /**
     * Retrieves the birthday of the person.
     *
     * @return An integer representing the person's birthday.
     */
    public int fetchBirthday() {
        return dayOfBirth;
    }

    /**
     * Allows changing the person's birthday.
     *
     * @param newBirthDay The new birthday value.
     */
    public void changeBirthday(int newBirthDay) {
        dayOfBirth = newBirthDay;
    }
}
