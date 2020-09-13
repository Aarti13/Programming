// contains key and corresponding value  , Not Order Sepecific Order ie random order 
//HashMap<String , Integer> hm = new HashMap<>();
// functions : hm.put( " INDIA" , 90);  // insert , update
		hm.get(" INDIA")  	// if key exist value , null 
		hm.containsKey(" INDIA")  // true , false
		Set<String> keys = hm.keySet() // for all the keys 

///////////////////////
khudka  HashMap 

import java.io.*;
import java.util.*;

public class Main {

  public static class HashMap<K, V> {
    private class HMNode {
      K key;
      V value;

      HMNode(K key, V value) {
        this.key = key;
        this.value = value;
      }
    }

    private int size; // n
    private LinkedList<HMNode>[] buckets; // N = buckets.length

    public HashMap() {
      initbuckets(4);
      size = 0;
    }

    private void initbuckets(int N) {
      buckets = new LinkedList[N];
      for (int bi = 0; bi < buckets.length; bi++) {
        buckets[bi] = new LinkedList<>();
      }
    }

    public void put(K key, V value) throws Exception {
      
        int bi = hashfun(key); // bucket index
        int di = getIndexWithinBucket(key , bi); // value index if key exist in that bucket
    
        //if key exist update it
        if( di!= -1){
            HMNode node = buckets[bi].get(di);
            node.value= value;
        }
        // add new node insert
        else{
            HMNode node = new HMNode(key , value );
            buckets[bi].add(node);
            size++;
        }
        
        // or rehashing
        double lambda = size * 1.0 / buckets.length ; 
        double k = 2.0;
        if( lambda > k ){
            rehash();
        }
    }
    
    private void rehash() throws Exception {
        LinkedList<HMNode>[] oba = buckets;
        
        initbuckets( 2*oba.length );
        size = 0;
        
        for( int i =0;i<oba.length ; i++){
            for( HMNode node : oba[i] )
                put( node.key , node.value );
        }
    }
    
    private int hashfun(K key){
        int hv = key.hashCode(); // inbuilt java function for hashing
        return Math.abs(hv) % buckets.length; // as it will return any type of value which should be in range
    }
    
    private int getIndexWithinBucket(K key , int bi ){
        int di=0;
        for( HMNode node : buckets[bi] ){
            if( node.key.equals(key) ) return di;
            di++;
        }
        return -1; // no key found
    }

    public V get(K key) throws Exception {
        int bi = hashfun(key); // bucket index
        int di = getIndexWithinBucket(key , bi); // value index if key exist in that bucket
    
        //if key exist  return value 
        if( di!= -1){
            HMNode node = buckets[bi].get(di);
            return node.value;
        } 
        else return null;
    }

    public boolean containsKey(K key) {
        int bi = hashfun(key); // bucket index
        int di = getIndexWithinBucket(key , bi); // value index if key exist in that bucket
    
        //if key exist  TRUE 
        if( di!= -1) return true;
        else return false;
    }

    public V remove(K key) throws Exception {
        int bi = hashfun(key); // bucket index
        int di = getIndexWithinBucket(key , bi); // value index if key exist in that bucket
    
        //if key exist  remove value 
        if( di!= -1){
            HMNode node = buckets[bi].remove(di);
            size--;
            return node.value;
        } 
        else return null;

    }

    public ArrayList<K> keyset() throws Exception {
        ArrayList<K> key = new ArrayList<>();
        
        for( int i =0 ; i<buckets.length ; i++ ){
            for( HMNode node : buckets[i] )
                key.add(node.key);
        }
        return key;
    }

    public int size() {
        return size;
    }

   public void display() {
      System.out.println("Display Begins");
      for (int bi = 0; bi < buckets.length; bi++) {
        System.out.print("Bucket" + bi + " ");
        for (HMNode node : buckets[bi]) {
          System.out.print( node.key + "@" + node.value + " ");
        }
        System.out.println(".");
      }
      System.out.println("Display Ends");
  }
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, Integer> map = new HashMap();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("put")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        Integer val = Integer.parseInt(parts[2]);
        map.put(key, val);
      } else if (str.startsWith("get")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.get(key));
      } else if (str.startsWith("containsKey")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.containsKey(key));
      } else if (str.startsWith("remove")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.remove(key));
      } else if (str.startsWith("size")) {
        System.out.println(map.size());
      } else if (str.startsWith("keyset")) {
        System.out.println(map.keyset());
      } else if (str.startsWith("display")) {
        map.display();
      }
      str = br.readLine();
    }
  }
}



