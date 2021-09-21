import java.io.*;
import java.util.*;

public class Main {
    // same as permutation technique
    // level : spot because woh kam hai 
    // choices mein str char konsa wala aaega str char mein se spot 1 pe
  public static void kLenWordGenerate(int cs, int ts, String ustr,String asf,HashSet<Character> containCh ){
      
      if(cs==ts){
          System.out.println(asf);
          return;
      }
      //loop pure str ke elements ke liye block those char which comes again
      for( int i=0 ; i<ustr.length() ; i++){
          char ch = ustr.charAt(i);
          if( !containCh.contains(ch) ){
            containCh.add(ch);  
            kLenWordGenerate(cs+1,ts,ustr,asf+ch,containCh);
            containCh.remove(ch);
          }
      }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int k = Integer.parseInt(br.readLine());

    HashSet<Character> unique = new HashSet<>();
    String ustr = "";
    for (char ch : str.toCharArray()) {
      if (unique.contains(ch) == false) {
        unique.add(ch);
        ustr += ch;
      }
    }
    
    HashSet<Character> containCh = new HashSet<>();    
   kLenWordGenerate(0,k,ustr,"",containCh);
  }

}