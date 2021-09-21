import java.io.*;
import java.util.*;

public class Main {
    
    //Xor Of Sum Of All Pairs
    public static int solution(int[] arr){
       
       int sum = 0 ;
       for(  int val : arr ){
           sum = sum ^ (2 * val);
       }
       
       return sum;
    }
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        System.out.println(solution(arr));
    }
    
    
}