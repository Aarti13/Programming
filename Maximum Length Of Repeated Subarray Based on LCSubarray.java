import java.io.*;
import java.util.*;

public class Main {

// Maximum Length Of Repeated Subarray
// s1 :  5 4 3 2 1    s2 :  7 8 4 3 2 5
// common subarray length : 432 (3)

// here we compare all prefix of s1 to all prefix of string 2 and calculate the length of suffix
//    s1 prefix             s2 prefix                   suffix of same   length
// ie 5 compraed with       7,78,784,7843,78432,784325  784                1
//    54 compared with      7,78,784,7843,78432,784325  784                 1
//    543 compared with     7,78,784,7843,78432,784325  7843                2
//    5432 compared with    7,78,784,7843,78432,784325  78432               3
//    54321 compared with   7,78,784,7843,78432,784325  no one 

  public static int solution(int[] arr1, int[] arr2) {
    
    int n1 = arr1.length ;
    int n2 = arr2.length ;
    
    int[][] dp = new int[n1+1][n2+1];
    int max = Integer.MIN_VALUE;
    
    for( int i=1 ; i<=n1 ; i++){
        for( int j=1 ; j<=n2 ; j++ ){
            if( arr1[i-1] == arr2[j-1] ) {
                dp[i][j] = dp[i-1][j-1]+1;
                max = Math.max(max , dp[i][j] );
            }
            else dp[i][j] = 0;
        }
    }
    return max;
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr1 = new int[n];
    for (int i = 0 ; i < n; i++) {
      arr1[i] = scn.nextInt();
    }

    int m = scn.nextInt();
    int[] arr2 = new int[m];
    for (int i = 0 ; i < m; i++) {
      arr2[i] = scn.nextInt();
    }
    System.out.println(solution(arr1, arr2));
  }

}