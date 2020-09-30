import java.io.*;
import java.util.*;

public class Main {
    
    /*
words 7 aaaa asas able ability actt actor access 
puzzle 6  aboveyz abrodyz abslute absoryz actresz gaswxyz

ans 
aboveyz -> 1
abrodyz -> 1
abslute -> 3
absoryz -> 2
actresz -> 4
gaswxyz -> 0

        Word Should contain first letter of puzzle
        Puzzle must contain all letters of words
    */
	public static ArrayList<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
	    
	    // HashMap to store Character with bit masking that start with that particular word
	    HashMap< Character , ArrayList<Integer> > map = new HashMap<>();
	    for( int i =0 ;i<26 ;i++){
	        map.put( (char)( i+'a') , new ArrayList<>() );
	    }
	    
	    for( String w : words ){
	        int mask = 0;
	        // making BitMasking no for particular words where ch marked(a--z) adfc  101101
	        for( char ch : w.toCharArray()){
	            int bit = ch - 'a';
	            mask = mask | (1<<bit) ;
	        }
	        
	        // as this leads to storing each word more then once for particular char
	        // so we make hashSet for this 
	        
	        HashSet<Character> unique = new HashSet<>();
	        //stroring the same ch starting words into hashmap
	        for( char ch : w.toCharArray()){
	            
	            // checking for unique words
	            if( unique.contains(ch)) continue;
	            unique.add(ch);
	            
	            map.get(ch).add(mask);
	        }
	    }
	    
	    ArrayList<Integer> res = new ArrayList<>();
	    // For puzzle checking for each words
	    for( String puzzle : puzzles ){
	        int pmask = 0;
	        
	        for( char ch : puzzle.toCharArray()){
	            int bit = ch - 'a';
	            pmask = pmask | (1<<bit) ;
	        }
	        
	        char fch = puzzle.charAt(0); // first character
	        ArrayList<Integer> wordToCheck = map.get(fch) ;
	        // at last checking word char is present in puzzle
	        int c=0;
	        for( int wmask : wordToCheck){
	             if( (wmask & pmask )== wmask ) c++;
	         }
	         res.add(c);
	    }
	    return res;
	 }

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		String[] words = new String[n];
		for(int i = 0 ; i < words.length; i++) {
			words[i] = scn.next();
		}
		int m = scn.nextInt();
		String[] puzzles = new String[m];
		for(int i = 0 ; i < m ;i++) {
			puzzles[i] = scn.next();
		}
		ArrayList<Integer> ans = findNumOfValidWords(words,puzzles);
		for(int i=0;i<ans.size() ; i++ ) {
			System.out.println(puzzles[i] + " -> " + ans.get(i));
		}
	}
	
}