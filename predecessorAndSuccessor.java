import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
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
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

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
////////////////////////////////////////////////////////////////
s
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

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    predecessor = null;
    successor = null;
    predecessorAndSuccessor(root, data);
    if(predecessor == null){
      System.out.println("Predecessor = Not found");
    } else {
      System.out.println("Predecessor = " + predecessor.data);
    }

    if(successor == null){
      System.out.println("Successor = Not found");
    } else {
      System.out.println("Successor = " + successor.data);
    }
  }

}