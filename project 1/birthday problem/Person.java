import java.util.Random;

public class Person {
    private int dayOfBirth;
    Random rng = new Random();

    public Person() {
        dayOfBirth = rng.nextInt(365);
    }

    // Returns the Person's birthday (a day between 0 and 364)
    public int fetchBirthday() {
        return dayOfBirth;
    }

    // Sets the Person's birthday
    public void changeBirthday(int newBirthDay) {
        dayOfBirth = newBirthDay;
    }
}
