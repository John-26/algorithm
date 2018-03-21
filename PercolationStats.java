import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    private int times;
    private Percolation P;
    private double fractions[];
    public PercolationStats (int n, int trials){
        times = trials;
        fractions = new double[times];
        for (int i = 0;i<times;++i) {
            P = new Percolation(n);
            while (!P.percolates()) {
               int row = StdRandom.uniform(1,n+1);
               int col = StdRandom.uniform(1,n+1);
               if(!P.isOpen(row,col)) P.open(row,col);
            }
            fractions[i] = (double)P.numberOfOpenSites()/(n*n);
        }
    }

    public double mean(){
        return StdStats.mean(fractions);
    }
    public double stddev(){
        return StdStats.stddev(fractions);
    }
    public double confidenceLo()     {
        return mean()-(1.96*stddev()/(Math.sqrt(times)));
    }
    public double confidenceHi(){
        return mean()+(1.96*stddev()/(Math.sqrt(times)));
    }
    public static void main(String[] args){
        PercolationStats test = new PercolationStats(200,100);
        StdOut.println(test.mean());
        StdOut.println(test.stddev());
        StdOut.println(test.confidenceLo());
        StdOut.println(test.confidenceHi());


    }
}
