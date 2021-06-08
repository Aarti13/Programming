import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node next;
  }

  public static class LinkedList {
    Node head;
    Node tail;
    int size;

    void addLast(int val) {
       
       Node nnode = new Node();
       nnode.data = val;
       nnode.next = null;
       
       if( size == 0){
           head = tail = nnode ;
       }
       
       else{
           tail.next = nnode;
           tail = nnode ;
       }
       size++;
    }

``public int size(){
      return size;
    }

    public void display(){
      
      Node temp = head;
      while( temp != null){
          
          System.out.print( temp.data + " ");
          temp = temp.next;
      }
      System.out.println();
    }
   
    public void removeFirst(){
     
     if( size == 0 )
         System.out.println( "List is empty" );
         
     else if( size == 1 ){
         head = tail = null;
         size = 0;
     }
     else{
        head  = head.next;
        size-- ;
     }
    }

    public int getFirst(){
     if( size == 0 ){
        System.out.println("List is empty");
        return -1;
     }
     return head.data;
    }

    public int getLast(){
     if( size == 0 ){
        System.out.println("List is empty");
        return -1;
     }
     return tail.data;
    }

    public int getAt(int idx){
     if( size == 0 ){
        System.out.println("List is empty");
        return -1;
     }
     else if(idx <0 || size <= idx ){
        System.out.println("Invalid arguments");
        return -1; 
     }
     else{
         Node temp = head;
         while( idx !=0 ){
             temp = temp.next;
             idx--;
         }
         return temp.data;
     }
     
    }

   public void addFirst(int val) {
     Node nnode = new Node();
     nnode.data = val;
     nnode.next = head;
     head = nnode;
     
     if( size == 0){
         tail = nnode;
     }
     
     size++;
    }
      public void addAt(int idx, int val){
        
      if( idx < 0 || size < idx ) System.out.println("Invalid arguments");    
      else if( idx == 0 ) addFirst(val);
      else if( idx == size ) addLast(val);
      else{
          Node nnode = new Node();
          nnode.data = val;
          Node temp = head;
          
          for( int i =0 ;i< idx-1 ; i++){
              temp = temp.next ;
          }
          nnode.next = temp.next;
          temp.next = nnode ;
          size++;
      }
    }
      public void removeLast(){
        
      if( size == 0) System.out.println("List is empty");
      else if( size == 1) {
          head = tail = null;
          size=0 ;
      }
      else{
        Node temp = head;
        while( temp.next.next != null ){
          temp = temp.next;  
        }
        tail = temp;
        tail.next=null;
        size--;
      }
    }
     public void removeAt(int idx) {
        
        if (idx == 0) removeFirst();
        else if( idx<0 || idx >= size) System.out.println("Invalid arguments");
        else if (idx == size-1 ) removeLast();
        else {
        Node temp = head;
        for (int i = 0; i < idx-1; i++) {
          temp = temp.next;
        }

        temp.next = temp.next.next;
        size--;
      }
    }
   private Node getNode(int idx){
        Node temp = head ;
        for(int i =0;i<idx ; i++)
            temp = temp.next;
        return temp;
    }
    public void reverseDI() {
      
      int li = 0;
      int ri = size-1;
      while( li < ri ){
          
          Node left = getNode(li);
          Node right = getNode(ri);
          
          int temp = left.data;
          left.data= right.data;
          right.data= temp;
          
          li++;
          ri--;
      }
    }

       public void reversePI(){
        
      Node curr = head;
      Node prev = null;
      while( curr != null){
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      
      Node temp = head ;
      head = tail;
      tail = temp;
    }

   public int kthFromLast(int k){
     
    Node f = head;
    Node s = head;
    
    for(int i =0;i<k ; i++) f = f.next;
    
    while( f != tail ){
        f= f.next;
        s = s.next;
    }
    return s.data;
    }
   
    public int mid(){
    
    int s = 0;
    int e = size -1;
    Node st = null;
    while( s <= e ){
        
        st = getNodeAt(s);
        Node ed = getNodeAt(e);
        s++;
        e--;
    }
    return st.data;
    }
  }
   
   public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
      
      Node one = l1.head;
      Node two = l2.head;
      LinkedList NL = new LinkedList();
      
      while( one != null && two != null ){
          
          if( one.data <= two.data ){
             NL.addLast(one.data);
              one = one.next;
          }
          else{
             NL.addLast(two.data);
              two = two.next;
          }
      }
      
      while( one != null  ){
             NL.addLast(one.data);
              one = one.next;
          }
      while( two != null  ){
            NL.addLast(two.data);
            two = two.next;
          }  
    return NL;
    }
   
   public static Node midNode( Node head, Node tail ) {
      Node f = head;
      Node s = head;

      while (f != tail && f.next != tail ) {
        f = f.next.next;
        s = s.next;
      }

      return s;
    }
    
    public static LinkedList mergeSort(Node head, Node tail){
    
    if( head == tail ){
        LinkedList base = new LinkedList();
        base.addLast(head.data);
        return base;
    }
    Node mid = midNode(head , tail );
    
    LinkedList N1 = mergeSort(head , mid );
    LinkedList N2 = mergeSort(mid.next , tail);
    LinkedList ans =mergeTwoSortedLists(N1 , N2 );
    return ans;
    }
    
  // space constant as we are removind then adding in res
  // time o(n)
  public void removeDuplicates(){
      
      LinkedList res = new LinkedList();
      
      while( this.size != 0){
          
          int val = this.getFirst();
          this.removeFirst();
          
          if( res.size() == 0 || res.tail.data != val )  res.addLast(val);
      }
    // why?? becoz l1 mein kuch nhi bacha 
    // this= res nhi karenge  as this is read only we
    // can change only its property
      this.head= res.head;
      this.tail= res.tail;
      this.size = res.size;
    }

  // without changing its relative order
  // odd pehle even baad mein
  // o(n) const space 
    public void oddEven(){
    
    LinkedList odd = new LinkedList();
    LinkedList even = new LinkedList();
    
    while( this.size != 0){
        int val = this.getFirst();
        this.removeFirst();
        
        if(val% 2 == 0)  even.addLast(val);
        else odd.addLast(val);
    }
    
    if(odd.size !=0  && even.size !=0 ){
        odd.tail.next = even.head ;
        this.head = odd.head;
        this.tail = even.tail;
        this.size = even.size + odd.size ;
    }
    
    else if( odd.size !=0 ){
        this.head = odd.head;
        this.tail = odd.tail;
        this.size = odd.size ; 
    }
    else{
        this.head = even.head;
        this.tail = even.tail;
        this.size = even.size ; 
    }
    }
  
  public void kReverse(int k) {
    
        LinkedList prev = null;
     
        while(this.size > 0){
            
            LinkedList curr = new LinkedList();
        
            if( this.size >= k){
              for( int i=0 ; i<k ; i++ ){
                int val = this.getFirst();
                this.removeFirst();
                curr.addFirst(val);  
              }
            }
              else{
                int os = this.size();
                for( int i=0 ; i<os ; i++ ){
                int val = this.getFirst();
                this.removeFirst();
                curr.addLast(val);  
              }
              }
               
               if( prev == null) prev = curr;
               else{
                   prev.tail.next = curr.head;
                   prev.tail = curr.tail;
                   prev.size += curr.size;
               }
            }
            
            this.head= prev.head;
            this.tail=prev.tail;
            this.size=prev.size;
    }  
    
  private void displayReverseHelper(Node node){
      
      Node temp = node;
       if( temp == null ) return;
      
      displayReverseHelper(temp.next);
      System.out.print(temp.data+" ");
    }
  // reverse using ptr 
    private void reversePRHelper(Node node){
      
      if( node == null ) return;
      reversePRHelper(node.next);
  //wapas aate hue reverse order mein use karne ko milenge
     // if(node == tail ) // nothing to do
    // den self ke next ka next khudpe pt karwaliya
      if(node != tail )  node.next.next = node;
    }

    public void reversePR(){
      reversePRHelper(head);
      head.next = null;
      Node temp = head;
      head= tail;
      tail= temp;
    }
  
  //O(n) space const
  // right ko parameter rakha stcak mein banegaa
  // left ko node banya heap mein banegaa
      private boolean IsPalindromeHelper(Node right) {
            if (right == null) {
                return true;
            }
            boolean rres = IsPalindromeHelper(right.next);
            if (rres == false) {
                return false;
            } else if (pleft.data != right.data) {
                return false;
            } else {
                pleft = pleft.next;
                return true;
            }
        }

        Node pleft;
        public boolean IsPalindrome() {
            pleft = head;
            return IsPalindromeHelper(head);
        }
   
  // k folds
  private void foldHelper(Node right , int fold){
        
        if(right == null ) return ;
        foldHelper(right.next , fold+1 );
        
        if( fold > size()/2 ) {
             Node temp = fleft.next;
            right.next = temp;
            fleft.next = right;
            fleft = temp;
        }
        else if( fold == size()/2 ){
            
            tail=right;
            tail.next = null;
        }
    
    }
    
    Node fleft = null; //fold left
    public void fold() {
      fleft = head;
      foldHelper(head , 0);
    
    }
  }
