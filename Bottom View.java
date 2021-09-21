class Solution
{
    class Pair{
        Node n;
        int d;
        
        Pair(Node n , int d){
            this.n = n;
            this.d = d;
        }
    }
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node node)
    {
        ArrayList <Integer> al = new ArrayList <>();
        if(node == null ) return al;
        
        Queue<Pair> q = new ArrayDeque<>();
        HashMap<Integer , Integer> hm = new HashMap<>();
        
        q.add(new Pair(node , 0));
        int min = Integer.MAX_VALUE , max = Integer.MIN_VALUE;
        
        while(q.size()> 0 ){
            Pair p = q.remove();
            
            hm.put(p.d, p.n.data );
            max = Math.max(max, p.d);
            min = Math.min(min , p.d);
           
            if( p.n.left != null ) q.add(new Pair(p.n.left , p.d-1 ) );
            if( p.n.right != null )q.add(new Pair(p.n.right , p.d+1 ) );
        }
        for( int i=min ; i<=max ; i++ ){
            al.add(hm.get(i));
        }
        return al;
    }
}