// heap : heap order priority every parent has higher prior than its children (peek) O(1)
//	 CBT : Complete Binary Tree fill height - 1 level full first ie add filling from left to right (add , remove )//logn
//	 MinHeap , MaxHeap
//	 left child index = 2parent +1 
//	 right child index = 2parent +2
//	 parent = child - 1 / 2
//	 CBT -> AL implementation -> Index formulate -> Parent path capability -> add at last O(n) than
//		upheapify call ie swap if child is > parent (logn) height n -> logn

// sol 1 
// add : just add it O(1)
// remove : traverse and den remove O(n)
// peek : O(n)
// total complexity : add O(n) + remove , peek  n*n = O(n^2)

// sol 2 
// add : add it at right position O(n) 
// remove : just remove O(1)
// peek : O(1)
// total complexity :  remove , peek n*n  + add  O(n) = O(n^2)

// sol 3 
// add : just add it O(logn)
// remove : traverse and den remove O(logn)
// peek : O(1)
// total complexity : O(n) +  n*n = O(n^2)

****************************************
//O(n)
public PriorityQueue() {
      data = new ArrayList<>( int[] arr );
	for( int val : arr ) this.add(val);   //O(nlogn)
	upheapify();
    }

public PriorityQueue() {
      data = new ArrayList<>( int[] arr );
	for( int val : arr ) data.add(val);   //O(n)
	// n/2 logo pe 0 kam hua n/4 pe 1 ka kam hua
	for( int i = data.size() /2 -1 ; i>=0 ; i--)
	downheapify(i);
    }

time complexity proof:
T(n) = 2^(1-1) h + 2^(2-1) (h-1) + 2^(3-1) (h-2) + .. .. .. .. + 2^(h-1) 0
T(n) = 2^(0) h + 2^1 (h-1) + 2^(2) (h-2) + .. .. .. .. + 2^(h-1) 0

2*T(n) = 2^(1) h + 2^2 (h-1) + 2^(3) (h-2) + .. .. .. .. + 2^(h-1)
    eqn 2 -1 

T(n) = -2^(0) h + [ 2^1 + 2^2 + 2^(3) + .. .. .. .. + 2^(h-1) ] // add + 2^(0)-2^(0)
using gp 
 (1*2^h - 1) / 2-1  - 2^(0) - h
 2^h - h -2   as  h = logn
 
n- logn - 2 
//)(n)
****************************************
// O(nlogn)
// write Priority Queue Using Heap

import java.io.*;
import java.util.*;

public class Main {

  public static class PriorityQueue {
    ArrayList<Integer> data;

    public PriorityQueue() {
      data = new ArrayList<>();
    }

    public void add(int val) {
      
      data.add( val );
      upheapify( data.size()-1 );
    }
    
    public void upheapify( int ci ){ //log(n)
        
        if(ci == 0) return;
        int pi = ( ci - 1 )/2  ;// parent index
        if( data.get(ci) < data.get(pi) ) 
	{
        swap( ci , pi );
        upheapify( pi );
        }
    }
    
    public void swap( int i , int j ){
        int n = data.get(i);
        int m = data.get(j);
        data.set( i , m);
        data.set( j , n);
    }

    public int remove() {
     if( data.size() == 0 ) {
          System.out.println("Underflow");
          return -1;
      }
      swap(0 , data.size()-1);
      int val = data.remove(data.size()-1);
      downheapify( 0);
      
      return val;
    }
    
    public void downheapify( int pi ){
        
        int min =pi;
        int li = 2*pi + 1  ;// left child index
        if( li < data.size() && data.get(min) > data.get(li) ) min=li;
        
        int ri = 2*pi + 2  ;// right child index
        if( ri < data.size() && data.get(min) > data.get(ri) ) min=ri;
        
        if( min!= pi ){
        swap( min , pi);
        upheapify( min );
        }
    }

    public int peek() {
      if( data.size() == 0 ) {
          System.out.println("Underflow");
          return -1;
      }
      return data.get(0);
    }

    public int size() {
      return data.size()-1;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PriorityQueue qu = new PriorityQueue();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("add")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        qu.add(val);
      } else if (str.startsWith("remove")) {
        int val = qu.remove();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("peek")) {
        int val = qu.peek();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("size")) {
        System.out.println(qu.size());
      }
      str = br.readLine();
    }
  }
}
