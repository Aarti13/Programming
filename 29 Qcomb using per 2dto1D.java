import java.io.*;
import java.util.*;

public class Main {

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        // 2D to 1D conversion
        //using permutation method nCr = nPr / r!
        // level queen , choice box
        // if 1 queen is placed in box 1 den queen 2 will be placed in 2,3...n*n box
        
        if( qpsf == tq ){
            for(int r=0;r<chess.length ; r++ ){
                for(int c=0 ; c<chess[0].length ; c++ ){
                    if(chess[r][c] == true ) System.out.print("q	");
                    else System.out.print("-	");
                }
                System.out.println();
            }
            System.out.println();
          return;    
        }
        for( int i=lcno+1 ; i<chess.length*chess.length ; i++){
            int r = i/chess.length ;
            int c = i%chess.length ;
            chess[r][c] = true;
            queensCombinations(qpsf+1,tq,chess,i);
            chess[r][c] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        queensCombinations(0, n, chess, -1);
    }
}