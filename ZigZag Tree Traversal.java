class GFG
{
    
    //Function to store the zig zag order traversal of tree in a list.
    
    class Pair{
        Node n;
        int d;
        
        Pair(Node n , int d){
            this.n = n;
            this.d = d;
        }
    }
    
	ArrayList<Integer> zigZagTraversal(Node root)
	{
        ArrayList<Integer> al = new ArrayList<>();
        if(root == null ) return al;
        
        Stack<Pair> st = new Stack<>();
        Stack<Pair> helperSt = new Stack<>();
        st.push(new Pair(root,0));
        
        while(st.size()>0 ){
            
            int s = st.size();
            while(s>0){
                Pair p = st.pop();
                al.add(p.n.data);
                if( p.d % 2 == 0 ){ // even
                   if(p.n.left != null )helperSt.push(new Pair(p.n.left,p.d+1));
                   if(p.n.right != null )helperSt.push(new Pair(p.n.right,p.d+1));
                }
                else{  // odd level
                   if(p.n.right != null )helperSt.push(new Pair(p.n.right,p.d+1)); 
                   if(p.n.left != null )helperSt.push(new Pair(p.n.left,p.d+1));
                }
                s--;
            }
            st = helperSt;
            helperSt = new Stack<>();
        }
        return al;
	}
	
}