import java.util.ArrayList;
import java.util.Collections;

public class StatsLibrary {

    // Calculate mean of a list
    public double mean(ArrayList<Integer> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / list.size();
    }

    // Calculate median of a list
    public double median(ArrayList<Integer> list) {
        ArrayList<Integer> sorted = new ArrayList<>(list);
        Collections.sort(sorted);
        int n = sorted.size();
        if (n % 2 == 1) {
            return sorted.get(n / 2);
        } else {
            return (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
        }
    }

    // Calculate mode (most frequent number)
    public int mode(ArrayList<Integer> list) {
        int mode = list.get(0);
        int maxCount = 0;
        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == list.get(i))
                    count++;
            }
            if (count > maxCount) {
                maxCount = count;
                mode = list.get(i);
            }
        }
        return mode;
    }

    // Sample variance
    public double variance(ArrayList<Integer> list) {
        double m = mean(list);
        double sumSq = 0;
        for (int i = 0; i < list.size(); i++) {
            double diff = list.get(i) - m;
            sumSq += diff * diff;
        }
        return sumSq / (list.size() - 1);
    }

    // Standard deviation
    public double stdDev(ArrayList<Integer> list) {
        return Math.sqrt(variance(list));
    }

    // Factorial
    public double factorial(int n) {
        double f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    // Permutation: nPr = n! / (n - r)!
    public double permutation(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / factorial(n - r);
    }

    // Combination: nCr = n! / (r! * (n - r)!)
    public double combination(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // Conditional probability: P(A|B) = P(A and B) / P(B)
    public double condProb(double joint, double pB) {
        if (pB == 0) return 0;
        return joint / pB;
    }

    // Check if events are independent: P(A and B) ~ P(A) * P(B)
    public boolean independent(double joint, double pA, double pB) {
        return Math.abs(joint - (pA * pB)) < 1e-6;
    }

    // Bayes' theorem: P(A|B) = (P(B|A) * P(A)) / P(B)
    public double bayes(double prior, double likelihood, double marginal) {
        if (marginal == 0) return 0;
        return (likelihood * prior) / marginal;
    }

    // Geometric distribution: probability that the first success occurs on kth trial
    public double geometric(int k, double p) {
        if (k < 1) return 0;
        return Math.pow(1 - p, k - 1) * p;
    }

    // Binomial distribution: P(X = k) = C(n, k) * p^k * (1-p)^(n-k)
    public double binomial(int n, int k, double p) {
        if (k < 0 || k > n) return 0;
        return combination(n, k) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    // Hypergeometric distribution:
    // P(X = k) = [C(K, k) * C(N-K, n-k)] / C(N, n)
    // N: total population, K: total successes in population, n: draws, k: successes in draws
    public double hypergeometric(int N, int K, int n, int k) {
        if (K < k || N - K < n - k) return 0;
        double numerator = combination(K, k) * combination(N - K, n - k);
        double denominator = combination(N, n);
        return numerator / denominator;
    }

    // Negative binomial distribution:
    // P(X = k) = C(r+k-1, k) * p^r * (1-p)^k
    // r: number of successes required, k: number of failures before r successes, p: probability of success
    public double negativeBinomial(int r, int k, double p) {
        if (r <= 0 || k < 0) return 0;
        return combination(r + k - 1, k) * Math.pow(p, r) * Math.pow(1 - p, k);
    }
}
