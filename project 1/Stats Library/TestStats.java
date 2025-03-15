import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to test various statistical methods from the StatsLibrary.
 * It creates a sample list of numbers, then prints out results for different statistical calculations.
 */
public class TestStats {
    public static void main(String[] args) {
        // Create an instance of StatsLibrary which contains our statistical methods.
        StatsLibrary s = new StatsLibrary();
        
        // Define a sample list of numbers to work with.
        // Here we're using an ArrayList initialized with the values 1, 2, 2, 3, and 4.
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4));

        // Calculate and print the mean (average) of the numbers.
        System.out.println("Mean: " + s.mean(nums));
        
        // Calculate and print the median (middle value) of the list.
        System.out.println("Median: " + s.median(nums));
        
        // Calculate and print the mode (most frequent value) in the list.
        System.out.println("Mode: " + s.mode(nums));
        
        // Calculate and print the variance (a measure of spread in the data).
        System.out.println("Variance: " + s.variance(nums));
        
        // Calculate and print the standard deviation (the square root of the variance).
        System.out.println("Standard Deviation: " + s.stdDev(nums));
        
        // Calculate and print the factorial of 5.
        System.out.println("Factorial of 5: " + s.factorial(5));
        
        // Calculate and print the number of permutations for choosing 3 items from 5.
        System.out.println("Permutation (5,3): " + s.permutation(5, 3));
        
        // Calculate and print the number of combinations for choosing 3 items from 5.
        System.out.println("Combination (5,3): " + s.combination(5, 3));

        // Testing probability functions:
        
        // Calculate and print the conditional probability given two probabilities.
        System.out.println("Conditional Prob (0.2, 0.5): " + s.condProb(0.2, 0.5));
        
        // Check if events are independent given two events and their joint probability.
        System.out.println("Are events independent? " + s.independent(0.2, 0.5, 0.4));
        
        // Use Bayes' theorem with sample probabilities and print the result.
        System.out.println("Bayes theorem (0.5, 0.6, 0.3): " + s.bayes(0.5, 0.6, 0.3));

        // Testing different probability distributions:
        
        // Calculate and print the probability for the geometric distribution.
        // We are checking the probability of the 3rd success when p=0.2.
        System.out.println("Geometric (k=3, p=0.2): " + s.geometric(3, 0.2));
        
        // Calculate and print the binomial probability.
        // In this example we want the probability of getting exactly 2 successes in 5 trials with p=0.5.
        System.out.println("Binomial (n=5, k=2, p=0.5): " + s.binomial(5, 2, 0.5));

        // Testing the hypergeometric distribution:
        // For example, in a deck of 52 cards with 4 aces, this calculates the probability of drawing exactly 1 ace in 5 cards.
        System.out.println("Hypergeometric (N=52, K=4, n=5, k=1): " + s.hypergeometric(52, 4, 5, 1));

        // Testing the negative binomial distribution:
        // This calculates the probability that you get 3 successes with 2 failures when p=0.5.
        System.out.println("Negative Binomial (r=3, k=2, p=0.5): " + s.negativeBinomial(3, 2, 0.5));
    }
}
