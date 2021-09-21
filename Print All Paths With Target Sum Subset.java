import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int i;
        int j;
        String psf;

        public Pair(int i, int j, String psf){
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }
    // dp for target sum pairs:
    // 2D banegaa subset nikal ke sum karenge 
    // sbset matlab no subsequence has to be in 
    
    // target : 10
    // dp[n+1][tar+1]
    // storage : i,j i tak ka subset j target bana skta hai ki nhi 
    // dp[i][j] =  ( dp[i-1][j-arr[i-1]] , dp[i-1][j] ) ? T : F
    
    //       0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10
    //    0  T   F   F   F   F   F   F   F   F   F   F
    // 0  4  T   F   F   F   T   F   F   F   F   F   F
    // 1  2  T   F   T   F   T   F   T   F   F   F   F
    // 2  7  T   F   T   F   T   F   T   T   F   T   F
    // 3  1  T   T   T   T   T   T   T   T   T   T   T   
    // 4  3  T   T   T   T   T   T   T   T   T   T   T
    
    // Path:
    // 
    
    public static void PrintAllPathsWithTargetSumSubset(int[] arr , int tar){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][tar+1];
        
        for( int i=0 ; i<= n ; i++){
            for( int j=0 ; j<= tar ; j++){
                if( i==0 && j==0 ) dp[i][j] = true;
                else if( i==0 ) dp[i][j] = false;
                else if( j==0 ) dp[i][j] = true;
                else{
                    if ( j-arr[i-1] >= 0 ){
                        dp[i][j] = (dp[i-1][j-arr[i-1]] || dp[i-1][j]  );
                    }
                    else dp[i][j] = dp[i-1][j];
                }
           // System.out.print(dp[i][j]+" ");
            }
           // System.out.println();
        }
        
        System.out.println( dp[n][tar] );
        // path
        // follow true values : if( j-arr[i-1] ) true to add pasf value i else no pasf add
        
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair(n , tar , ""));
        
        while(que.size() > 0 ){
            Pair p = que.remove();
            int m = p.i-1 ;
            if( p.i == 0 || p.j == 0 ) System.out.println(p.psf);
            else{
                if( p.j-arr[p.i-1] >= 0 && dp[p.i-1][p.j-arr[p.i-1]] == true )  
                    que.add(new Pair(p.i-1 , p.j-arr[p.i-1] , m+" "+p.psf ));
                if( dp[p.i-1][p.j] == true ) que.add(new Pair(p.i-1 , p.j , p.psf ));
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int tar = Integer.parseInt(br.readLine());
        PrintAllPathsWithTargetSumSubset(arr , tar);
    }
}