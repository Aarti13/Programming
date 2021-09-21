import java.io.*;
import java.util.*;

public class Main {

// Optimal Strategy For A Game
// max( arr[i] + min( (i+2 ,j) , (i+1, j-1) ) , arr[j] + min( (i+1,j-1), (i+1,j-2) )
// TC : O(n^2)
	public static void optimalStrategy(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n][n];
        
        for( int gap=0 ; gap< n ; gap++){
            for( int i=0 , j=gap ; j<n ; j++ , i++ ){
                if( gap == 0) dp[i][j] = arr[i];
                else if( gap == 1 ) dp[i][j] = Math.max( arr[i] , arr[j] );
                else{
                    dp[i][j] = Math.max( Math.min( dp[i+1][j-1], dp[i+2][j] ) + arr[i] , 
                                         Math.min( dp[i+1][j-1], dp[i][j-2] ) + arr[j] ) ;
                }
                 //System.out.print(dp[i][j]+"  ");
            }
            // System.out.println();
        }
        System.out.println(dp[0][n-1]);
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = scn.nextInt();
		}
		optimalStrategy(a);
	}

}