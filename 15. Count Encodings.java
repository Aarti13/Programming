import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        
        int[] dp= new int[str.length() ];
        dp[0]= 1;
        
        for( int i =1 ;i< dp.length ; i++){
            
            // 0 0 case
            if( str.charAt(i-1 ) == '0' &&  str.charAt(i ) == '0' ) dp[i] = 0;
            // 0 nonzero case
            else if( str.charAt(i-1 ) == '0' &&  str.charAt(i ) != '0' ) dp[i] = dp[i-1];
            // nonzero  0 case
            else if( str.charAt(i-1 ) != '0' &&  str.charAt(i ) == '0' ) {
                
                if( str.charAt(i-1 ) =='1' || str.charAt(i-1 )== '2' ) 
                    dp[i] = i >= 2 ? dp[i-2] : 1;
                else dp[i] = 0 ;
            }
            // nonzero nonzero case
            else{
                if( Integer.parseInt(str.substring( i-1 , i+1 )) <= 26 )
                    dp[i] = dp[i-1] + (i >= 2 ? dp[i-2] : 1 ) ;
                    
                else dp[i] =  dp[i-1];
            }
        }
        System.out.println( dp[dp.length-1] );
    }
}