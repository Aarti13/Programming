import java.io.*;
import java.util.*;

public class Main {

  public static void solution(boolean[][] board, int row, int cols, int ndiag, int rdiag, String asf) {
    
    if( row == board.length ){
        System.out.println(asf+".");
        return;
    }
    for( int c=0 ; c<board.length ; c++ ){
        
        if( ( (1<<c) & cols) == 0 && ( ( 1<< (row + c)) & ndiag ) == 0 && 
            ( ( 1<< ( row -c + board.length -1 )) & rdiag ) == 0 ) {
            
            board[row][c]=true;
            cols ^= (1<<c) ;
            ndiag ^= ( 1<< (row + c) )  ;
            rdiag ^= ( 1<< (row - c + board.length -1 ) );
            solution(board , row+1,cols , ndiag , rdiag, asf+row+"-"+c+", " );
            board[row][c]=false;
            cols ^= (1<<c) ;
            ndiag ^= ( 1<< (row + c) )  ;
            rdiag ^= ( 1<< (row - c + board.length -1 ) );
        }
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    boolean[][] board = new boolean[n][n];
    int cols = 0;
    int ndiag = 0;
    int rdiag = 0;
    solution(board, 0, cols, ndiag, rdiag, "");
  }

}