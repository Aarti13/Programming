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
    
    int ls = size( node.left);  // left size
    int rs = size( node.right); // right size
    int ts = ls +rs +1 ; // total size
    return ts;
  }

  public static int sum(Node node) {
    
    if( node == null ) return 0;
    
    int ls = sum( node.left);  // left sum
    int rs = sum( node.right); // right sum
    int ts = ls +rs + node.data ; // total sum
    return ts;
  }
    
  public static int max(Node node) {
     if( node == null ) return 0;
    
 
    int lm = max( node.left);  // left max
    int rm = max( node.right); // right max
    int max = Math.max(lm , rm) ;
    int tm = Math.max( max , node.data  ); // total 
    return tm;
  }

  public static int height(Node node) {
    
    // find edge height
     if( node == null ) return -1;
    
    int lh = height( node.left);  // left height
    int rh = height( node.right); // right height
    int th = Math.max(lh , rh ) +1;
    return th;
    
  }  

////// remove --- print --- array
  public static void levelOrder(Node node) {
    
    Queue<Node> q = new ArrayDeque<>();
    q.add(node);
    
    while( q.size() > 0 ){
        
        int count = q.size();
        for( int i =0; i<count ; i++){
            node = q.remove();
            System.out.print(node.data +" ");
        
            if( node.left != null ) q.add( node.left );
            if( node.right != null ) q.add( node.right );
        }
        System.out.println();
    }
   
  }

/////// find node to root path

      static ArrayList<Integer> path = new ArrayList<>();
  public static boolean find(Node node, int data){
    
    if( node == null ) return false;
    if( node.data == data ) 
    {
        path.add(node.data);
        return true;
    } 
    
    boolean lans = find(node.left , data);
    if( lans ){
        path.add(node.data);
        return true;
    } 
    boolean rans = find(node.right , data);
    if( rans ){
        path.add(node.data);
        return true;
    } 
    
    return false;
  }
  
/////// print all the elements which occur at kth level  down 
  public static void printKLevelsDown(Node node, int k){
      
        if( node == null || k<0 ) return ;
        if( k == 0 ) System.out.println( node.data +" ");
        printKLevelsDown(node.left,k-1 );
        printKLevelsDown(node.right,k-1 );
  }

/////// print all the nodes that are k distance away

 public static void printKNodesDown(Node node, int k , Node blocker) {
     if(node == null || k<0  || node == blocker ) return ;
     if( k == 0 ) System.out.println( node.data);
     printKNodesDown(node.left , k-1  , blocker);
     printKNodesDown(node.right , k-1 , blocker );
  }
  
  public  static ArrayList<Node> path = new ArrayList<>();
  public static boolean find(Node node, int data){
    
    if( node == null ) return false;
    if( node.data == data ) 
    {
        path.add(node);
        return true;
    } 
    
    boolean lans = find(node.left , data);
    if( lans ){
        path.add(node);
        return true;
    } 
    boolean rans = find(node.right , data);
    if( rans ){
        path.add(node);
        return true;
    } 
    return false;
  }
  
   public static void printKNodesFar(Node node, int data, int k) {
      
    find(node , data  );  
    for( int i =0 ; i< path.size() ; i++ ){
        printKNodesDown( path.get(i) , k-i , i==0 ? null : path.get(i-1) );
    }
      
  }
  
 ////////// path to leaf from root in given range 

  public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
        
        if( node == null ) return ;
        if( node.left == null && node.right == null ){
            sum += node.data;
            if( sum>= lo && sum <= hi ){
                System.out.println(path +node.data);
            }
        }
        pathToLeafFromRoot( node.left , path+node.data+" " , sum+node.data , lo, hi);
        pathToLeafFromRoot( node.right , path+node.data+" " , sum+node.data , lo, hi);
  }

/////////// transform to left clone tree  ie parent left child is copied in it become left child of left parent
//  a -> b , c b -> c , d original 
//  a-> a , c  a-> b b-> b d 

  public static Node createLeftCloneTree(Node node){
        
        if( node == null ) return null ;
      
        Node ln = createLeftCloneTree(node.left);
        Node rn = createLeftCloneTree(node.right);
        
        Node nn = new Node( node.data , ln , null );
        node.left = nn;
        node.right = rn;
        
        return node;
  } 

// transform to normal from left clone tree

  public static Node transBackFromLeftClonedTree(Node node){
        
        if( node == null ) return null;
        
        Node ln = transBackFromLeftClonedTree(node.left.left);
        Node rn = transBackFromLeftClonedTree(node.right);
        node.left = ln;
        node.right = rn;
        
        return node;
  }

// print single child nodes
  public static void printSingleChildNodes(Node node, Node parent){
    
    if(node == null) return;
    if( ( parent != null && parent.left == node &&  parent.right == null ) || 
        ( parent != null && parent.left == null &&  parent.right == node ) )
        System.out.println(node.data);
        
    printSingleChildNodes( node.left , node );
    printSingleChildNodes( node.right , node );
  }

