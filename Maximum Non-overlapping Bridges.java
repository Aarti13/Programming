import java.io.*;
import java.util.*;

public class Main {
    
    // comparable : we can sort object elements without chaning its natural ordering
    //    compareTo(class obj) use  this.__ - obj.__ (+)
    // comprator : we can sort the ele it give us flexibility only we have to add that class 
    // like Collection.sort(classobj , new classname which implements comparator fu)
    //  compare(clsobj o1 , clsobj o2)
    
    /////////////////////////////////////
    // Max non overlaping bridges
    // Brute force : frst find all the subsequence : 2^n
    //              den find all count of those pairs only which are non overlapping 
    //              ie. if curent.north < prev.north den current.s < prev.north
    //              n^2
    // TC : 2^n*n^2
    
    // sol:
    // max longest subsequence used.....
    // sort north bank in inc order
    // den find the maxLongestSubsequence on South bank basis  
    // why??? because then they are non overlapping...
    
    public static class bridgeClass implements Comparable<bridgeClass>{
        int n ; // north 
        int s ; // south
        
        bridgeClass( int n, int s){
            this.n = n;
            this.s = s;
        }
        
        public int compareTo(bridgeClass o){
            if( this.n - o.n == 0  ) return this.s-o.s;  // important case
            // if both north bank are same then we have to sort the pair acc to south bank
            return this.n-o.n;
        } 
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        bridgeClass[] ob = new bridgeClass[n];
        
        for( int i =0 ; i<n ; i++ ){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            ob[i] = new bridgeClass(n1,n2);
        }
        
        //Maximum Non-overlapping Bridges
        Arrays.sort(ob);

        int maxC = Integer.MIN_VALUE;
        int[] dp= new int[n];
        dp[0] = 1;
        for( int i=1 ; i<n ; i++){
            for(int j = i-1 ; j>=0 ; j-- ){
                if( ob[i].s >=  ob[j].s )dp[i] = Math.max(dp[i] , dp[j]);
            }
            dp[i] += 1;
            maxC = Math.max(dp[i] , maxC );
        }
        
        System.out.println(maxC);
    }

}