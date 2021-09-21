import java.io.*;
import java.util.*;

public class Main {

// Longest Common Substring
// pqabcxy  ,  xyzabcp : abc(3)
// compare all substring of s1 to s2 and find length of similar suffix 
//
//																				max length
// p        compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: xyzabcp       1
// pq       compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp 	: 0				0
// pqa      compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: xyza			1
// pqab     compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: xyzab			2
// pqabc    compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: xyzabc		3
// pqabcx   compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: x				1
// pqabcxy  compared with x,xy,xyz,xyza,xyzab,xyzabc,xyzabcp	: xy			2

// ans max length : 3 abc

	public static int solution(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        int max = Integer.MIN_VALUE;
        
        int[][] dp = new int[n1+1][n2+1];
        for( int i=1 ; i<=n1 ; i++){
            for( int j=1 ; j<=n2 ; j++){
                dp[i][j] = ( s1.charAt(i-1) == s2.charAt(j-1) ) ? dp[i-1][j-1] + 1 : 0 ;
                max = Math.max( max , dp[i][j] );
            }
        }
		return max;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		System.out.println(solution(s1,s2));
	}

}