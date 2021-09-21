import java.io.*;
import java.util.*;

public class Main {
    
 //using combination using permu
 //level : spots
 //choices : string char
 public static void KWordLenComb2Per( int lcusi,String ustr, int cs , int ts,HashMap<Character, Integer> unique ,String asf  ){
 //lcusi // last character Used it
   if(cs == ts){
       System.out.println(asf);
       return;
   }
    for(int i=lcusi ; i<ustr.length() ; i++){
        char ch = ustr.charAt(i);
        if( unique.get(ch) > 0 ){
            unique.put(ch, unique.get(ch)-1 );
            KWordLenComb2Per(i,ustr,cs+1,ts,unique,asf+ch);
            unique.put(ch, unique.get(ch)+1 );
        }
    }
 }
 
  public static void main(String[] args) throws Exception  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int k = Integer.parseInt(br.readLine());

    HashMap<Character, Integer> unique = new HashMap<>();
    String ustr = "";
    for (char ch : str.toCharArray()) {
      if (unique.containsKey(ch) == false) {
        unique.put(ch, 1);
        ustr += ch;
      } else {
        unique.put(ch, unique.get(ch) + 1);
      }
    }
    KWordLenComb2Per(0,ustr,0,k,unique,"");
    
  }

}