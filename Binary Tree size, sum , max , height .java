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

      // remove --- print --- array
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
  
    // print all the elements which occur at kth level
  public static void printKLevelsDown(Node node, int k){
      
        if( node == null || k<0 ) return ;
        if( k == 0 ) System.out.println( node.data +" ");
        printKLevelsDown(node.left,k-1 );
        printKLevelsDown(node.right,k-1 );
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