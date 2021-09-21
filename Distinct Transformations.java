import java.io.*;
import java.util.*;

public class Main {

// TC : O(s1*s2 length )
// s1/s2   convert s1 to s2
// abc/abc : convert ways is 1 no char removal
// abc/kvh , abc/sdffg  0 ways 

// if char not equals xabc/abc =>  delete x from xabc  :    abc/abc ie dp[i][j+1]
// if char equals aabc/abc => 2 ways    a.bc ,  .abc 
//                            1st way a ko hta do   : abc/abc  :   ie dp[i][j+1]
//  						  2nd way a ko rakhlo   : aabc/abc recursion ko small krne ke liye 
//                                                    cancel a from both string      abc/bc dp[i+1][j+1]
	public static int solution(String s, String t) {
        
        int ns = s.length();
        int nt = t.length();
        
        int[][] dp = new int[nt+1][ns+1];
        for( int i=nt ; i>=0 ; i--){
            for( int j=ns ; j>=0 ; j--){
                if( i==nt && j==ns ) dp[i][j] = 1;
                else if( i==nt ) dp[i][j] = 1;
                else if( j==ns ) dp[i][j] = 0;
                else{
                    if( t.charAt(i) == s.charAt(j) ) 
                        dp[i][j]  = dp[i+1][j+1] + dp[i][j+1];
                    else dp[i][j] = dp[i][j+1];
                }
               // System.out.print(dp[i][j]+" ");
            }
           // System.out.println();
        }
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		System.out.println(solution(s1, s2));
	}

}