import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
       
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        for( int i=0;i<m ; i++) val[i] = sc.nextInt();  // stick price rates for each day
        
        int lbd = Integer.MAX_VALUE; // least buying day
        int profit = 0; //profit if sell today 
        int op = 0; // overall Profit
        
        for( int i =0;i<m ; i++){
            // for calculating least buying day for every day
            if( val[i] < lbd )  lbd = val[i] ; 
            // cal profit for sell day for prev least buy day 
            profit = val[i] - lbd ;
            
            // for cal overall max profit 
            if(profit > op) op = profit ; 
        }
        System.out.println(op);
    }

}