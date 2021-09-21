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

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }
    
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
    
    // max distance between two nodes of left height treee
    Dpair ld = diameter2(node.left); //left diameter
    // max distance between two nodes of right height tree
    Dpair rd = diameter2(node.right); //right diameter
    
    Dpair mp = new Dpair();
    mp.ht = Math.max( ld.ht , rd.ht ) + 1;
    
    int fee = ld.ht + rd.ht + 2;
    mp.dia = Math.max( fee , Math.max( ld.dia , rd.dia ) ) ;
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

    Dpair dp = diameter2(root);
    System.out.println(dp.dia);
  }

}