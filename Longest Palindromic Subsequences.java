import java.io.*;
import java.util.*;

public class Main {
    
    // only upper diagonal will fill because start can not be > end position
    
    // LPS : 
    public static void LongestPalindromicSubsequences(String str){
        
        int n = str.length();
        int[][] dp = new int[n][n];
        
        for( int gap=0 ; gap<n ; gap++){
            for( int i=0 , j=gap ; j<n ; i++ , j++){
                if( gap == 0 ) dp[i][j] = 1;
                else if( gap == 1) dp[i][j] = ( str.charAt(i) == str.charAt(j) ) ? 2 : 1 ;
                else{
                   if( str.charAt(i) == str.charAt(j) ) dp[i][j] = dp[i+1][j-1] + 2;
                   else dp[i][j] = Math.max( dp[i+1][j] , dp[i][j-1] );
                }
            }
        }
        
        System.out.println(dp[0][n-1]);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        LongestPalindromicSubsequences(str);
    }

}