import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int side_len;
    private WeightedQuickUnionUF uf;
    private int top;
    private int bottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        side_len = n;
        grid = new boolean [n][n];
        top = 0;
        bottom = n * n + 1;
        openSites = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col))
        {
            grid[row - 1][col - 1] = true;
            openSites++;
        }
        

        if (row == 1)
            uf.union(getUFIndex(row, col), top);
        if (row == side_len)
            uf.union(getUFIndex(row,col), bottom);
        
        if (row > 1 && isOpen(row - 1, col)){
            uf.union(getUFIndex(row, col), getUFIndex(row - 1, col));
        }
        if (row < side_len && isOpen(row + 1, col)){
            uf.union(getUFIndex(row, col), getUFIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)){
            uf.union(getUFIndex(row, col), getUFIndex(row, col - 1));
        }
        if (col <side_len && isOpen(row, col + 1)){
            uf.union(getUFIndex(row, col), getUFIndex(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.find(getUFIndex(row, col)) == uf.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(bottom) == uf.find(top);
    }

    private int getUFIndex(int row, int col) {
        return side_len * (row - 1) + col;
    }
    // test client (optional)
    public static void main(String[] args) {

    }
}