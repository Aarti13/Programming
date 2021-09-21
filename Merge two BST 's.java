class Solution
{
    //Function to return a list of integers denoting the node 
    //values of both the BST in a sorted order.
    static ArrayList<Integer> al = new ArrayList<>();
    public void BST(Node root){
        
        if( root == null ) return ;
        BST(root.left);
        al.add(root.data);
        BST(root.right);
    }
    public List<Integer> merge(Node root1,Node root2)
    {
        BST(root1);
        ArrayList<Integer> al1 = al;
        System.out.print(al1);
        al = new ArrayList<>();
        BST(root2);
        ArrayList<Integer> al2 = al;
        System.out.print(al2);
        
        List<Integer> ans = new ArrayList<>();
        int i=0,j=0;
        while( i<al1.size() && j<al2.size() ){
            if( al1.get(i) < al2.get(j) ){
                ans.add( al1.get(i) );
                i++; 
            }
            else {
                ans.add( al2.get(j) );
                j++; 
            }
        }
        
        while( i<al1.size() ){
                ans.add( al1.get(i) );
                i++; 
        }
        
        while( j<al2.size() ){
                ans.add( al2.get(j) );
                j++; 
        }
        return ans;
    }
}
