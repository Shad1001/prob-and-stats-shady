import java.util.Random;
import java.util.Scanner;
public class Door {

public static void main(String[] args) {
    Scanner scan = new Scanner (System.in);
    Random generator = new Random();
    
    int user_door;
    int open_door;
    int other_door;
    int prize_door;
    
    // Create random value 1-3
    prize_door = generator.nextInt(3)+1;
    open_door = prize_door;

    while(open_door == prize_door){
        open_door = generator.nextInt(3)+1;
    }

    other_door = open_door;

    while (other_door == open_door || other_door == prize_door){
        other_door = generator.nextInt(3)+1;
    }
    
    
    
    
    
    
    
    
    
    //starting the game

    System.out.println("Welcome to the Game");
    System.out.println("Pick doors (1, 2 or 3");
    
    
}
}
