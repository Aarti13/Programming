import java.io.*;
import java.util.*;

public class Main {

// abccbc  : 2 = a bccb c
// a bccbc , ab ccbc , abc cbc , abcc bc , abccb c 
// 0+( bccbc : bccb c = 1)+1  , 1 + 1 + 1 , 2 + 0 + 1 , 2 + 1 + 1 ,  1 + 0 + 1
// min (2, 3, 3, 4, 2) => 2


	public static int minPalindromicCut(String s) {
        // left and right cut + add khudka cut
        // TC: O(n^3)
        int n = s.length();
        boolean[][] dpisP = new boolean[n][n];
        for( int gap=0 ; gap<n ; gap++){
            for( int i=0, j =gap ; j<n ; i++,j++){
                if( gap == 0 ) dpisP[i][j] = true;
                else if( gap == 1 ) dpisP[i][j] = ( s.charAt(i) == s.charAt(j) ) ? true : false;
                else{
                    if( s.charAt(i) == s.charAt(j) ) dpisP[i][j] = ( dpisP[i+1][j-1] ) ? true : false;
                    else dpisP[i][j] = false;
                }
            }
        }
        // int[][] dp = new int[n][n];
        // for( int gap=0 ; gap<n ; gap++){
        //     for( int i=0, j =gap ; j<n ; i++,j++){
                
        //         if( gap == 0 ) dp[i][j] = 0;
        //         else if( gap == 1 ) dp[i][j] = ( dpisP[i][j] ) ? 0 : 1;
        //         else {
        //             if( dpisP[i][j] ) dp[i][j] = 0;
        //             else{
        //                 int min = Integer.MAX_VALUE;
        //                 for( int k =i ; k<j ; k++ ){
        //                     min = Math.min( min , dp[i][k]+ dp[k+1][j] + 1);
        //                 }    
        //                 dp[i][j] = min; 
        //             }
        //         }
        //     }
        // }
        
		//  a b c c b c
		
        // TC : O(n*n)
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = (s.charAt(0) == s.charAt(1) ) ?  0 :  1;
        
        for( int i=2 ; i<n ; i++ ){
            
            if( dpisP[0][i] ) dp[i] = 0 ;
            else{
               int min = Integer.MAX_VALUE;
                for( int j=i ; j>0 ; j-- ){
                    if( dpisP[j][i] )  min = Math.min(dp[j-1] , min );
                }
                dp[i] = min + 1; 
            }
            
        }
        
        // for( int i=0 ; i<n ; i++){
        //     System.out.print( dp[i] +"  " );
        // }
	
        
        
//         for( int i=0 ; i<n ; i++){
//             for( int j=0 ; j<n ; j++){
//                 System.out.print( dp[i][j] +"  " );
//             }
//             System.out.println();
//         }
// 		//return dp[0][n-1];
		
		return dp[n-1];
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		System.out.println(minPalindromicCut(str));
	}
}