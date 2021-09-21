import java.io.*;
import java.util.*;

public class Main {

    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        // level Queens no 1,2,....
        //choices mein konse box mein placed hogi
        // after placing one queen we left with n*n-1 boxes ....
        // 	 tab 
        if(qpsf>tq){
            for( int r=0 ; r<tq; r++){
                 for( int c=0 ; c<tq ; c++){
                    if( chess[r][c] !=0) System.out.print("q"+chess[r][c]+"	");
                    else System.out.print("-	");
                 }
            System.out.println();
            }
        System.out.println();
            return;
        }
        for( int r=0 ; r<tq; r++){
            for( int c=0 ; c<tq ; c++){
                if(chess[r][c] == 0 ){
                    chess[r][c]=qpsf;
                    queensPermutations(qpsf+1,tq,chess);
                    chess[r][c]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] chess = new int[n][n];
        
        queensPermutations(1, n, chess);
    }
}