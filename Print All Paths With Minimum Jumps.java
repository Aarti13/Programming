import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    
    //Print All Paths With Minimum Jumps
    // 0   1   2    3   4   5  6   7   8  9
    // 3 , 3 , 0  , 2 , 1 , 2 , 4, 2 , 0 , 0 

    // storage : 6 : 6-last index min moves 
    
    public static class pair{
        int i;
        int val ;
        int j;
        String psf ;
        
        pair(int i , int val , int j , String psf ){
            this.i = i;
            this.val = val;
            this.j = j ;
            this.psf = psf;
        }
    }
    public static void Solution(int arr[]){
        
        int n = arr.length;
        Integer[] dp = new Integer[n]; 
        
        dp[n-1] = 0 ;
        for(int i=n-2 ; i>=0 ; i--){
            int min = Integer.MAX_VALUE;
            if( arr[i] == 0 ) continue;
            
            for( int j = i+1 ; j<= i+arr[i] && j<n  ; j++ ){
                if( dp[j] != null && dp[j] < min  ){
                    min = dp[j];
                }
            }
            if( min != Integer.MAX_VALUE ) dp[i] = min+1;
        
        }
            System.out.println(dp[0]);
            Queue<pair> q = new ArrayDeque<>();
            q.add( new pair(0 , arr[0] , dp[0] , 0+""));
            
            while(q.size() > 0){
                pair p = q.remove();
                if( p.j == 0){
                    System.out.println(p.psf+" .");
                }
                
                for( int k= p.i+1 ; k<=p.i+p.val && k<n ; k++ ){
                    if( dp[k]!= null && dp[k] == p.j-1 ){
                      q.add( new pair(k , arr[k] , dp[k] , p.psf+" -> "+k));
                    }
                }
            }
            
    }
    public static void main(String []args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++)
            arr[i] = scn.nextInt();

        Solution(arr);
        scn.close();
    }
}