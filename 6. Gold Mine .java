import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        
        int[][] arr = new int[m][n];
        
        for( int i=0;i<m ; i++){
            for( int j=0; j<n ; j++)
                arr[i][j] = sc.nextInt();
        }
        
        int[][] dp= new int[m][n]; 
        for( int j=n-1 ; j>=0; j--)
       {
         for( int i=m-1 ; i>=0 ; i--)   
           {
                    
            if( j == dp[0].length-1 )  dp[i][j] = arr[i][j];
            else if( i ==0 )  dp[i][j] = Math.max( dp[i][j+1] , dp[i+1][j+1] ) + arr[i][j];
            else if( i == dp.length-1 )  dp[i][j]  = Math.max( dp[i][j+1] , dp[i-1][j+1] ) + arr[i][j] ;
            else 
            dp[i][j] = Math.max( Math.max(  dp[i][j+1] , dp[i+1][j+1] ) , Math.max( dp[i][j+1] , dp[i-1][j+1] ) ) + arr[i][j];
                      
            }
        }
        int max = 0;
        for( int i=0;i<dp.length ; i++ ){
            if( max < dp[i][0] )
                max = dp[i][0];
        }
        System.out.println( max );
    }

}