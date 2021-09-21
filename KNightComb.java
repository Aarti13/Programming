import java.io.*;
import java.util.*;

public class Main {
    
    public static int[][] dir = {{-1,-2},{-2,-1},{-2,1},{-1,2} };
    public static boolean IsKnightSafe(boolean[][] chess, int i, int j) {
        
        for(int x=0 ; x<dir.length ; x++){
            int r = dir[x][0]+i ;
            int c = dir[x][1]+j ;
            
            if(r>=0 && c>= 0 && r< chess.length && c<chess[0].length && chess[r][c] == true ) return false;
        }
        return true;
    }

    public static void nknights(int kpsf, int tk, boolean[][] chess, int lcno) {
        if (kpsf == tk) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "k	" : "-	");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] == false && IsKnightSafe(chess, row, col)) {
                chess[row][col] = true;
                nknights(kpsf + 1, tk, chess, i);
                chess[row][col] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        nknights(0, n, chess, -1);
    }
}