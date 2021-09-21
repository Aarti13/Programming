import java.io.*;
import java.util.*;

public class Main {

    public static void coinChange(int[] coins, int tamt, String asf) {
        // using permutation 1
        //level amount ,  choices if coins... is selected
        
        if( tamt == 0 ){
            System.out.println(asf+".");
            return;
        }
        for( int i=0 ; i<coins.length ; i++ ){
          if( tamt - coins[i] >= 0 ){
              coinChange(coins,tamt-coins[i] , asf+coins[i]+"-");
          }  
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int amt = Integer.parseInt(br.readLine());
        coinChange(coins, amt, "");
    }
}