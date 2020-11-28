package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
    private int experimentTimes;
    private int N;
    private PercolationFactory obj = null;

    private double[] estimates = null;
    private double mean;
    private double standardDeviation;
    private double interval;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.experimentTimes = T;
        this.N = N;
        this.obj = pf;
        estimates = new double[experimentTimes];
        for (int i = 0; i < experimentTimes; ++i) {
            estimates[i] = mySimulation();
        }
    }

    private double mySimulation() {
        Percolation grid = obj.make(N);
        while (!grid.percolates()) {
            // uniform(int n), Returns a random real number uniformly in [0, n).
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            if (!grid.isOpen(row, col)) {
                grid.open(row, col);
            }
        }
        return grid.numberOfOpenSites() * 1.0 / N / N;
    }

    // sample mean of percolation threshold
    public double mean() {
        this.mean = StdStats.mean(estimates);
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        this.standardDeviation = StdStats.stddev(estimates);
        return this.standardDeviation;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean - (1.96 * this.standardDeviation / Math.sqrt(experimentTimes));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return this.mean + (1.96 * this.standardDeviation / Math.sqrt(experimentTimes));
    }
}
