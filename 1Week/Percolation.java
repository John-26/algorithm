import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid ;
    private int matrix[][];
    private int sz;
    public Percolation(int n){
        grid = new WeightedQuickUnionUF(n*n+2);
        matrix = new int[n][n];
        for(int i =0; i<n; i++){
            for(int j = 0;j<n; ++j){
                matrix[i][j]=0;
            }
        }
        sz = n;
    }
    public void open(int row, int col){
        //if(row<=0||col<=0) {throw new ava.lang.IllegalArgumentException();return;}
        matrix[row-1][col-1] = 1;
        if(row == 1){
            grid.union(0,col);
            if (col==1){
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
            }
            else if(col==sz){
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
            }
            else{
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
            }
        }
        else if(row == sz){
            grid.union(sz*sz+1,(sz-1)*sz+col);
            if(col==1){
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
            }
            else if(col==sz){
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
            }
            else{
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
            }
        }
       else{
            if(col == 1){
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
            }
            else if(col == sz){
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
            }
            else {
                if(matrix[row-1][col]==1) grid.union((row-1)*sz+col,(row-1)*sz+col+1);
                if(matrix[row-1][col-2]==1) grid.union((row-1)*sz+col,(row-1)*sz+col-1);
                if(matrix[row-2][col-1]==1) grid.union((row-1)*sz+col,(row-2)*sz+col);
                if(matrix[row][col-1]==1) grid.union((row-1)*sz+col,(row)*sz+col);
            }
        }
    }
    public boolean isOpen(int row, int col){
        return  matrix[row-1][col-1]==1;
    }
    public boolean isFull(int row, int col){
        return grid.find((row-1)*sz+col)==0;
    }
    public int numberOfOpenSites() {
        int count = 0;
        for(int i =0; i<sz; i++){
            for(int j = 0;j<sz; ++j){
                if (matrix[i][j]==1) count++;
            }
        }
        return count;
    }
    public boolean percolates() {
        return grid.connected(0,sz*sz+1);
    }

    public static void main(String[] args) {

    }  // test client (optional)
}
