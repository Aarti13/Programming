// BINARY SEARCH TREE
/* node.left.data < node.data < node.right.data
   faster searching  find(value) // log(n) as after each node half of the ele are eliminated
   values functions will be optimized   but functions which depend on structure will be same in Binary Tree

   construct BST :
   low , mid , high ie node.left.data < node.data < node.right.data
    
public static Node construct2(Integer[] arr , lo , hi ) {
    
    if( lo > hi ) return null ; 
    int mid = ( lo + hi )/2;
    Node l = construct2( arr , lo,mid-1);
    Node r = construct2( arr , mid+1 , hi);
    
    Node root = new Node( arr[mid] , l , r );
    return root;
  }

*/

// size , sum , min , max , find

import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }
    

  
  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static int size(Node node) {
    
    if( node == null ) return 0;
    int ls = size( node.left );
    int rs = size( node.right );
    
    int s = ls + rs + 1;
    return s;
  }

  public static int sum(Node node) {
    if( node == null ) return 0;
    int ls = sum( node.left );
    int rs = sum( node.right );
    
    int s = ls + rs + node.data ;
    return s;
  }

  public static int max(Node node) {
    
    if( node.right != null ) return max(node.right);
    else return node.data;
  }

  public static int min(Node node) {
      
    if( node.left != null ) return min(node.left);
    else return node.data;
  }

  public static boolean find(Node node, int data){
    
    if( node == null ) return false;
    if( node.data == data ) return true;
    else if( node.data < data ) return find( node.right , data );
    else return find( node.left , data );
  }  

   public static Node add(Node node, int data) {
    
    if( node == null ) {
        Node m = new Node( data , null , null );
        return m;
    }
    
    if( node.data > data ) node.left = add( node.left , data );
    else if( node.data < data )  node.right = add( node.right , data );
    else{ // do nothing 
    }
    return node;
  } 
 // remove nodes with 0 , 1 , 2 childs
public static Node remove(Node node, int data) {
        
        if( node == null ) return null;
        if( node.data > data ) node.left = remove( node.left , data );
        else if( node.data < data ) node.right = remove( node.right , data );
        else{
            
            if( node.left != null && node.right != null ){
                // 2 child find left side ka rightmost val nd swap with node
                int m = max(node.left);
                node.data = m;
                node.left = remove(node.left , m );
                return node;
            }
            else if( node.left != null ){
                // 1 child
                return node.left ;
            }
            else if( node.right != null ){
                // 1 child
                return node.right ;
            }
            else{ return null;
            }
        }
        return node;
  }

// replace with sum of largest value 
 static int sum = 0;
  public static void rwsol(Node node){
    
    if( node == null ) return ;
   
    rwsol(node.right);
    int s = node.data;
    node.data = sum;
    sum += s ;
    rwsol(node.left);
  }

// longest common ancester of BST 
  public static int lca(Node node, int d1, int d2) {
        
        if( node == null ) return 0;
        
        if( node.data < d1 && node.data < d2 ) 
            return lca( node.right , d1, d2 ); 
        else if( node.data > d1 && node.data > d2 ) 
            return lca( node.left , d1, d2 ); 
        else return node.data;
  }

// print all the nodes that comes in a range
  public static void pir(Node node, int d1, int d2) {
        
        if( node == null ) return ;
     if( node.data < d1 && node.data < d2 )  pir( node.right , d1, d2 );
     else if( node.data > d1 && node.data > d2 )  pir( node.left , d1, d2 );
     else{
        pir( node.left , d1, d2 );
        if( node.data >= d1 && node.data <= d2 ) System.out.println( node.data +" ");
        pir(node.right , d1, d2 );
     }
  }

// target sum pairs
// 1st method 
// TC: nlog(n) nof ele * height   SC: height 
  public static void tsp( Node root , Node node , int target){
      
      if( node == null ) return;
      
      tsp(root , node.left , target );
      int sum = target - node.data ; // find compliment
      
      if( node.data < sum){  // for non repeating pairs 
        if( find( root , sum) ) System.out.println( node.data +" "+ sum );
      }
      tsp(root , node.right , target );
  }

// 2nd method 
// using arraylist for filling sorted list 
// TC: n  SC: n

  public static void tsp(  Node node , ArrayList<int> list){
      // fill arraylist 
      if( node == null ) return;
      tsp( node.left , list );
      list.add(node.data);
      tsp( node.right , list );
  } 
   // den in main func
   int si = 0 ;
   int ei = list.length - 1;
    while( si< ei){
        int left = list.get(si);
        int right = list.get(ei);
        
        if( left+ right < data )  si++;
        else if( left+ right > data )  ei--;
        else {
            System.out.println( left + " " + right );
            si++ ; ei--;
        }
    }

// 3rd method 
// using inorder or reverse order tarversal act as same start adn end indx in arraylist  
// TC: n  SC: h

 public static class ItPair{
      
      Node node;
      int state; // 0 preorder  1 inorder  2 postorder
      
      ItPair( Node node , int state){
          this.node = node;
          this.state = state;
      }
  }
  
  public static void BestApproach( Node node  , int target){
      
      Stack<ItPair > ls = new Stack<>() ; // left stack 
      Stack<ItPair> rs = new Stack<>() ; // right stack
      
      ls.push( new ItPair( node , 0)) ;
      rs.push( new ItPair( node , 0)) ;
      
      Node left = getNextFromNextInorder(ls);
      Node right = getNextFromReverseInorder(rs);
      
      while( left.data < right.data ){
          
          if( left.data + right.data < target  ) 
              left = getNextFromNextInorder(ls);
              
          else if( left.data + right.data > target  ) 
              right = getNextFromReverseInorder(rs);
              
          else{
              System.out.println( left.data + " "+ right.data );
               left = getNextFromNextInorder(ls);
              right = getNextFromReverseInorder(rs);
          }
    
      }
  }
  
  public static Node getNextFromNextInorder( Stack<ItPair> st ){
      
      while( st.size() > 0){
          ItPair top = st.peek();
          
          if( top.state== 0 ){
              if( top.node.left != null )
                st.push( new ItPair(top.node.left , 0) );
            top.state++;
          }
          else if( top.state == 1){
              if( top.node.right != null )
                st.push( new ItPair(top.node.right , 0) );
            top.state++;
            return top.node;
          }
          else st.pop();
      }
      return null;
  }
  
    public static Node getNextFromReverseInorder( Stack<ItPair> st ){
      
      while( st.size() > 0){
          ItPair top = st.peek();
          
          if( top.state== 0 ){
              if( top.node.right != null )
                st.push( new ItPair(top.node.right , 0) );
            top.state++;
          }
          else if( top.state == 1){
              if( top.node.left != null )
                st.push( new ItPair(top.node.left , 0) );
            top.state++;
            return top.node;
          }
          else st.pop();
      }
      return null;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int min = min(root);
    boolean found = find(root, data);

    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(min);
    System.out.println(found);
  }

}
