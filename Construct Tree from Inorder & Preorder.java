class Solution
{
// TC: O(n^2)
    public static int search( int inorder[] , int preorder[] , int pres , int ins , int ine){
        int i=0;
        for( i=ins ; i<ine ; i++){
            if(inorder[i] == preorder[pres] )break;
        }
        return i;
    }
    public static Node construct( int inorder[],int ins,int ine , int preorder[] ,int pres,int pree){
        
        if(pres>pree) return null;
        int idx = search(inorder,preorder,pres,ins,ine);
        Node node = new Node(inorder[idx]);
        int lpres = pres+1 ;
        int lins = ins;
        int line = idx-1;
        int rins = idx+1;
        int rine = ine;
        int lpree = line-lins+lpres;
        int rpres = lpree+1;
        int rpree = pree;
        node.left = construct(inorder,lins,line,preorder,lpres,lpree);
        node.right = construct(inorder,rins,rine,preorder,rpres,rpree);
        
        return node;
    }
    public static Node buildTree(int inorder[], int preorder[], int n)
    {
        return construct(inorder,0,n-1,preorder,0,n-1);
        
    }
}
