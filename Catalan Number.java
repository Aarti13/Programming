import java.io.*;
import java.util.*;

public class Main{

// catalan number 2nCn / n+1
//  c0 = 1 , c1 = 1 , c2 = c0*c1 + c1*c0 , c3 = c0*c2 + c1*c1 + c2*c0 , c4 = ......
//  
public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    
    // int[] dp = new int[n+1];
    // dp[0]=1;
    // dp[1]=1;
    // for( int i=2 ; i<=n ; i++){
    //     for( int j=0 ; j<i ; j++ ){
    //         dp[i] += dp[j]*dp[i-j-1]; 
    //     }
    // }
    // System.out.println(dp[n]);
    
    //8C5 = 8!/5!*3! = 8*7*6*5!/5!*3! = 8*7*6/3*2*1 normal combination
    //2nCn / n+1
	
	
	
    int r=n;
    n=2*n;
    int res=1;
    for(int i=0;i<r;i++)
     {
        res*=(n-i);
        res/=(i+1);
     }
    System.out.println( (res)/(r+1) );
    
}
}