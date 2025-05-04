import java.math.BigInteger;

public class StatsLibrary {
    // compute n!
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Poisson PMF: P(Y=y) = lambda^y e^{-lambda} / y!
    public static double poisson(int y, double lambda) {
        if (lambda < 0)
            throw new IllegalArgumentException("lambda >= 0 required");
        if (y < 0)
            throw new IllegalArgumentException("y >= 0 required");
        BigInteger factY = factorial(y);
        double num = Math.pow(lambda, y) * Math.exp(-lambda);
        double den = factY.doubleValue();
        return num / den;
    }

    // Poisson expected value = lambda
    public static double poissonExpected(double lambda) {
        if (lambda < 0)
            throw new IllegalArgumentException("lambda >= 0 required");
        return lambda;
    }

    // Poisson variance = lambda
    public static double poissonVariance(double lambda) {
        if (lambda < 0)
            throw new IllegalArgumentException("lambda >= 0 required");
        return lambda;
    }

    // Chebyshev's inequality bound
    public static double chebyshev(double variance, double k) {
        if (variance < 0 || k <= 0)
            throw new IllegalArgumentException("variance >= 0 and k > 0 required");
        return variance / (k * k);
    }

    // Continuous uniform distribution on [a, b]
    public static double uniformProb(double x, double a, double b) {
        if (a >= b)
            throw new IllegalArgumentException("Require a < b");
        return (x < a || x > b) ? 0.0 : 1.0 / (b - a);
    }

    // Expected value of uniform = (a + b) / 2
    public static double uniformExpected(double a, double b) {
        if (a >= b)
            throw new IllegalArgumentException("Require a < b");
        return (a + b) / 2.0;
    }

    // Variance of uniform = (b - a)^2 / 12
    public static double uniformVariance(double a, double b) {
        if (a >= b)
            throw new IllegalArgumentException("Require a < b");
        double diff = b - a;
        return diff * diff / 12.0;
    }

    // Normal distribution 
    public static double normalPdf(double x, double mean, double stdDev) {
        if (stdDev <= 0)
            throw new IllegalArgumentException("stdDev > 0 required");
        double z = (x - mean) / stdDev;
        return Math.exp(-0.5 * z * z) / (stdDev * Math.sqrt(2 * Math.PI));
    }

    // Normal expected value = mean
    public static double normalExpected(double mean, double stdDev) {
        if (stdDev <= 0)
            throw new IllegalArgumentException("stdDev > 0 required");
        return mean;
    }

    // Normal variance = stdDev^2
    public static double normalVariance(double mean, double stdDev) {
        if (stdDev <= 0)
            throw new IllegalArgumentException("stdDev > 0 required");
        return stdDev * stdDev;
    }
}