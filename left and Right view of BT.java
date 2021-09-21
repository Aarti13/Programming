class Solution{
    //Function to return list containing elements of right view of binary tree.
    ArrayList<Integer> rightView(Node node) {
        
        ArrayList<Integer> al = new ArrayList<>();
        if( node == null ) return al;
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        
        while(q.size()> 0 ){
            int s = q.size();
            int i=1;
            while( s>=i ){
                Node p = q.remove();
                if( i==1) al.add(p.data);
                if(p.right != null ) q.add(p.right);
                if(p.left != null  ) q.add(p.left);
                i++;
            }
        }
        return al;
    }
}

// left view
class Solution{
    //Function to return list containing elements of right view of binary tree.
    ArrayList<Integer> leftView(Node node) {
        
        ArrayList<Integer> al = new ArrayList<>();
        if( node == null ) return al;
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        
        while(q.size()> 0 ){
            int s = q.size();
            int i=1;
            while( s>=i ){
                Node p = q.remove();
                if( i==1) al.add(p.data);
				if(p.left != null  ) q.add(p.left);
                if(p.right != null ) q.add(p.right);
                
                i++;
            }
        }
        return al;
    }
}