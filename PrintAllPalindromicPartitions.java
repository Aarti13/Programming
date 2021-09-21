import java.io.*;
import java.util.*;

public class Main {
    
    // abaaba
    // op: 
    // (a) (b) (a) (a) (b) (a) 
    // (a) (b) (a) (aba) 
    // (a) (b) (aa) (b) (a) 
    // (a) (baab) (a) 
    // (aba) (a) (b) (a) 
    // (aba) (aba) 
    // (abaaba) 
	public static void PrintAllPalindromicPartitions(String str, String asf) {
        
        if(str.length() == 0 ){
            System.out.println(asf);
            return;
        }
        
        // make call for every prefix if it is a palindrome 
        for( int i =0 ; i<str.length() ; i++){
            String prefix = str.substring(0,i+1); //prefix string for call
            String ros = str.substring(i+1); // rest of the string
            if( isPalindrome(prefix)){
                PrintAllPalindromicPartitions(ros , asf+"("+prefix+") ");
            }
        } 
	}
	
	public static boolean isPalindrome(String str){
	    int i =0 ;
	    int j =str.length()-1;
	    while(i<j){
	        if( str.charAt(i) != (str.charAt(j))) return false;
	        i++;
	        j--;
	    }
	    return true;
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		PrintAllPalindromicPartitions(str, "");
	}

}