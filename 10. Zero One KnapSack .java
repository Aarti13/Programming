import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        
        for( int i=0;i<m ; i++){
                val[i] = sc.nextInt();
        }
        
        int[] wt = new int[m];
        
        for( int i=0;i<m ; i++){
                wt[i] = sc.nextInt();
        }
        int cap = sc.nextInt();
        
        int[][] dp= new int[m+1][cap+1]; 
        
        for( int i = 1; i < dp.length ; i++ ){
            for( int j =1 ; j < dp[0].length ; j++ ){
                
               if( j >= wt[i-1] ){
                int rcap = j - wt[i-1] ; 
                if( dp[i-1][rcap] + val[i-1] > dp[i-1][j] ) dp[i][j] = dp[i-1][rcap] + val[i-1];
                else dp[i][j] = dp[i-1][j] ; 
               }
               else dp[i][j] = dp[i-1][j] ;  
            }
        }
                      
        System.out.println( dp[dp.length-1][dp[0].length-1] );
    }
}