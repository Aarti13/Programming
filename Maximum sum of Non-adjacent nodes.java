class Solution
{
    //Function to return the maximum sum of non-adjacent nodes.
    
    public static HashMap<Node , Integer> hm = new HashMap<>();
    static int getMaxSum(Node node)
    {
        if( node == null ) return 0;
        if( hm.containsKey(node)) return hm.get(node);
		
        int inc = node.data ; // when we include that particular node
        if(node.left != null ) inc += getMaxSum(node.left.left) +
                                      getMaxSum(node.left.right);
        if(node.right != null ) inc += getMaxSum(node.right.left) +
                                      getMaxSum(node.right.right);
                                      
        int exe = getMaxSum(node.left)+getMaxSum(node.right); // when we notinclude that particular node
        hm.put(node,Math.max(exe,inc));
        return Math.max(exe,inc);
    }
}















