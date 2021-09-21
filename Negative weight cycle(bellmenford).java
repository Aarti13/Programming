
class Solution
{
    public int isNegativeWeightCycle(int n, int[][] edges)
    {
        int[] arr= new int[n];
        for( int i=0 ; i<n ; i++ ){
            arr[i] = Integer.MAX_VALUE;
        }
        arr[0]=0;
        for(int j=0 ; j<n-1 ; j++ ){
            for( int i=0 ; i<edges.length ; i++){
                int u = edges[i][0];
                int v = edges[i][1];
                int wt= edges[i][2];
                
                if(arr[u] == Integer.MAX_VALUE ) continue;
                if( arr[u] + wt < arr[v] ){
                    arr[v] = arr[u]+wt;
                }
            }
        }
        
        for( int i=0 ; i<edges.length ; i++){
                int u = edges[i][0];
                int v = edges[i][1];
                int wt= edges[i][2];
                
                if(arr[u] == Integer.MAX_VALUE ) continue;
                if( arr[u] + wt < arr[v] ){ return 1;
                }
            }
            return 0;
    }
}