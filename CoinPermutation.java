import java.io.*;
import java.util.*;

public class Main {

    public static void coinChange(int[] coins, int tamt, String asf, boolean[] used){
        // permutation using combination  
        // no repitition of coins
        //level amount choices if coins... is selected
        
        if( tamt == 0){
            System.out.println(asf+".");
            return;
        }
        for( int i=0 ; i<coins.length ; i++ ){
            if( used[i] == false){
                if(tamt - coins[i] >= 0){
                    used[i]=true;
                    coinChange(coins,tamt-coins[i],asf+coins[i]+"-",used);
                    used[i]=false;
                }
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
        boolean[] used = new boolean[coins.length];
        coinChange(coins , amt, "", used);
    }
}