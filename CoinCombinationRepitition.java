import java.io.*;
import java.util.*;

public class Main {

    public static void coinChange(int idx, int[] coins,  int tamt, String asf) {
        //repetition is allowded ie we have infinite supply of coins
        // combination using permutation
        //level tamt , choice coins konsa select hoga if infinity supply hai toh
        // for removing permu always strt from ith index in coin selection
        
        if( tamt == 0 ){
            System.out.println(asf+".");
            return;
        }
        
        for( int i=idx ; i<coins.length ; i++){
            if( tamt-coins[i] >= 0 ){
                coinChange(i,coins,tamt-coins[i],asf+coins[i]+"-");
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
        coinChange(0, coins, amt, "");
    }
}