//Generic Trees : information are stored in non linear ways (Hierarchical structure) eg file storage 
// parent child rltn  children can be more than 2 ie Node , node's data and it;s children 

import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
	Node(){}
	Node(int data){this.data = data;}
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) { 	// -1 pop it
        st.pop();
      } else {
        Node t = new Node();  // made new data 
        t.data = arr[i];	// add data to it	

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }
// size 
  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static int max(Node node) {
    int m = Integer.MIN_VALUE;

    for (Node child : node.children) {
      int cm = max(child);
    }
    m = Math.max(Math.max(m, cm), node.data);

    return m;
  }

  public static int height(Node node) {
    int h = -1;

    for (Node child : node.children) {
      int ch = height(child);
      h = Math.max(h, ch);
    }
    h += 1;

    return h;
  }

  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);

    for(Node child: node.children){
      System.out.println("Edge Pre " + node.data + "--" + child.data);
      traversals(child);
      System.out.println("Edge Post " + node.data + "--" + child.data);
    }

    System.out.println("Node Post " + node.data);
  }
    
    //remove ---- print ---- add
// 10 20 30 40 50 60  ie print each level in straight line
  public static void levelOrder(Node node){
     
    Queue<Node>q = new ArrayDeque<>();
    q.add(node);
    
    while( q.size() > 0){
        node = q.remove();
        System.out.print(node.data + " ");
        for(Node child: node.children){
            q.add(child);
        }
    }
    System.out.print(".");
  }
 
// each level at different line 
//10
// 20 30 
// 40 50..

//1st Approach ie Parent queue and Child Queue
   public static void levelOrderLinewise(Node node){
      
        Queue<Node> pq = new ArrayDeque<>(); // parent queue
        Queue<Node> cq = new ArrayDeque<>(); // child queue
        pq.add(node);
        
        while( pq.size() > 0  ){
            
            node = pq.remove(); // remove
            System.out.print(node.data +" ");              // print
            for( Node ch : node.children){      //add in child queue
                cq.add(ch);
            } 
            
            if( pq.size() == 0) {
                pq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }
        }
  }

// 2nd method Parent queue add delimiter
  public static void levelOrderLinewise_Second(Node node){
      
        Queue<Node> pq = new ArrayDeque<>(); // parent queue
        pq.add(node);
        pq.add( new Node(-1));
        
        while( pq.size() > 0  ){
            
            node = pq.remove(); // remove
            
            if(node.data != -1){
                System.out.print(node.data +" ");              // print
                for( Node ch : node.children){      //add in child queue
                    pq.add(ch);
                }  
            }
            else
            { if( pq.size() > 0) {
                pq.add( new Node(-1));
                System.out.println();
                }
            }
        }
  }

// using count variable with parent Queue
    public static void levelOrderLinewise(Node node){
      
        Queue<Node> pq = new ArrayDeque<>(); // parent queue
        pq.add(node);
    
        while( pq.size() > 0  ){
            
            int count = pq.size();
            for( int i=0 ; i<count ;i++){
                node = pq.remove(); // remove
                System.out.print(node.data +" "); 
                for( Node ch : node.children){      //add in child queue
                    pq.add(ch);
                }  
            }  
            System.out.println();           
        }
  }
// using Pair class 

 // pair Class
  private static class Pair {
    int n;
    Node node;
    
    Pair(){}
    Pair(Node node ,int n ){
        this.node = node;
        this.n = n;
    }
  }
    public static void levelOrderLinewise(Node node){
      
        Queue<Pair> pq = new ArrayDeque<>(); // parent queue
        pq.add(new Pair(node , 1 ) );	     // add level with node data with each level 	
        int level = 1;
    
        while( pq.size() > 0  ){
            
            Pair p = pq.remove(); // remove
            if( level < p.n ){
                level = p.n;
                System.out.println(); 
            }
           System.out.print(p.node.data +" "); 
            for( Node ch : p.node.children){      //add in child queue
                    pq.add(new Pair(ch , p.n+1));
            }
        }
  }

// print all the zig zag nodes while tarversing

  public static void levelOrderLinewiseZZ(Node node){
      
        Stack<Node> ps = new Stack<>();// parent stack
        Stack<Node> cs = new Stack<>(); // child stack
        ps.push(node);
        int level = 1; // as for level 1 and 3 print from left to right and for
                       // level 2 and 4 print from right to left level wise 
        
        while( ps.size() > 0  ){
            
            node = ps.pop(); // remove
            System.out.print(node.data +" ");              // print
            
            //for even level
            if( level%2 == 1 ){
            for( Node ch : node.children)      //add in child queue
                cs.push(ch);
            }
            
            //for odd level
            else{
            for( int i = node.children.size()-1 ;i >=0 ; i-- )      //add in child queue
               { Node ch = node.children.get(i);
                cs.push(ch);
               }
            }
            
            if( ps.size() == 0) {
                ps = cs;
                level++;
                cs = new Stack<>();
                System.out.println();
            }
        }
  }
// mirror image of a graph
    public static void mirror(Node node){
     
        for( Node ch : node.children){
         mirror(ch);
        }
        Collections.reverse(node.children);
  }
  
   public static void removeLeaves(Node node) {
      
    for( int i =node.children.size()-1 ; i>=0 ; i--){
        Node child = node.children.get(i);
     if( child.children.size() == 0 ) node.children.remove(child);
    }
    
    for (Node child : node.children) {
      removeLeaves(child);
    }
  }

