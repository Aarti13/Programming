import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		solve(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
		System.out.println(ans);
	}

//Tug Of War
//You have to divide these n integers into 2 subsets such that the difference of sum of two subsets 
	static int mindiff = Integer.MAX_VALUE;
	static String ans = "";

// soset : sum of set 1 or 2
	public static void solve(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1,
			int soset2) {
       
        if( vidx == arr.length ){
            int min = Math.abs(soset1 - soset2);
            if(mindiff > min ){
                mindiff=min;
                ans = set1+" "+set2;
            }
            //System.out.println(ans);
            return ;
        }
        if(set1.size() <  (arr.length+1) /2  ){
            set1.add(arr[vidx]);
            solve(arr,vidx+1,set1,set2,soset1+arr[vidx],soset2);
            set1.remove(set1.size()-1);
        }
        if(set2.size() < (arr.length+1) /2  ){
            set2.add(arr[vidx]);
            solve(arr,vidx+1,set1,set2,soset1,soset2+arr[vidx]);
            set2.remove(set2.size()-1);
        }
	}

}