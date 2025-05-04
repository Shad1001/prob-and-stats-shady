public class TestStatsLibrary {
    public static void main(String[] args) {
        // Poisson PMF test
        System.out.println("Poisson PMF Y=5, lambda=8 -> "
            + StatsLibrary.poisson(5, 8));

        // Poisson expected value & variance
        double lambda = 3.0;
        System.out.println("Poisson E[X] for lambda=" + lambda
            + " -> " + StatsLibrary.poissonExpected(lambda));
        System.out.println("Poisson Var[X] for lambda=" + lambda
            + " -> " + StatsLibrary.poissonVariance(lambda));

        // Chebyshev's inequality bound
        System.out.println("Chebyshev bound (variance=4.0, k=2.0) -> P(|X-mu|>=k) <= "
            + StatsLibrary.chebyshev(4.0, 2.0));

        // Uniform distribution [a, b]
        double a = 0.0, b = 5.0, x = 3.0;
        System.out.println("Uniform PDF at x=" + x + " on [" + a + "," + b + "] = "
            + StatsLibrary.uniformProb(x, a, b));
        System.out.println("Uniform E[X] on [" + a + "," + b + "] = "
            + StatsLibrary.uniformExpected(a, b));
        System.out.println("Uniform Var[X] on [" + a + "," + b + "] = "
            + StatsLibrary.uniformVariance(a, b));

        // Normal distribution N(mean, sd^2)
        double mean = 0.0, stdDev = 1.0, z = 1.0;
        System.out.println("Normal PDF at x=" + z + " for N(mean=" + mean + ", sd=" + stdDev + ") = "
            + StatsLibrary.normalPdf(z, mean, stdDev));
        System.out.println("Normal E[X] = " + StatsLibrary.normalExpected(mean, stdDev));
        System.out.println("Normal Var[X] = " + StatsLibrary.normalVariance(mean, stdDev));
    }
}