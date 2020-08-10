// 1st method with O(n*n) 

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] val = new int[n];
        for( int i = 0; i< n ; i++) val[i] = sc.nextInt();
        int tar = sc.nextInt();
        
        int[][] dp = new int[tar+1][n];
        for( int t = 1 ; t <= tar  ; t++){  // t : no of transactions
            for( int d = 1; d < dp[0].length ; d++ ){  // for particular day
                
                int max = dp[t][d-1];
                for(int pd = 0 ; pd < d ; pd++) //pd: previous day
                {
                    int ptilltm1 = dp[t-1][ pd]; // prev transaction till t-1
                    int pt  = val[d] - val[pd];     // pth transaction;
                    
                    if( ptilltm1 + pt > max ) max = ptilltm1 + pt ;
                    
                }
                dp[t][d]= max;
            }
        }
        System.out.println( dp[tar][n-1]);
    }

}

//second method O(n*n)


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] val = new int[n];
        for( int i = 0; i< n ; i++) val[i] = sc.nextInt();
        int tar = sc.nextInt();
        
        int[][] dp = new int[tar+1][n];
        for( int t = 1 ; t <= tar  ; t++){  // t : no of transactions
             int max = Integer.MIN_VALUE ;
            for( int d = 1; d < dp[0].length ; d++ ){  // for particular day
            
                if( max < dp[t-1][d-1] - val[d-1]) max = dp[t-1][d-1] - val[d-1];
                 
                if( max + val[d] > dp[t][d-1]   ) dp[t][d]= max + val[d] ;
                else dp[t][d] = dp[t][d-1];
            }
        }
        System.out.println( dp[tar][n-1]);
    }

}