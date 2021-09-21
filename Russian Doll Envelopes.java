import java.io.*;
import java.util.*;

public class Main {
    
    //Russian Doll Envelopes
    // same as bridges 
    // frst sort pairs on basis wt then find max LIS on ht
    
    public static class EnvelopeClass implements Comparable<EnvelopeClass>{
        int wt ; //width
        int ht ; //height
        
        EnvelopeClass(int wt , int ht ){
            this.wt = wt;
            this.ht = ht;
        }
        
        public int compareTo( EnvelopeClass o ){
            return this.wt - o.wt;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        EnvelopeClass[] obj = new EnvelopeClass[n];
        for(int i=0 ;i<n ; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            
            obj[i] = new EnvelopeClass(n1,n2);
        }
        Arrays.sort(obj);
        
        int[] dp=new int[n];
        dp[0] = 1;
        int maxC = Integer.MIN_VALUE;
        for( int i=1 ; i<n ; i++){
            for( int j=i-1; j>=0 ; j--){
                if( obj[i].ht > obj[j].ht ){
                    dp[i] = Math.max(dp[i] , dp[j]);
                }
            }
            dp[i] += 1;
            maxC = Math.max(maxC , dp[i] );
        }
        
        System.out.println(maxC);
    }

}