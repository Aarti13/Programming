import java.io.*;
import java.util.*;

public class Main {

// TC : O(n^2)

//  s1 = abcdfg  s2 = aebdgh
//  Longest common subsequence = abdg
//  Minimum Cost To Make Two Strings Identical 
// (LCS - length of s1 * cost of s1 deleteing 1 char ) + (LCS - length of s2 * cost of s2 deleteing 1 char )
	public static int solution(String s1, String s2, int c1, int c2) {
        
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp= new int[s1.length()+1][s2.length()+1];
        for( int i=1 ; i<dp.length ; i++ ){
            for( int j=1 ; j<dp[0].length ; j++ ){
                dp[i][j] = ( s1.charAt(i-1) == s2.charAt(j-1) ) ? dp[i-1][j-1] + 1 : 
                            Math.max( dp[i][j-1] , dp[i-1][j] );
            }
        }
        int ans =  (n1 - dp[n1][n2] )*c1 + (n2 - dp[n1][n2] )*c2 ;
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		int x = scn.nextInt();
		int y = scn.nextInt();
		System.out.println(solution(s1, s2,x, y));
	}

}