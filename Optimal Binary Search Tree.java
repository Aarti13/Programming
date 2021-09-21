import java.io.*;
import java.util.*;

public class Main {
  
  // abc = (root)  -a-(b,c) ,            (a)-b-(c) ,             (a,b)-c-
  //                0+bc+[a'+b'+c']  vs  a+c+[a'+b'+c'] 'freq   vs     ab+0+[a'+b'+c']
  //              b-c     ,    b-c
  //            b'+2c'   vs      c'+2b'  select min (b-c)
  //            b-c converted to a-b-c then all level inc by 1
  //     so, c'+2b' = > a'+2c'+3b'  for this conversion write  [a'+b'+c'] 
  
  // 1 2 3
  // 2 4 5
  
  // 123  : -1-(23) , (1)-2-(3)   ,  (12)-3  :     -1-(32) : 1(1)+3(2)+2(3)
  //      :  1-2-3 (2+2.4+3.5) , 1-3-2( 1.2+2.5+3.4 )
  
  // 23 : 2-3 (1.4+2.5), 3-2 (1.5+2.4)  :  3(1)+2(2) 
  
  // 23 => convert to 123 (1+2+3) freq
  private static void optimalbst(int[] keys, int[] frequency, int n) {
        int nk = keys.length;
        int nf = frequency.length;
        
        int[][] dp= new int[n][n];
        
        for( int gap=0 ; gap<n ; gap++ ){
            for( int i=0 , j=gap ; j<n ; i++ , j++ ){
                if( gap == 0 ) dp[i][j] = frequency[i];
                else if( gap == 1 ) dp[i][j] = Math.min( frequency[i] + 2*frequency[j] ,
                                                         2*frequency[i] + frequency[j] );
                else{
                    int min= Integer.MAX_VALUE;
                    int val =0 ;
					// convert 23 to 123 add height factor comparision ie add freq of (1+2+3)
                    for( int x=i ; x<=j ; x++ ){
                        val += frequency[x];
                    }
                    
                    for( int k=i ; k<= j ; k++){
                        int left = (k==0) ? 0 : dp[i][k-1];
                        int right = (k == j) ? 0 : dp[k+1][j];
                        
                        min = Math.min( left+right+val , min);
                    }
                    dp[i][j] = min;
                }
            }
            
        }
        System.out.println( dp[0][n-1] );
	}

    public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
	int[] keys = new int[n];
	int[] frequency = new int[n];
    for(int i= 0 ;i < n ; i++) {
		keys[i] = scn.nextInt();
	}
	for(int i = 0 ; i < n; i++){
      frequency[i] = scn.nextInt();
    }
	optimalbst(keys,frequency,n);
	}

}