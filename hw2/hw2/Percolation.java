package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF obj = null;
    private int[] grid = null;
    private int N;
    private int numberOfOpenSites;
    private int topIndex;
    private int bottomIndex;
    private final int[][] surroundings = {{0, -1}, {-1, 0}, {1, 0},{0, 1},};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        numberOfOpenSites = 0;
        grid = new int[N * N + 2];
        obj = new WeightedQuickUnionUF(N * N + 2);
        topIndex = 0;
        bottomIndex = N * N + 1;
    }

    private boolean validRowAndCol(int row, int col) {
        return (0 <= row) && (0 <= col) && (row <= N - 1) && (col <= N - 1);
    }

    private int getIndex(int row, int col) {
        // the row and column indices are integers between 0 and N − 1.
        return row * N + col + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validRowAndCol(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int currentIndex = getIndex(row, col);
        grid[currentIndex] = 1;
        for (int[] sur:surroundings) {
            int surRow = row + sur[0];
            int surCol = col + sur[1];
            if (validRowAndCol(surRow, surCol) && isOpen(surRow, surCol)) {
                obj.union(currentIndex, getIndex(surRow, surCol));
            }
        }
        // especially, if the site is on the 0 row.
        if (row == 0) {
            obj.union(topIndex, currentIndex);
        }
        // especially, if the site is on the N-1 row.
        if (row == N - 1) {
            obj.union(currentIndex, bottomIndex);
        }
        numberOfOpenSites += 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validRowAndCol(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[getIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validRowAndCol(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return obj.connected(getIndex(row, col), topIndex);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return obj.connected(topIndex, bottomIndex);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
