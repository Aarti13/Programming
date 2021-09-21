import java.io.*;
import java.util.*;

public class Main {
    
    //Longest Common Subsequence 
    // brute force :
    // find all the subsequence of string 1 : 2^(s1.length)
    // find all the subsequence of string 1 : 2^(s2.length)
    // then find all the string whic are common ie : n*n loop
    // TC: 2^(s1.length) * 2^(s2.length)
    
    // sol
    // make 2D matrix cols rep s1 rows rep s2
    // if( s1.charAt(i) == s2.charAt(j) ) dp[i][j] = dp[i-1][j-1] + 1
    // else dp[i][j] = max (dp[i-1][j] , dp[i][j-1] ) 
    // res dp[n-1][n-1]
    public static void LongestCommonSubsequence(String s1 , String s2){
        
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp= new int[n1+1][n2+1];
        
        for( int i=1 ; i<=n1 ; i++){
            for( int j=1; j<= n2 ; j++){
                if( s1.charAt(i-1) == s2.charAt(j-1) ) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1] ) ;
            }
        }
        System.out.println( dp[n1][n2] );
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        
        LongestCommonSubsequence(s1,s2);
    }

}