package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // used to determine whether percolates.
    private int[] grid = null;
    // obj is associated with grid.
    private WeightedQuickUnionUF obj = null;
    // use obj2 to determine whether is full, the reason I use a
    // second WQUUF is to avoid backwash.
    private WeightedQuickUnionUF obj2 = null;
    // N rows and N cols.
    private int N;
    private int numberOfOpenSites;
    private int topIndex;
    private int bottomIndex;
    private final int[][] neighbour = {{0, -1}, {-1, 0}, {1, 0},{0, 1},};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        topIndex = 0;
        bottomIndex = N * N + 1;
        numberOfOpenSites = 0;
        grid = new int[bottomIndex + 1];
        obj = new WeightedQuickUnionUF(bottomIndex + 1);
        obj2 = new WeightedQuickUnionUF(bottomIndex);
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
        if (!isOpen(currentIndex)) {
            grid[currentIndex] = 1;
            numberOfOpenSites += 1;
            // especially, if the site is on the 0 row.
            if (row == 0) {
                obj.union(topIndex, currentIndex);
                obj2.union(topIndex, currentIndex);
            }
            // especially, if the site is on the N-1 row.
            if (row == N - 1) {
                obj.union(currentIndex, bottomIndex);
                // since I don't want backwash, I do not union
                // bottomIndex in obj2.
            }
            for (int[] sur: neighbour) {
                int neighbourRow = row + sur[0];
                int neighbourCol = col + sur[1];
                int neighbourIndex = getIndex(neighbourRow, neighbourCol);
                if (validRowAndCol(neighbourRow, neighbourCol) && isOpen(neighbourIndex)) {
                    obj.union(currentIndex, neighbourIndex);
                    obj2.union(currentIndex, neighbourIndex);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validRowAndCol(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[getIndex(row, col)] == 1;
    }

    private boolean isOpen(int index) {
        return grid[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validRowAndCol(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return obj2.connected(getIndex(row, col), topIndex);
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
