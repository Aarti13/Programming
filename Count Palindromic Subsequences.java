import java.io.*;
import java.util.*;

public class Main {
    
    // Count Palindromic Subsequences
    // ccb : ans 5 
    // cc( 3: c , c, cc ) , cb(2 : c, b )
    // ans : 3+2 -1 (why?? -1 because c is repeating )
    // if( both end points are diff den ) : dp[i][j] = dp[i+1][j] + dp[i][j-1]  - dp[i+1][j-1] 
    
    // bcdeb : ans 9
    // bcde ( 4 : b,c,d,e) , cdeb( 4: c,d,e,b )
    //  ans : 4 + 4 - cde( 3:c,d,e ) + 1:bb (same endpoints) + 3:( bcb, bdb, beb )
    // finally left + right + 1 because middle one + , -
    
    public static void CountPalindromicSubsequences(String str){
        int n = str.length();
        int[][] dp = new int[n][n];
        
        for( int gap =0 ; gap< n ; gap++){
            for( int i=0 , j=gap ; j<n ; i++,j++){
                if( gap == 0 ) dp[i][j] = 1;
                //else if( gap == 1 ) dp[i][j] = ( str.charAt(i) == str.charAt(j) ) ? 3 : 2 ;
                else {
                  if( str.charAt(i) == str.charAt(j) )  
                       dp[i][j] = dp[i+1][j] + dp[i][j-1] + 1;
                  else dp[i][j] = dp[i+1][j] + dp[i][j-1]  - dp[i+1][j-1] ;
                }
            }
        }
        System.out.println(dp[0][n-1]);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        CountPalindromicSubsequences(str);
    }

}