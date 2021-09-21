import java.io.*;
import java.util.*;

public class Main {

//same : dp[i+1][j+1]
//diff : min ( dp[i][j+1]+col string cost , dp[i+1][j] + cost row string )

//t/ea = 314 => min (1st way  -/ea = 116 + 198 = 314 , 
//                  2nd way t/a  = 101 + 213 = 314 )


// ASCII s - 115 , t -116 ,  a -97,  e -101
//      s        e       a    - 
// e    231     116     217   314
// a    332     217    116  213
// t    429     314   213   116
// -    313    198    97    0


	public static int solution(String s1, String s2) {
        
        int n1 = s1.length();
        int n2 = s2.length();
        
        int[][] dp = new int[n1+1][n2+1];
        for( int i=n1 ; i>=0 ; i--){
            for( int j=n2 ; j>=0 ; j--){
                if( i== n1 && j==n2 ) dp[i][j] = 0;
                else if( i==n1 ) dp[i][j] = (char)(s2.charAt(j)) + dp[i][j+1] ;
                else if( j==n2 ) dp[i][j] = (char)(s1.charAt(i)) + dp[i+1][j] ;
                else{
                    if( s1.charAt(i) == s2.charAt(j) ) dp[i][j] = dp[i+1][j+1];
                    else{
                        dp[i][j] = Math.min( (char)s1.charAt(i)+dp[i+1][j] , (char)s2.charAt(j)+dp[i][j+1] );
                    }
                }
                //System.out.print(dp[i][j]+"  ");
            }
            //System.out.println();
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