import java.io.*;
import java.util.*;

public class Main {


  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    String str = scn.nextLine();
    int k = scn.nextInt();

    HashSet<Character> unique = new HashSet<>();
    String ustr = "";
    for (char ch : str.toCharArray()) {
      if (unique.contains(ch) == false) {
        unique.add(ch);
        ustr += ch;
      }
    }

    combination(0, ustr, 0, k, "");
  }

// aabbbccdde -> abcde (---) 5C3 : 
// Total calls : 2^5 = 5C0 + 5C1 + 5C2 + 5C3 + 5C4 + 5C5
//                      1 +  5 + 10 + 10 + 5 + 1
// from all these calls select only those calls when string is empty and placed elements is equal to k spots
  public static void combination(int i, String ustr, int ssf, int ts, String asf ) {
        
        //level : string char 
        //choice : box mein aaega yea  nhi aaega
        
        if( i == ustr.length()){
            if( ssf == ts ){
                System.out.println(asf);
            }
            return;
        }
        
        char ch = ustr.charAt(i);
        combination(i+1,ustr,ssf+1,ts,asf+ch);
        combination(i+1,ustr,ssf,ts,asf);
  }

}