//O(n^2) time complexity
    public static void linearize(Node node){
    
        for(Node ch: node.children) linearize(ch);
        
        while( node.children.size() > 1 ){
           Node ln = node.children.remove(node.children.size() - 1); // remove last node
           Node sln = node.children.get(node.children.size() - 1); //second last node
           Node slnt = getTail(sln); // second last node tail 
           slnt.children.add(ln); //assign last node to be the child of second last tail node 
        }
  }
  
  public static Node getTail(Node node){
      while(node.children.size() == 1){
          node = node.children.get(0);
      }
      return node;
  }
  
 //O(n)
  
  public static Node linearize2(Node node){
        
        if(node.children.size()  == 0) return node;
        Node lnt = linearize2( node.children.get(node.children.size()-1 ) ); //last node tail
        
        while( node.children.size() > 1 ){
           Node ln = node.children.remove(node.children.size() - 1); // remove last node
           Node sln = node.children.get(node.children.size() - 1); //second last node
           Node slnt = linearize2(sln); // second last node tail 
           slnt.children.add(ln); //assign last node to be the child of second last tail node 
        }
        return lnt;
  }
  
//data present in graph or not
    public static boolean find(Node node, int data) {
    
        if( node.data == data ) return true;
        for( Node ch : node.children)
           {
               boolean res = find(ch , data);
               if(res) return true;
           }
        return false;
  }
   public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    
        if( node.data == data ){
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(node.data);
            return ans;
        } 
        for( Node ch : node.children)
           {
               ArrayList<Integer> ptc = nodeToRootPath(ch , data);  // path till child
               if( ptc.size() > 0 ) {
                    ptc.add(node.data);
                    return ptc;
               }
           }
        return new ArrayList<>();
  
 }

// longest common ancester
    public static int lca(Node node, int d1, int d2) {
    
         ArrayList<Integer> npd1 = nodeToRootPath( node , d1);// node to root path for d1
         ArrayList<Integer> npd2 = nodeToRootPath( node , d2);// node to root path for d2
        
        int i =npd1.size()-1 , j=npd2.size()-1;
        
        while( i>=0 && j>=0 && npd1.get(i) == npd2.get(j) )
        {
            j-- ; i--;
        }
        i++;
        return npd1.get(i);
  }

    public static int distanceBetweenNodes(Node node, int d1, int d2){
    
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);
    
    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }
    i++;
    j++;

    return i+j;
  }
  
// two trees are symmetric or not
   public static boolean areSimilar(Node n1, Node n2) {
    
        if( n1.children.size() != n2.children.size() ) return false;
        for( int i =0 ;i<n1.children.size() ; i++){
            Node p1 = n1.children.get(i);
            Node p2 = n2.children.get(i);
            
            if( areSimilar(p1,p2) == false ) return false;
        }
        return true;
  }
    public static boolean areMirror(Node n1, Node n2) {
     
     if( n1.children.size() != n2.children.size() ) return false;
     int i ,j;
     for( i =0 , j=n2.children.size()-1 ; i<n1.children.size() && j>=0 ; i++, j-- ){
           Node p1 = n1.children.get(i);
           Node p2 = n2.children.get(j);
            
            if( areMirror(p1,p2) == false ) return false;
     }
     return true;
  }

  //it has a common property ie each symmetric things is mirror image of itself
  public static boolean IsSymmetric(Node node) {
    return areMirror(node,node);
  }

  // Multisolver.........
// for max , min , size , height
    //travel and change
    //present in heap they are not changed
    static int size = 0;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int height = 0;
    
    public static void MultiSolver(Node node , int depth){
        size++;
        min = Math.min(min , node.data);
        max = Math.max(max , node.data);
        height = Math.max(height , depth);
        
        for( Node ch: node.children ){
            MultiSolver(ch , depth+1 );
        }
    }

// Node predecessor and successor;

  static Node predecessor;
  static Node successor;
  static int size = 0;
  public static void predecessorAndSuccessor(Node node, int data) {
    if( size == 0){
        if( node.data == data ) size = 1;
        else {
            predecessor = node;
        }
    }
    else if( size == 1){
        successor = node;
        size = 2;
    }
    
    for( Node ch: node.children ){
        predecessorAndSuccessor(ch , data);
    }
  }

  static int ceil = Integer.MAX_VALUE; // smallest among largest
  static int floor = Integer.MIN_VALUE; // largest among smallest
  
  public static void ceilAndFloor(Node node, int data) {
   
    if( node.data > data ) {
        if( ceil > node.data )
            ceil = node.data ;
    }
    if( node.data < data ) {
        if( floor < node.data )
            floor = node.data ;
    }
    
    for(Node ch : node.children) ceilAndFloor(ch , data);
  }

// kth largest ele
  public static int kthLargest(Node node, int k){
     
     // infinity ka floor = frst largest
     //frst largest ka floor = second largest
     //scnd largest ka floor = third largest and soo on.....
     
     floor = Integer.MIN_VALUE ;
     int factor =  Integer.MAX_VALUE ;
     
     for ( int i =0 ; i<k ;i++) {
        ceilAndFloor(node , factor);
        factor = floor;
        floor = Integer.MIN_VALUE ;
    }
    return factor;
  }

    //node with maximum subtree sum

    static int msn = 0;//maximum sum node 
    static int ms = Integer.MIN_VALUE;//maximum sum 
    
    public static int NodeSum(Node node ){
        int sum = 0;
        
        for (Node child : node.children) {
          int csum +=  NodeSum(child);
	  sum += csum;
        }
	sum += node.data ;
        if(sum > ms ){
            ms = sum;
            msn = node.data;
        }
        return sum;
    }


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    levelOrder(root);
  }

}