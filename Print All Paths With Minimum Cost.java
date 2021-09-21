import java.io.*;
import java.util.*;

public class Main {

   private static class Pair {
      String psf;
      int i;
      int j;

      public Pair(String psf, int i, int j) {
         this.psf = psf;
         this.i = i;
         this.j = j;
      }
   }

    // stoarge : each cell wha se end tak ka min cost kitna hoga
    
    public static void PrintAllPathsWithMinimumCost(int[][] arr ){
        
        int m = arr.length; // i , h
        int n = arr[0].length; // j , v
        int[][] dp = new int[m][n];
        
        for( int i=m-1 ; i>=0 ; i--){
            for( int j =n-1 ; j>=0 ; j--){
                if( i == m-1 && j == n-1 ) dp[i][j] = arr[i][j]; // (n,n)
                else if( i == m-1 ) dp[i][j] = dp[i][j+1] + arr[i][j]; // Horizontal
                else if( j == n-1 ) dp[i][j] = dp[i+1][j] + arr[i][j]; // Vertical
                else{
                    dp[i][j] = Math.min( dp[i][j+1] , dp[i+1][j] ) + arr[i][j] ;// min(Horizontal , Vertical ) + khud ka cost
                }
                
                //System.out.print(dp[i][j] +"  ");
            }
            //System.out.println();
        }
        System.out.println(dp[0][0]);
        // backtracking use karengeee
        // que banaengee : add kisko karengee woh decide min(h,v) 
        
        Queue<Pair> que = new ArrayDeque<>();
        que.add(new Pair("",0,0 ) );
        
        while( que.size() > 0 ){
            Pair p = que.remove();
            int h = p.i;
            int v = p.j;
           // m*n 
            if( h==m-1 && v==n-1 ) System.out.println( p.psf);
            else if( v == n-1 ) { h++ ; que.add(new Pair(p.psf+"V",h ,v ) ); }
            else if( h == m-1 ) { v++ ; que.add(new Pair(p.psf+"H",h,v ) ); }
            else{
                if( dp[h][v+1] < dp[h+1][v] ) { v++ ; que.add(new Pair(p.psf+"H",h,v ) );}
                else if( dp[h][v+1] == dp[h+1][v] ){
                    que.add(new Pair(p.psf+"V",h+1,v ) );
                    que.add(new Pair(p.psf+"H",h,v+1 ) );
                }
                else   { h++; que.add(new Pair(p.psf+"V",h ,v ) ); }
            }
        }
    }
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int m = Integer.parseInt(br.readLine());
      int[][] arr = new int[n][m];

      for (int i = 0; i < n; i++) {
         String str = br.readLine();
         for (int j = 0; j < m; j++) {
            arr[i][j] = Integer.parseInt(str.split(" ")[j]);
         }
      }

       PrintAllPathsWithMinimumCost(arr); 
   }

}