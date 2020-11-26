    // ***************** journey to moon ***************

#include<iostream>
#include<vector>

using namespace std;

class Edge
{
    public:
    int v=0;

    Edge() {}

    Edge(int v)
    {
        this->v=v;
    }
};

long value=0 ,m,n, prev_value=0,answer=0;
 vector < vector <Edge *>> graph ;

void addEdge(int u ,int v)
{
   graph[u].push_back(new Edge(v)) ;
   graph[v].push_back(new Edge(u)) ;
}

void DFS(int src, vector<bool> & vis)
{
    vis[src]=true;
    for(Edge * e:graph[src])
    {
        if( ! vis[e->v]) 
            DFS(e->v,vis);
    }
     value++; 
}

void solve()
{
    cin>>m>>n;
    for(int i=0;i<m;i++)
    {
        vector<Edge *> ar;
        graph.push_back(ar);
    }
    
    for(int i=0;i<n;i++)
    {   
        long u,v;
        cin>>u>>v;
         addEdge(u,v);
    }

   
   
   vector<bool> vis(graph.size(), false);
   for(int i=0;i<graph.size() ;i++)
   {
       if(! vis[i])
       {
            DFS(i , vis);
            answer +=prev_value * value;
            prev_value +=value;
            value=0;
       }
   }
    cout<<answer;
}
int main()
{
    solve();
    return 0;
}


    // ***************** Course Schedule ***************

#include<iostream>
#include<vector>
#include<list>

using namespace std;

class Edge
{
    public:
    int v=0;

    Edge() {}

    Edge(int v)
    {
        this->v=v;
    }
};

int main()
{
    int m;
    cin>>m;
    vector < vector <Edge *>> graph ;
    
    for(int i=0;i<graph.size();i++)
    {   
        vector<Edge *> ar;
        graph.push_back(ar);
        long u,v;
        cin>>u>>v;
         addEdge(u,v);
    }

    solve(m, graph);
    return 0;
}

void addEdge(int u ,int v)
{
   graph[u].push_back(new Edge(v)) ;
}

bool DFS(int src, vector<bool> & vis ,vector<bool> & cycle ,vector<int> & stack)
{
    vis[src]=true;
    cycle[src]=true;

    bool res=false;
    for(Edge * e:graph[src])
    {
        if( ! vis[e->v]) 
           res=res|| DFS(e->v,vis , cycle ,stack);
        else if( cycle[e->v] )
        return true;
    }
     
     stack.push_back(src);
     cycle[src]=false;
     return res;
}

void solve(int m,vector < vector <Edge *>> & graph)
{
    bool res=true;
   
   vector<bool> vis(graph.size(), false);
   vector<bool> cycle(graph.size(), false);
   vector<int>stack;
   
   for(int i=0;i<graph.size() ;i++)
   {
       if(! vis[i])
       {
            if( DFS(i , vis, cycle,stack) )
                res=false;
       }
   }

    cout<<"[";
   for(int i=0; i<=stack.size() -1   && res ;i++)
         cout<<stack[i] <<",";
    cout<<"]";     
}
