import java.io.*;
import java.util.*;

public class Main {

  public static void kLengthWordGenerate(int idx ,String ustr,int cs , int ts , Character[] spots){
    //No Repetition
    //same as permuatation using combination
    //level string char , choices spots
    if( idx == ustr.length() ){
        if( cs == ts){
            for(int i=0 ; i<spots.length ; i++){
                System.out.print(spots[i]);
            }
         System.out.println();
        }
        return;
    }  
    
    char ch = ustr.charAt(idx);
    for( int i=0;i<spots.length ; i++){
        if( spots[i] == null ){
            spots[i]=ch;
            kLengthWordGenerate(idx+1,ustr,cs+1,ts,spots);
            spots[i]=null; 
        }
    }  
    kLengthWordGenerate(idx+1,ustr,cs,ts,spots);  
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
    Character[] spots = new Character[k];
    kLengthWordGenerate(0,ustr,0,k,spots);
    
  }

}