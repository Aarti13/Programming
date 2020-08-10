import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
       
       // constraint non overlapping transactions ie BBSS
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        for( int i=0;i<m ; i++) val[i] = sc.nextInt();  // stick price rates for each day
        
        int bd = 0; //  buying day
        int sd = 0; // buying day
        // cal all the upstrokes
        int profit = 0; //profit for every appropriate buy and sell day 
        
        for( int i = 1;i<m ; i++){
            // if next day price in den selling day would also inc
            if( val[i] >= val[i-1] )  sd++ ; 
            // if the price dec then cal the 
            else {
                profit += val[sd] - val[bd] ;
                bd = sd = i;
            }
        }
        profit += val[sd] - val[bd] ; // for last buy sell upstrokes as no downfall would be noticed at the end
        System.out.println(profit);
    }

}