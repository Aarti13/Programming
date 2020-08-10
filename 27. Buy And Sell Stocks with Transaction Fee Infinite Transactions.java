import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
       
       // constraint non overlapping transactions ie BBSS
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        for( int i=0;i<m ; i++) val[i] = sc.nextInt();  // stick price rates for each day
        int fee = sc.nextInt();
        
        int bsp = -val[0]; //  buy state price
        int ssp = 0; // sell state price
        
        for( int i = 1;i<m ; i++){
            
            int nbsp = 0;
            int nssp = 0;
            // for buy state price
            if( ssp - val[i] > bsp ) nbsp = ssp - val[i] ;
            else nbsp = bsp ;
            
            // for sell state price
            if( bsp + val[i] - fee > ssp ) nssp = bsp + val[i] - fee ;
            else nssp = ssp ;
            
            bsp = nbsp ; 
            ssp = nssp;
        }
        System.out.println(ssp);
    }

}