//remove leaf node
  public static Node removeLeaves(Node node){
        
       if( node == null) return null;
       if( node.left == null && node.right == null ) return null;
       
       Node ln = removeLeaves(node.left);
       Node rn = removeLeaves(node.right);
      
        node.left= ln ;
        node.right= rn ;
 
        return node;
  }

// Diameter of a binary tree

 // First Method
    //O(N^2)
  public static int diameter1(Node node) {
    
    if( node == null ) return 0;
    
    // max distance between two nodes of left height treee
    int ld = diameter1(node.left); //left diameter
    // max distance between two nodes of right height tree
    int rd = diameter1(node.right); //right diameter
    
    // max distance between right deepest and left deepest tree
    int h = height(node.left) + height(node.right) + 2;
    int dia = Math.max( h , Math.max( ld , rd ) ) ;
    return dia;
  }
  
  public static class Dpair{
      int dia;
      int ht;
  }
  
  // second Method
    //O(N)
   public static Dpair diameter2(Node node) {
    
    if( node == null ) {
        Dpair dp = new Dpair();
        dp.dia = 0;
        dp.ht = -1;
        return dp;
    }
    
    // max distance between two nodes of left height tree
    Dpair ld = diameter2(node.left); //left diameter
    // max distance between two nodes of right height tree
    Dpair rd = diameter2(node.right); //right diameter
    
    Dpair mp = new Dpair();
    mp.ht = Math.max( ld.ht , rd.ht ) + 1;
    
    int fee = ld.ht + rd.ht + 2;
    mp.dia = Math.max( fee , Math.max( ld.dia , rd.dia ) ) ;
    return mp;
  }

// tilt of a binary tree ie
// sum of tilt for every node
// tilt fro particular node is = abs ( sum of left subtree  -   sum of right subtree )

  static int tilt = 0;
  public static int tilt(Node node){
        
        if(node == null) return 0;
        
        int lt = tilt(node.left) ;
        int rt = tilt(node.right)  ;
        
        int s = lt + rt + node.data  ;
        
        tilt += Math.abs( lt - rt );
        
        return s;
  }

// check if Binary Tree is a BST or not 
// ie it should be parent is grater than left child and less than right child for all nodes 
// and den for each node all the left nodes an dright nodes 
// node BST and Tree BST  if both true den the tree is BST 
// for each nodes we calculate - nodeBST , TREEBST , MAX , MIN (as ur parent need node's Min , Max for calc it's Own BST)

 public static class BSTPair{
        int max ;
        int min ;
        boolean isbst;
    }
    
    public static BSTPair isBST(Node node) {
        
        if( node == null ){
            BSTPair bp = new BSTPair();
            bp.max = Integer.MIN_VALUE;
            bp.min = Integer.MAX_VALUE;
            bp.isbst = true;
            return bp;
        }
        
        BSTPair lbst = isBST( node.left );
        BSTPair rbst = isBST( node.right );
        
        BSTPair mp = new BSTPair();
        
        mp.isbst = lbst.isbst && rbst.isbst && 
                    ( lbst.max <= node.data ) && (node.data <= rbst.min ) ;
        mp.max = Math.max( Math.max(lbst.max , rbst.max), node.data );
        mp.min = Math.min( Math.max(lbst.min , rbst.min), node.data );
        
        return mp;
    }
   
// is Balanced Tree
// ie its left height - right height <= 1 diff should be 0 , 1 only 

  static boolean isbt = true;
  public static int isBalancedTree( Node node ){
      
      if( node == null ) return 0;
      int l = isBalancedTree( node.left );
      int r = isBalancedTree( node.right );
       
      int gap = Math.abs( l -  r );
      if( gap > 1 ) isbt= false;
      int height = Math.max( l , r )+ 1;
      return height;
  }

// largest BST 

 public static class BSTPair{
        int max ;
        int min ;
        boolean isbst;
        int size ;
        Node node;
    }
    
    public static BSTPair isBST(Node node) {
        
        if( node == null ){
            BSTPair bp = new BSTPair();
            bp.max = Integer.MIN_VALUE;
            bp.min = Integer.MAX_VALUE;
            bp.isbst = true;
            bp.size = 0;
            bp.node = null;
            return bp;
        }
        
        BSTPair lbst = isBST( node.left );
        BSTPair rbst = isBST( node.right );
        
        BSTPair mp = new BSTPair();
        
        mp.isbst = lbst.isbst && rbst.isbst && 
                    ( lbst.max <= node.data ) && (node.data <= rbst.min ) ;
        mp.max = Math.max( Math.max(lbst.max , rbst.max), node.data );
        mp.min = Math.min( Math.max(lbst.min , rbst.min), node.data );
        
        if( mp.isbst ){
          mp.size = lbst.size + rbst.size + 1;
          mp.node = node;
        }
        else if( lbst.size > rbst.size ){
          mp.size =  lbst.size ;
          mp.node = lbst.node;
        }
        else{ 
          mp.size =  rbst.size ;
          mp.node = rbst.node;
        }
        
        return mp;
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

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int ht = height(root);
    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(ht);
  }

}