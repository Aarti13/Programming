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
   
   public static void PrintAllPathsWithMaximumGold(int[][] arr ){
       int m = arr.length;
       int n = arr[0].length;
       
       int[][] dp = new int[m][n];
       for( int j=n-1 ; j>=0 ; j--){
           for( int i =m-1; i>=0 ; i--){
               if( j == n-1 ) dp[i][j] = arr[i][j];
               else if( i == m-1 ) dp[i][j] = Math.max(dp[i][j+1] , dp[i-1][j+1] )+ arr[i][j];
               else if( i == 0 ) dp[i][j] = Math.max(dp[i][j+1] , dp[i+1][j+1] )+ arr[i][j];
               else  dp[i][j] = Math.max( dp[i+1][j+1] , Math.max(dp[i][j+1] , dp[i-1][j+1] ) )+ arr[i][j];     
           }
       }
       
       int maxG=Integer.MIN_VALUE;
       int maxi=0;
       for( int i=0 ; i<m ; i++ ){
           if( maxG < dp[i][0] ) {
               maxi = i; 
               maxG = dp[i][0];
            }
       }
       System.out.println(maxG);
      // System.out.print(maxi+" ");

       // for printing
       // You are allowed to move 1 cell right-up (d1), 1 cell right (d2) or 1 cell right-down(d3).

       Queue<Pair> que = new ArrayDeque<>();
       que.add(new Pair(maxi+" " , maxi , 0) );
       
       while(que.size() > 0 ){
           Pair p = que.remove();
            
            int val = dp[p.i][p.j] - arr[p.i][p.j];
            
            if( p.j == n-1 ) System.out.println(p.psf);
            else {
            if( p.i-1 >=0 && val == dp[p.i-1][p.j+1] )  que.add(new Pair(p.psf+"d1 " , p.i-1 , p.j+1 ) );
            if( p.i <m && val == dp[p.i][p.j+1] )  que.add(new Pair(p.psf+"d2 " , p.i , p.j+1 ) );
            if( p.i+1 <m && val == dp[p.i+1][p.j+1] )  que.add(new Pair(p.psf+"d3 " , p.i+1 , p.j+1 ) );
            }
            } 
       
   }
   
   // max(a,b,c)
   // if(a<b) if( b<c ) c
  //          else b
  // else    if(a>c) a
  //          else c
  
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

    PrintAllPathsWithMaximumGold(arr);
}


}