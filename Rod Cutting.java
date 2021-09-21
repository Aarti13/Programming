import java.io.*;
import java.util.*;

public class Main {

//Rod Cutting
  public static int solution(int[] prices) {
    
    int n = prices.length;
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = prices[0];
    
    int max = Integer.MIN_VALUE;
     // for( int i=2 ; i<=n ; i++){
            //     for( int j = 1 ; j<=i ; j++){
    
            //cut - strategy
            //dp[i] = Math.max( dp[i] , prices[j-1]+dp[i-j]);
            
           
    for( int i=2 ; i<=n ; i++){
        for( int j = 1 ; j<=i/2 ; j++ ){
           
            // left right strategy
             dp[i] = Math.max( dp[i]  , dp[j]+ dp[i-j] ) ;
        }
        dp[i] = Math.max( dp[i] , prices[i-1]);
        //System.out.print(dp[i]+"  ");
    }
    return dp[n];
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] prices = new int[n];
    for (int i = 0; i < n; i++) {
      prices[i] = scn.nextInt();
    }
    System.out.println(solution(prices));
  }

}