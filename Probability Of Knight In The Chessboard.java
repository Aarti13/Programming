import java.io.*;
import java.util.*;

public class Main {

//Probability Of Knight In The Chessboard
// places where knight can move are 
    
    public static int[][] dir = {{-2,1} , {-1,2} , {1,2} , {2,1} , {2,-1} , {1,-2} , {-1,-2},{-2,-1}};
	public static boolean isSafeToPlaceKnight( int r , int c , int n){
	    if( r<n && c<n && r>=0 && c>=0 ) return true;
	    return false;
	}
	public static void solution(int r, int c, int n, int k) {
        
        double[][] currdp = new double[n][n];
        double[][] nextdp = new double[n][n];
        
        currdp[r][c] = 1;
        for( int x=1 ; x<=k ; x++ ){  // for k times 
            for( int i=0 ; i<n ; i++){ // n*n loop 
                for( int j=0 ; j<n ; j++ ){
                    
                    if( currdp[i][j] != 0 ){
                       
                        // direction loop
                        for( int d=0 ; d<dir.length ; d++ ){
                            int r1 = dir[d][0] + i;
                            int c1 = dir[d][1] + j;
                            if( isSafeToPlaceKnight(r1,c1,n)){
                                nextdp[r1][c1] += currdp[i][j] / 8.0 ;
                            }
                        }
                    }
                }
            }
            currdp = nextdp;
            nextdp = new double[n][n];
        }
        
        double sum=0;
        for( int i=0 ; i<n ; i++){ // n*n loop 
                for( int j=0 ; j<n ; j++ ){
                    sum += currdp[i][j] ;
            }
        }
        System.out.println(sum);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int k = scn.nextInt();
		int r = scn.nextInt();
		int c = scn.nextInt();
		solution(r, c, n, k);
	}

}