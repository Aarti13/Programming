//TC:  O(n)  ,  SC:  O(n)
class Tree
{
    ArrayList<Integer> al = new ArrayList<>();
    public boolean rootToNode(Node root , int data){
        
        if( root == null ) return false;
        if( root.data == data ) {
            al.add(root.data); return true;
        }
        boolean lc = rootToNode(root.left , data );
        if( lc ){
            al.add(root.data); return true;
        }
        boolean rc = rootToNode( root.right , data );
         if( rc ){
            al.add(root.data); return true;
        }
       return false;
    }
    public int kthAncestor(Node root, int k, int node)
    {
        boolean a = rootToNode(root,node);
        
        if( al.size()-1 < k ) return -1;
        int i=0;
        while(i<k){
            i++;
        }
        return al.get(i);
    }
}