///////////////////////
import java.io.*;
import java.util.*;

public class Main {
    
    // Highest Frequency Character
    public static void HFreqChar( String str ){
        
        HashMap<Character , Integer > hm = new HashMap<>();
        
        for(int i =0 ; i<str.length(); i++){
            char ch = str.charAt(i);
          
            if( hm.containsKey(ch)){
                int freq = hm.get(ch) ;
                int nf= freq+1 ;// new freq
                hm.put(ch, nf );
            }
                
            else hm.put(ch,1);
        }
        char mfc = str.charAt(0) ; // max character freq
        for( Character key : hm.keySet() ){
            if( hm.get(key) > hm.get(mfc) ){
                mfc = key;
            }
        }
        System.out.println( mfc );
        
    }
    	

    // Greastest Common Element
    public static void gce1( int[] arr1 , int[] arr2 ){
        
        //first initialize the frequency hashmap on array 1
        HashMap<Integer, Integer > hm = new HashMap<>();
        
        for(int i =0 ; i<arr1.length; i++){
        
            if( hm.containsKey( arr1[i] )){
                int freq = hm.get( arr1[i] ) ;
                int nf= freq+1 ;// new freq
                hm.put( arr1[i] , nf );
            }
                
            else hm.put( arr1[i] ,1);
        }
        
        for( int i =0;i< arr2.length ; i++){
            if( hm.containsKey( arr2[i] ))
                {
                    System.out.println(arr2[i]);
                    hm.remove(arr2[i]);
                }
        }
        
    }
    // Greastest Common Element with repetition 
    public static void gce2( int[] arr1 , int[] arr2 ){
        
        //first initialize the frequency hashmap on array 1
        HashMap<Integer, Integer > hm = new HashMap<>();
        
        for(int i =0 ; i<arr1.length; i++){
        
            if( hm.containsKey( arr1[i] )){
                int freq = hm.get( arr1[i] ) ;
                int nf= freq+1 ;// new freq
                hm.put( arr1[i] , nf );
            }
                
            else hm.put( arr1[i] ,1);
        }
        //traverse array 2 and check if it contains teh key den print it
        for( int i =0;i< arr2.length ; i++){
            if( hm.containsKey( arr2[i] ) &&  hm.get( arr2[i] ) >= 1)
                {
                    System.out.println(arr2[i]);
                    int val = hm.get( arr2[i] );
                    int nv = val-1;
                    hm.put(arr2[i] ,nv );
                }
        }
        
    }

    // largest consecutive sequence of elements
    public static void lcse(int[] arr ){
        HashMap<Integer , Boolean> hm = new HashMap<>();
        // add true to all the values
        for( int val : arr ) hm.put(val , true);
        // add false to all values whose val-1 is present ie they cannot be teh start of the lcs
        for( int val : arr ){
           if(hm.containsKey(val - 1 )) hm.put(val , false);
        } 
        
        // check for max length sequence for all those who have true values
        int msp = 0;//maximum starting point 
        int ml = 0; // maximum length
        for( int val: arr ){
            if(hm.get(val ) == true){
                int tl = 1; // total length
                int tsp = val;   // stsrting point
                
                while( hm.containsKey( tsp+tl )){
                    tl++;
                }
                
                if( tl > ml) {
                    ml=tl; 
                    msp = tsp;
                }
            }
        }
        
        for( int i=0;i<ml ; i++){
            System.out.println( msp+ i );
        }
    }
    

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] arr1 = new int[m];
        for( int i =0 ; i<m ; i++){
             int a = sc.nextInt();
             arr1[i] = a;
        }
        int n = sc.nextInt();
        int[] arr2 = new int[n];
        for( int i =0 ; i<n ; i++){
             int a = sc.nextInt();
             arr2[i] = a;
        }
        gce1( arr1 , arr2);
        
    }

}