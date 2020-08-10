import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int np = sc.nextInt(); // no of people
        int nt = sc.nextInt(); // no of teams
        
        if( nt == 0 || np== 0 || np < nt ) {
            System.out.println(0);
            return ;
        }
        long[][] dp = new long[nt+1][np+1];
        
        for( int t =1 ; t<=nt ; t++){
          for( int p =1 ; p<=np ; p++){
            if( p < t ) dp[t][p] = 0;
            else if( p == t ) dp[t][p] = 1;
            else dp[t][p] = dp[t-1][p-1] + dp[t][p-1] * t;
          }
        }
        
        System.out.println(dp[nt][np] );
    }
}