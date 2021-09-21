import java.io.*;
import java.util.*;

public class Main {

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j){
        // level : queen q
        // choices : box
        // combination using permuatation nCr = nPr / r!
        // ie in n*n if we select 1st row den baki ki queens next rows min baithenge taki duplicacy hta skee
        
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
        
        // same row mein toh queens sirf aage wale col++++
        // den row+1 , 0---col tak baith sakte ai sirf
        // toh col ke liye
        for( int c=j+1; c<chess.length ; c++){
            chess[i][c] = true;
            queensCombinations(qpsf+1,tq,chess,i,c);
            chess[i][c] = false;    
        }
        //next row ke liye loop
        for(int r=i+1; r<chess.length ; r++ ){
            for(int c=0 ; c<chess[0].length ; c++ ){
                chess[r][c] = true;
                queensCombinations(qpsf+1,tq,chess,r,c);
                chess[r][c] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];
        
        queensCombinations(0, n, chess, 0, -1);
    }
}