//////////
          public static int findIntersection(LinkedList one, LinkedList two) {
            Node on = one.head;
            Node tn = two.head;

            if (one.size > two.size) {
                for (int i = 0; i < one.size - two.size; i++) {
                    on = on.next;
                }
            } else {
                for (int i = 0; i < two.size - one.size; i++) {
                    tn = tn.next;
                }
            }

            while (on != tn) {
                on = on.next;
                tn = tn.next;
            }

            return on.data;
        }
        public static int addTwoLists(Node on, Node tn, int pio, int pit, LinkedList res) {
            if (on == null && tn == null) {
                return 0;
            }

            int carry = 0;
            int data = 0;
            if (pio > pit) {
                carry = addTwoLists(on.next, tn, pio - 1, pit, res);
                data = carry + on.data;
            } else if (pio < pit) {
                carry = addTwoLists(on, tn.next, pio, pit - 1, res);
                data = carry + tn.data;
            } else {
                carry = addTwoLists(on.next, tn.next, pio - 1, pit - 1, res);
                data = carry + on.data + tn.data;
            }

            carry = data / 10;
            data = data % 10;

            res.addFirst(data);
            return carry;
        }

        public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
            LinkedList res = new LinkedList();

            int carry = addTwoLists(one.head, two.head, one.size, two.size, res);
            if (carry > 0) {
                res.addFirst(carry);
            }

            return res;
        }
    }



  }

  public static void testList(LinkedList list) {
    for (Node temp = list.head; temp != null; temp = temp.next) {
      System.out.println(temp.data);
    }
    System.out.println(list.size);

    if (list.size > 0) {
      System.out.println(list.tail.data);
    } 
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    LinkedList list = new LinkedList();

    String str = br.readLine();
    while(str.equals("quit") == false){
      if(str.startsWith("addLast")){
        int val = Integer.parseInt(str.split(" ")[1]);
        list.addLast(val);
      } 
      str = br.readLine();
    }

    testList(list);
  }
}
