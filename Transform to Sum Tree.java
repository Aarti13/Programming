Input:
             10
          /      \
        -2        6
       /   \     /  \
     8     -4   7    5

Output:
            20
          /    \
        4        12
       /  \     /  \
     0     0   0    0

Explanation:

           (4-2+12+6)
          /           \
      (8-4)          (7+5)
       /   \         /  \
      0     0       0    0

class Solution{
    public int constructSumTree(Node node){
        
        if(node == null ) return 0;
        int ls = constructSumTree(node.left);
        int rs = constructSumTree(node.right);
        int temp = node.data;
        int sum = ls+rs;
        node.data = sum;
        return temp+sum;
    }
    public void toSumTree(Node node){
        
       int ans = constructSumTree(node); 
    }
}