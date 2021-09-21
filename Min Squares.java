import java.io.*;
import java.util.*;

public class Main {

	public static int MinSquares(int n){
        int c=0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i=2; i<=n ; i++){
            dp[i] = Integer.MAX_VALUE;
            for( int j = 1 ; j*j <=i ; j++){
                if( (i-(j*j)) >= 0){
                    dp[i] = Math.min(dp[i-j*j]+1 , dp[i]);
                }
            }
        }
		return dp[n];
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		System.out.println(MinSquares(n));
	}


	
}Min SquaresMin Squares