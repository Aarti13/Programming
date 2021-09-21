//Tc : O( s1.length() * s2.length() )
// i s2 j = s1 
// s1  pepperatcoding  s2 pepcoding

// dp[s2][s1]
// if( s2[i] == s1[j] )  dp[i][j] = dp[i+1][j+1] 
// else  
//     insert : insert s2[i] in s1[j]       dp[i+1][j]
//     Delete : delete from s1[j]           dp[i][j+1] 
//     replace : replace s1[j] with s2[i]   dp[i+1][j+1]

//     min( dp[i+1][j] , dp[i][j+1] , dp[i+1][j+1] ) + 1    

import java.io.*;
import java.util.*;

public class Main {

// Edit Distance
// 	 Insert - You can insert any character in s1.
//   Remove - You can remove any character in s1.
//   Replace - You can replace any character in s1 with any other character.

// You have to find the minimum number of operations needed to convert s1 to s2.

	public static int solution(String str1, String str2) {
        
        int n2 = str1.length();
        int n1 = str2.length();
        int[][] dp = new int[n1+1][n2+1];
        
        for( int i=n1 ; i>=0 ; i-- ){
            for( int j=n2 ; j>=0 ; j-- ){
                if( i==n1 && j==n2 ) dp[i][j] = 0;  // shows both s1 and s2 are empty ie 0 no of ways
                else if( i==n1 ) dp[i][j] = dp[i][j+1]+1;      // shows s1 has value but s2 are empty ie j no of ways because we have to removal of char from s1 to convert into s2 which is empty
                else if( j==n2 ) dp[i][j] = dp[i+1][j]+1;      // shows s1 is empty but s2 has value ie i no of ways because we have to insert of char from empty s1 to convert into s2 which as char
                else{
                    if( str1.charAt(j) == str2.charAt(i) ) dp[i][j] = dp[i+1][j+1]; // remove both find in diagonal 
                    else{
                        dp[i][j] = Math.min( dp[i+1][j+1] , Math.min( dp[i][j+1] , dp[i+1][j] ) ) + 1; // all three operations insert , delete , and replace
                    }
                }
            }
        }
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		System.out.println(solution(s1,s2));
	}

}