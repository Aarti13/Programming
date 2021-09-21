import java.io.*;
import java.util.*;

public class Main {

    public static void solution(int[] arr) {
    //sort and perform immediate xor 
    // a,b,c,d
    // in the above pairs : ab,ac,ad,bc,bd,cd , ba,ca,da,cb,db,dc
    
    // why ?? a,b,c => a^b , b^c => 
    //  a <= b <= c because  a^b < a^c  and b^c < a^c
    // a = 11010110101  
    // b = 110101.....
    // c = 1101110....
    
        Arrays.sort(arr);
        ArrayList<String> al = new ArrayList<>();
        
        int min = Integer.MAX_VALUE;
        for( int i=0 ; i<arr.length-1 ; i++ ){
            int ans = arr[i] ^ arr[i+1];
            if( ans < min ){
                min = ans ;
                al = new ArrayList<>();
                al.add(arr[i] +", "+arr[i+1]);
            }
            else if(min == ans ){
                al.add(arr[i] +", "+arr[i+1]); 
            }
        }
        
        for(int i=0 ; i<al.size() ; i++){
            System.out.println(al.get(i));
        }
    }
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        solution(arr);
    }
    
    
}