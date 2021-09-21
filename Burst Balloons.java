import java.io.*;
import java.util.*;

public class Main {

// if(gap == 0)  only 1 balloon cost : 1*value*1  
//                                     dp[i][j] = arr[i]

// if( gap  == 1) two ballon :  2 3  : max( 1*2*3 + 1*3*1  , 2*3*1 + 1*2*1 )
//                                     max( arr[i]*arr[j] + arr[i] , 
//                                          arr[i]*arr[j] + arr[j] )        

// 3 balloon
// 1 2 3 
// 1 : cost 1*1*2
// 2 : cost 1*2*3


//  1 2 3 , 2 1 3 , 3 2 1 , 3 1 2 , 1 3 2 , 2 3 1 
// max (cst 1*1*2 + 1*2*3 + 1*3*1 , 
//          1*2*1 + 1*1*3 + 1*3*3 ,
//          1*3*2 + 1*2*1 + 1*1*1 ,
//          .. .. )

// 1 2 3 4 
// f(0) + 1 cost(1*1*2) + f(234)  
// f(1) + 2 cost (1*2*3) + f(34)
//...


// gap loop
// i=0 , j=gap ; j<n     i,j loop
// dp[i][j] loop   k=i ; k<=j ; k++
//               max : // particular ele ( (i==0) ? 1 : arr[i-1] ) * arr[k] * ( (j == n-1) ? 1 : arr[j+1] )             


// 231546 
// aakhri ballon jo phutegaaa
// 2 : 0 + f(31564) + 2 ki brust cost (1*2*1)
// 3 : f(2) + 3(1*3*1) + f(1546)
// 1 : f(23) + 1 + f(546)


// 2 3 1 5 6 4 => array mein se 2(3156)4  ie : 
  public static int solution(int[] arr) {
    int n = arr.length;  
    int[][] dp= new int[n][n];
        
        for( int gap=0 ; gap<n ; gap++ ){
            for( int i=0 , j=gap ; j<n ; i++ , j++ ){
                    int max= Integer.MIN_VALUE;
                    
                    for( int k=i ; k<= j ; k++){
                        int val = ( i==0  ? 1 : arr[i-1] ) * arr[k] * (j==n-1 ? 1 : arr[j+1] ) ;
                        int left = (k==i) ? 0 : dp[i][k-1];
                        int right = (k == j) ? 0 : dp[k+1][j];
                        
                        max = Math.max( left+right+val , max);
                    }
                    dp[i][j] = max;
                }
            }
    return dp[0][n-1];
  }
    
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
    }
    System.out.println(solution(arr));
  }

}