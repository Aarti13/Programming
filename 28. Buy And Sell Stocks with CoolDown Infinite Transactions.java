import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
       
       // constraint non overlapping transactions ie BBSS
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        for( int i=0;i<m ; i++) val[i] = sc.nextInt();  // stick price rates for each day
        
        int bsp = -val[0]; //  buy state price
        int ssp = 0; // sell state price
        int csp = 0; // clddown state price
        
        for( int i = 1;i<m ; i++){
            
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;
            
            // for buy state price
            if( csp - val[i] > bsp ) nbsp = csp - val[i] ;
            else nbsp = bsp ;
            
            // for sell state price
            if( bsp + val[i]  > ssp ) nssp = bsp + val[i] ;
            else nssp = ssp ;
            
            // for coldown state price
            if( ssp  > bsp ) ncsp = ssp  ;
            else ncsp = csp ;
            
            bsp = nbsp ; 
            ssp = nssp;
            csp = ncsp;
        }
        System.out.println(ssp);
    }

}