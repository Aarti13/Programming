import java.io.*;
import java.util.*;

public class Main {
    
    //Brute force: find all substring then chcek for palindrome in each substring
    // TC: n(n+1)/2 * n = n^3
    
    // sol: TC: O(n^2)
    // fill true if same ele and check for diagonal one also count at the same time if true happens
    // fill false if diff
    
    public static void CountPalindromicSubstrings(String str){
        
        int c=0;
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        
        for( int gap=0; gap<n ; gap++){
            for( int i=0 , j=gap ; j<n ; i++ , j++){
                if( gap == 0 ) { dp[i][j] = true; c++; }
                else if( gap == 1 ) { 
                    if( str.charAt(i) == str.charAt(j) ) { dp[i][j] = true; c++; }
                    else  dp[i][j] = false; 
                }
                else{
                    if( str.charAt(i) == str.charAt(j) ) { 
                        if( dp[i+1][j-1] == true ) { dp[i][j] = true; c++; }
                    }
                    else  dp[i][j] = false; 
                }
            }
        }
        System.out.println(c);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        CountPalindromicSubstrings(str);
    }

}