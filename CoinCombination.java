import java.io.*;
import java.util.*;

public class Main {

    public static void coinChange(int i, int[] coins, int tamt, String asf){
        // combination
		//without repitions of coins
        //level: coins choice: coin select hoga ye nhi hoga
        
        if(i== coins.length ) {
            if( tamt == 0) {
                System.out.println(asf+".");
            }
            return;
        }
        if(tamt-coins[i] >= 0 ) coinChange(i+1,coins,tamt-coins[i],asf+coins[i]+"-" );
        coinChange(i+1,coins,tamt,asf );
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int amt = Integer.parseInt(br.readLine());
        
        coinChange(0, coins, amt, "");
    }
}