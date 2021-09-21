class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static class pair{
        Node n;
        int d;
        
        pair(Node n , int d){
            this.n = n;
            this.d = d;
        }
    }
    static ArrayList<Integer> topView(Node node)
    {
        ArrayList<Integer> al = new ArrayList<>();
        if(node == null) return al;
        Queue<pair> q = new ArrayDeque<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        
        q.add(new pair(node,0));
        int min=Integer.MAX_VALUE , max= Integer.MIN_VALUE;
        while( q.size() > 0 ){
                pair p = q.remove();
                if(!hm.containsKey(p.d)){
                     hm.put(p.d,p.n.data);
                     min=Math.min(min,p.d);
                     max=Math.max(max,p.d);
                    //  al.add(hm.get(p.d));
                }
                if(p.n.left != null ) 
                    {
                        q.add(new pair(p.n.left,p.d-1)); 
                    }
                if(p.n.right != null )
                    { 
                        q.add(new pair(p.n.right,p.d+1)); 
                    }
        }
        //System.out.println(min+" "+max);
        for(int i=min;i<=max;i++)
        {
            al.add(hm.get(i));
        }
        return al;
    }
}