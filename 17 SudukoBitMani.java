// import java.io.*;
import java.util.*;

public class Main {

  public static void display(int[][] arr){
    for (int ii = 0; ii < arr.length; ii++) {
      for (int jj = 0; jj < arr.length; jj++) {
        System.out.print(arr[ii][jj] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void solveSudoku(int[][] arr, int[] rows, int[] cols, int[][] grid, int i, int j) {
      if( i == arr.length ){
          display(arr);
          return;
      }
      int nr = 0 ;
      int nc = 0;
      if(j < arr.length-1 ){
          nr = i;
          nc = j+1;
      }
      else{
          nr = i+1;
          nc = 0;
      }
      
      if( arr[i][j] == 0 ){
          for( int k =1; k<10 ; k++ ){
              int bmask = 1 << k;
               if( ( bmask &  rows[i] ) == 0 && ( bmask &  cols[j] ) == 0  && 
                   ( bmask &  grid[i/3][j/3] ) == 0  ){
                    
                    rows[i] |= bmask;
                    cols[j] |= bmask;
                    grid[i/3][j/3] |= bmask;
                    arr[i][j] = k;
                    solveSudoku( arr , rows,cols,grid , nr,nc);
                    arr[i][j] = 0;
                    rows[i] ^= bmask;
                    cols[j] ^= bmask;
                    grid[i/3][j/3] ^= bmask;
                  }
          } 
      }
      else solveSudoku( arr , rows,cols,grid , nr,nc);
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int[][] arr = new int[9][9];
    int[] rows = new int[9];
    int[] cols = new int[9];
    int[][] grid = new int[3][3];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int digit = scn.nextInt();
        arr[i][j] = digit;
        rows[i] |= (1 << digit);
        cols[j] |= (1 << digit);
        grid[i / 3][j / 3] |= (1 << digit);
      }
    }

     solveSudoku(arr, rows, cols, grid, 0, 0);
  }

}