import java.io.*;
import java.util.*;

public class Main {
//Boolean Parenthesization
// You have to find the number of ways in which the expression can be parenthesized so that the value of expression evaluates to true.

//  T F T F
//   & | ^

// lfc = left false count , ltc = left true count
// rfc = right false count , rtc = right true count

//               Tc                             Fc
// op = &       ltc.rtc                         lfc.rtc + ltc.rfc + lfc.rfc
// op = |       ltc.rtc + ltc.rfc + lfc.rtc     lfc.rfc
// op = ^       ltc.rfc + lfc.rtc               lfc.rfc + ltc.rtc

//       0	&	1	|	2	^	3   --------True Count ---------------
//       T 		F		T		F
//0	T	1		0		2
//1	F	0		0		1
//2	T	0		0		1		1
//3	F	0		0		0		0

//       0	&	1	|	2	^	3  ---------false Count ---------------
//       T 		F		T		F
//0	T	0		1		1
//1	F	0		1		0		
//2	T	0		0		0		0
//3	F	0		0		0		1

// T & F | T : (T&F)|T , T&(F|T)

	public static int solution(String str1, String str2) {
        
        int n = str1.length();
        
        int[][] dpt = new int[n][n]; // true count DP
        int[][] dpf = new int[n][n]; // false count DP
        
        for( int gap=0 ; gap<n ; gap++ ){
            for( int i=0 , j=gap; j<n ; i++ , j++ ){
                if( gap == 0 ) {
                    dpt[i][j] = ( str1.charAt(i) == 'T' ) ? 1 : 0;
                    dpf[i][j] = ( str1.charAt(i) == 'F' ) ? 1 : 0;
                }
                else{
                    for( int k=i ; k<j ; k++ ){
                        char ch = str2.charAt(k) ;
                        int ltc = dpt[i][k];
                        int rtc = dpt[k+1][j];
                        int lfc = dpf[i][k];
                        int rfc = dpf[k+1][j];
                        
                        if( ch == '&' ){
                            dpt[i][j] +=  ltc*rtc;
                            dpf[i][j] +=  lfc*rfc + ltc*rfc + lfc*rtc ;
                        }
                        else if( ch == '|'){
                            dpt[i][j] +=  ltc*rtc + lfc*rtc + ltc*rfc ;
                            dpf[i][j] +=  lfc*rfc  ;
                        }
                        else if( ch == '^'){
                            dpt[i][j] +=  ltc*rfc + lfc*rtc ;
                            dpf[i][j] +=  lfc*rfc + ltc*rtc  ;
                        }
                    }
                }
            }
        }
		return dpt[0][n-1];
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s1 = scn.next();
		String s2 = scn.next();
		System.out.println(solution(s1, s2));
	}

}