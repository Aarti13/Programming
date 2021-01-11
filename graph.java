// graph
// can be represented in two form 
/*
    1. Adjacency matrix : m*n matrix ver * ver (rep edges)
        vertex < 10000
    2. Adjacency List : array of arraylist (source , nbr , weight )
*/
// N/W based information for reaching from source to dest
// 1. print all the paths
// 2. shortest path- in terms of edges BFS
//                in terms of wt - DIJIKSTRA 
// 3. MST for connection of all nodes with min cabel required
//          PRIMS , KRUSKAL
// 4. files are dependent on other files so how to compile them
//          topological sort

///////////////////////////////////////////////////////////////////////////////////////////////////////////
// basic structure of graph
import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList < Edge > [] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList < > ();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
    }
}

// checking if there is path from src to dest or not if yes what is the path
bool hasPath(int src,int des,vector<bool> & vis,string s)
{
    if(src==des)
        {
            cout<<s+to_string(src);
            return true;
        }
    vis[src]=true; //mark particular vertex through which we traverse 
    // why because 1 - ask 0 to tell path
    // as way 0 ask 1 to tell path and we stuck btwn them undirected graph
    bool res=false;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        if(!vis[nbr])
            res=res|| hasPath(nbr,des,vis,s+to_string(src)+"->");
    }
    return res;
}

//all the path from src to dest
int  allhasPath(int src,int des,vector<bool> & vis,string s)
{
    if(src==des)
        {
            cout<<s+to_string(src)<<endl;
            return 1;
        }
    vis[src]=true; //mark particular vertex through which we traverse 
    int count=0;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        if(!vis[nbr])
            count+= allhasPath(nbr,des,vis,s+to_string(src)+"->");
    }
    // why taki hum baki ke paths nikal pae
    vis[src]=false;//unmark vertex
    return count;
}
//all preorderpaths / post order
void  preOrderPath(int src,int wt,vector<bool> & vis,string s)
{
    vis[src]=true; //mark particular vertex through which we traverse 
    // for pre order
    cout<<to_string(src)+":  "+s+to_string(src)+" @ "+to_string(wt)<<endl;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
            preOrderPath(nbr,w+wt,vis,s+to_string(src)+"->");
    }
    // cout<<to_string(src)+":  "+s+to_string(src)+" @ "+to_string(wt)<<endl;// for post order
    vis[src]=false;//unmark vertex
}
/* // on basis of wt
Smallest path , largest path , just largest path(ceil) , just smallest path(floor) , 3rd largest path
*/
int maxwt=0;
int minwt=10000000;
string ans="";

//maximum wt path
void maxwtPath(int src,int des,int wt,vector<bool> & vis,string s)
{
    if(src==des)
        {
            if(maxwt<wt) // for min wt path f(minwt>wt )
            {
                maxwt=wt; // minwt= wt;
                ans= s+to_string(src)+" @ "+to_string(maxwt);
            }
        }
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
             maxwtPath(nbr,des,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
}

// Ceil , Floor  Path 
int cd = 1e7;
int fd = -1e7;
string cp ="";
string fp = "";

void Ceil_Floor_Path( int src , int des , int wt , vector<bool> & vis , string s , int cf )
{
    if( src == des )
    {
        if( wt > cf && wt < cd ) // cf the val for just grt just larg
        {
            cd = wt ;// ceil
            cp= s+to_string(src)+" @ "+to_string(cd);
        }
        if( wt < cf && wt > fd )
        {
            fd = wt ; // floor
            fp= s+to_string(src)+" @ "+to_string(fd);
        }
    }

    vis[src]=true;

    for( int i = 0 ; i<graph[src].size() ; i++){
        int nbr = graph[src][i]->v;
        int w = graph[src][i]->w;
        if(!vis[ nbr ])
            Ceil_Floor_Path( nbr , des , w+wt , vis , s+to_string(src)+"->" , cf);
    }
    vis[src]= false;
}
/* 1) Initialize all vertices as not visited.
    2) Do following for every vertex 'v'.
       (a) If 'v' is not visited before, call DFSUtil(v)
       (b) Print new line character

    DFSUtil(v)
    1) Mark 'v' as visited.
    2) Print 'v'
    3) Do following for every adjacent 'u' of 'v'.
     If 'u' is not visited, then recursively call DFSUtil(u)
     unmark
*/
// isconnected  (if gcc_count = 1)
// iscyclic      (if (vis[s])  then return true)
// tree ( if there exist no cycle )
// forest ( if there is no cycle and gcc > 1 )

// get Connected components in java  O(V+E)
 boolean[] visited = new boolean[vtces];
 ArrayList < ArrayList < Integer >> comps = new ArrayList < > (); // components
        for (int v = 0; v < vtces; v++) {
            if (visited[v] == false) {
                ArrayList < Integer > comp = new ArrayList < > ();
                gcc(graph, v, visited, comp);
                comps.add(comp);
            }
        }
 System.out.println(comps.size() == 1);

    public static void gcc(ArrayList < Edge > [] graph, int src, boolean[] visited, ArrayList < Integer > comp) {
        comp.add(src);
        visited[src] = true;
        for (Edge e: graph[src]) {
            if (!visited[e.nbr]) {
                gcc(graph, e.nbr, visited, comp);
            }
        }

// hamiltinoan path ie we should traverse through all vertices only once 
// hamiltonian path = cycle (when tehre is a direct link btw source and dest)
// and find n the min wt path
void hamiltonianPathCycle(int src,int osrc,int count,int wt,vector<bool> & vis,string s)
{
    if(count== graph.size()-1)   //hamiltonian Path
    {
         cout<<endl<<s+to_string(src)+" @ "+to_string(wt);
         for(Edge * e: graph[src])       //hamiltonian Cycle
         {
             if(e->v == osrc)
             cout<<"  CYCLE  ";
         }
         return ; 
    }
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
             hamiltonianPathCycle(nbr,osrc,count+1,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
}

 //********************************************************
int counter=0;
// Knight Tour  Path problem :
// saarein cells ko visit karde without visiting any cell twice
// moves : 2 stright den one left or right 
void tour(vector<vector <int>> & box ,int r , int c , int csf)
{
    if( r<0 || c<0 || r<=box.size() || c<=box.size() || box[r][c] != 0) return ;
    // csf : moves number
    else if( csf == box.size()* box.size()- 1)
    {
           counter++;
            cout<<"*******" <<counter<< "*******" ;
            box[r][c] = csf;
        // for display : 8*8 chess board
            for(int i = 0; i < box.size(); i++){
                for(int j = 0; j < box.size(); j++){
                    cout<<box[i][j] <<endl;
                }
                cout<<endl;
            }
            box[r][c] = 0;
            cout<<"*******" <<counter << "*******" ;
            return;
    } 
    
     box[r][c] = csf; // chess board
    tour(box,r-2, c-1, csf+1);
    tour(box,r-1, c-2 , csf+1);
    tour(box,r+1, c-2 , csf+1);
    tour(box,r+2, c-1 , csf+1);
    tour(box,r+2, c+1 , csf+1);
    tour(box,r+1, c+2 , csf+1);
    tour(box,r-1, c+2 , csf+1);
    tour(box,r-2, c+1 , csf+1);
     box[r][c] = 0;
}

//*********************************************

//BFS: (Breadth First Search )
bool bfs(int s, int d){
    vector<bool> visited (graph.size(), false);
    queue<int> q;

    q.push(s);
    while(q.size() > 0){
        // grmpa
        int rem = q.front();
        q.pop();

        if(visited[rem] == true){
            continue;
        }
        visited[rem] = true;
        
        if(rem == d){
            return true;
        }

        for(int n = 0; n < graph[rem].size(); n++){

            if(visited[graph[rem][n]->v] == false){
                q.push(graph[rem][n]->v);
            }
        }
    }

    return false;
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.LinkedList;

public class graph
{
    public static void main(String [] agr)
    {
        solve();
    }

    public static class Edge
    {
        int v=0;
        int w=0;

        Edge(){}

        Edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }
    public static class Path
    {
        int vtx=0;
        int wsf=0;
        String psf="";

        Path(){}

        Path(int vtx,int wsf,String psf)
        {
            this.vtx=vtx;
            this.wsf=wsf;
            this.psf=psf;
        }
    }

    static  ArrayList <Edge> [] graph= new ArrayList[7];

    public static void addEdge(int v,int u,int w)
    {
        if( u < 0  || v<0 || u>graph.length || v>graph.length )
        return;
        graph[u].add(new Edge (v,w));
        graph[v].add(new Edge (u,w));
    }

    public static void display()
    {
        for(int i=0;i<graph.length;i++)
        {
            System.out.print(i+"->");
            for(int j=0 ; j<graph[i].size();j++)
            {
            System.out.print("("+graph[i].get(j).v+" , "+graph[i].get(j).v+")");
            
            }
            System.out.println();
        }
    }


//****************************************************************//
//     BFS........
 ArrayDeque < Pair > queue = new ArrayDeque < > ();
        queue.add(new Pair(src, src + ""));
        boolean[] visited = new boolean[vtces];
        while (queue.size() > 0) {
            Pair rem = queue.remove();

            if (visited[rem.v] == true) {
                continue;
            }
            visited[rem.v] = true;
            System.out.println(rem.v + "@" + rem.psf);

            for (Edge e: graph[rem.v]) {
                if (visited[e.nbr] == false) {
                    queue.add(new Pair(e.nbr, rem.psf + e.nbr));
                }
            }
    // iscyclic : 
    public static int BFSShortestPath(int src,int des,boolean[] vis)
    {
        LinkedList <Path> que=new LinkedList<> ();      //declare a LInked List as a queue 
        Path root=new Path(src,0,src+"");      //initialize the Path class 
        boolean isPath= false;
        int cyclec=1;      //cycle counter

        que.addLast(root);    // add src to the queue   e enqueue the src

        while(!que.isEmpty())   //traverse until queue become empty
        {
            Path rpair= que.removeFirst(); //dequeue the vertex from queue

            //for tree and forest *********************************
             if(vis[rpair.vtx])        /// for cycle   for this we have to mark the vtx later 
            {
                //**************for tree */
                //  System.out.println("CYCLE:"+cyclec+"   "+rpair.psf);
                  cyclec++;
            }

            vis[rpair.vtx]=true;   //mark the vertex visited 

            if(rpair.vtx == des && !isPath)   //if w ereach at destintion print the path
            {
              //for tree*************** 
              //  System.out.print(rpair.psf+" @ wt "+ rpair.wsf);
                 isPath=true;
            }

            for(Edge e: graph[rpair.vtx])    //loop until all the adjacent vertices  of the particular vertex
            {
                if(! vis[e.v])   // is that  adjacent vertex is no visited add to the queue
                {
                    Path pair=new Path(e.v , rpair.wsf + e.w , rpair.psf +"=>"+e.v );
                    que.addLast(pair);
                }
            }

        }
        return cyclec;
    }
 
 public static int GCC(int src,int des,boolean [] vis)
{
    int len=0;
    for(int i=0;i<graph.length ;i++)
    {
        if(!vis[i])
        {
            BFSShortestPath(i,des,vis);
            len++;
        }
    }
    return len;
}

//***************************************************//
// Bipartite graph if a graph can be divided into two sets in which there is no edge 
// between vertecies of one set ie all edges are across sets
// 2 mutually exhaustive and exclusive sets
// no cycle =bipart , cycle(odd length = no bipart), even = bipart
    
//  public static class BPair    // Bipartite class
//     {
//         int vtx=-1;
//         String colour="";

//         BPair(){}

//         BPair(int vtx,String colour)
//         {
//             this.vtx=vtx;
//             this.colour=colour;
//         }
//     }

// public static boolean BipartiteGraph(int src ,String [] vis)
// {
//     LinkedList<BPair> que=new LinkedList<>();
//     BPair root=new BPair(src,"R");
//     que.addLast(root);

//     boolean res=true;

//     while(que.size() > 0)
//     {
//         BPair bpair= que.removeFirst();
//         vis[bpair.vtx]=bpair.colour;

//         if(vis[bpair.vtx] != "")
//         {
//             if( ! vis[bpair.vtx].equals(bpair.colour))
//             {
//                 System.out.println(bpair.vtx + "  NOT Bipartitte");
//                 res=false;
//             }
//             else
//             System.out.println(bpair.vtx + "  Bipartitte");

//             continue;
//         }

//         for(Edge e:graph[bpair.vtx])
//         {
//             if(vis[e.v] == "")
//             {
//                 BPair bp= new BPair(e.v, bpair.colour.equals("R") ? "G" : "R");
//                 que.addLast(bp);
//             }
//         }
//     }

//     return res;
// }
 HashMap < Integer, Integer > visited = new HashMap < > ();
        for (int v = 0; v < vtces; v++) {
            if (!visited.containsKey(v)) {
                boolean bip = IsBipartite(graph, v, visited);
                if (!bip) {
                    System.out.println(false);
                    return;
                }
            }
        }

        System.out.println(true);
    }

    static class Pair {
        int vtx;
        int level;

        Pair(int vtx, int level) {
            this.vtx = vtx;
            this.level = level;
        }
    }

    public static boolean IsBipartite(ArrayList < Edge > [] graph, int src, HashMap < Integer, Integer > visited) {
        ArrayDeque < Pair > queue = new ArrayDeque < > ();
        queue.add(new Pair(src, 0));
        while (queue.size() > 0) {
            Pair rem = queue.remove();

            if (visited.containsKey(rem.vtx)) {
                if (visited.get(rem.vtx) % 2 != rem.level % 2) {
                    return false;
                }
            } else {
                visited.put(rem.vtx, rem.level);
            }

            for (Edge e: graph[rem.vtx]) {
                if (!visited.containsKey(e.nbr)) {
                    queue.add(new Pair(e.nbr, rem.level + 1));
                }
            }
        }

        return true;
    }
////////////////////////
 //Dijikstra Algorithm : All source shortest path
 // O(E log V) 
 static class Pair implements Comparable < Pair > {
        int v; String psf; int wsf;
        Pair(int v, String psf, int wsf) {
            this.v = v;
            this.psf = psf;
            this.wsf = wsf;
        }
     public int compareTo(Pair o) return this.wsf - o.wsf;
    }
 PriorityQueue < Pair > queue = new PriorityQueue < > ();
        queue.add(new Pair(src, src + "", 0));
        boolean[] visited = new boolean[vtces];
        while (queue.size() > 0) {
            Pair rem = queue.remove();

            if (visited[rem.v] == true) {
                continue;
            }
            visited[rem.v] = true;
            System.out.println(rem.v + " via " + rem.psf + " @ " + rem.wsf);

            for (Edge e: graph[rem.v]) {
                if (visited[e.nbr] == false) {
                    queue.add(new Pair(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
                }
            }
        }
    }
//Prims Algorithm 
// MST : subgraph, tree(connected , Acyclic), spanning(all vtx)
// vtx = vtx , pvtx = vtx , wt = wt

class Primpair
{
    public:
    int vtx=-1, pvtx=-1 , wt=-1, wsf=-1 ;
    string psf="";
    // same as dijikstra only wt pe PQ banege
    bool operator < (const Primpair  & o ) const   
    {
        return this->wt > o.wt ;  
    }
};

void PrimGraph(int src ,int des, vector <bool> & vis)
{
    priority_queue <Primpair> que;   // make a que for dijikpair 
    Primpair root(src,-1,0,0,to_string(src)+"");       // initialize the que by adding root
    que.push(root);                                      

    while(que.size()>0)
    {
        Primpair rpair= que.top();   // extract top elemnt in rpair
        que.pop();                    // remove the element

        if(vis[rpair.vtx])            // if vtx is already visited   ie cycle
        continue;

        if(vis[rpair.vtx] != -1)        // for making an spanning tree graph
        addEdge2(rpair.vtx , rpair.pvtx , rpair.wt);
 
        if(rpair.vtx == des)                 // if we reach at dest
        cout<<"MINIMUM wt Path:   "<< rpair.psf<<" @ wt  "<< rpair.wsf<<endl;

        vis[rpair.vtx]=true;           //mark that vtx 

        for(Edge * e: graph[rpair.vtx])
        {
            if( ! vis[e->v] )
            {
                Primpair npair(e->v , rpair.vtx , e->w , rpair.wsf + e->w , rpair.psf+" ->"+ to_string(e->v));
                que.push(npair);
            }
        }
    }
     display2();
}
// //Kruskal Algorithm

 vector<int> par(graph.size());
 vector<int> size(graph.size(),1);

int Find(int  vtx)           //find function to find the par+ent of the vertex
{
    if(par[vtx] != vtx)
      par[vtx]= Find(par[vtx]);      // perform recusion to find the super parent it also perform compression

     return par[vtx];
} 

void Union(int v1 , int v2 )        
{
    if( size[v1 ] <= size[ v2 ] )
    {
         par[v1] = v2 ;
         size[v2] += size[v1] ;
    }
    else
    {
        par[v2] = v1 ;
         size[v1] += size[v2] ;
    }
}

void KruskalGraph()
{
    for(int i=0 ; i< graph.size() ;i++)
    {
        par[i]=i;
    }

    Kpair root();       // initialize the que by adding root
    que.push(root);  

    while(que.size()>0)
    {
        Kruskalpair rpair= que.top();
        que.pop();

        int a=Find(rpair.vtx);
        int b=Find(rpair.pvtx);
        if(a != b)
        {
            addEdge2(rpair.vtx, rpair.pvtx , rpair.wt );
            Union(a,b);
        }
    }
    display2();
    
}
 /*************************************************//
//Articulation points

public static int time=0;
public static int root=0;
public static int[] par= new int[graph.length];
public static int[] dis= new int[graph.length];
public static int[] low= new int[graph.length];
public static boolean[] point= new boolean[graph.length];

public static void ArticulationPoints(int vtx, int parent)
{
    dis[vtx] = low[vtx] = time++ ;

    for(Edge e: graph[vtx])
    {
        if( dis[e.v] == -1)
        {
            par[e.v]=vtx;

            if(parent == -1)
                root++;
            
            ArticulationPoints(e.v, vtx);
            
            if(dis[vtx] <= low[ e.v] )  //dis should be low then low
                point[vtx]=true;
            low[vtx]= Math.min( low[vtx] , low[e.v]);
        }

        else if( par[vtx] != e.v)   
            low[vtx]= Math.min( low[vtx] , dis[e.v]);
    }
}

 //***************************************************//
    public static void solve()
    {
        
        for(int i=0;i<graph.length;i++)
        {
            graph[i]=new ArrayList<Edge> ();
        }

        addEdge( 0,1,10);
        addEdge( 0,2,10);
        addEdge( 2,3,40);
        addEdge( 1,3,10);
        addEdge( 2,4,2);
        addEdge( 4,5,2);
        addEdge( 5,6,8);
        addEdge( 4,6,3);
        
        //display();

        boolean [] visited=new boolean[graph.length];
       // BFSShortestPath(0,6,visited);

      // BFSCycleNullHeight(0,6,visited);
      //BFSHeight(0,6,visited);
     
     // int a=GCC(0,8,visited);

      //System.out.println("Connected Components:"+a);
      //boolean a=BipartiteGraph(0);
      //System.out.println(a);

    // for finding graph is connected or not 
    // if gcc is 1 connected
        // int a=GCC(0,8,visited);
        // if(a == 1)  
        //  System.out.println("Graph is connected ") ;
        //  else
        //   System.out.println(" Graph is not connected");

    //for finding graph is tree or not
    // if graph contain no cycle
        //  int a=BFSShortestPath(0,6,visited);
        // if(a == 0)  
        //  System.out.println("Graph is a Tree ") ;
        // else
        //   System.out.println(" Graph is not a Tree");

//for finding graph is forest or not
    // if graph contain no cycle and gcc>1 then only it is a forest
        //  int a=BFSShortestPath(0,6,visited);
        //  int b=GCC(0,6,visited);
        // if(a == 0 && b>1 )  
        //  System.out.println("Graph is a Forest ") ;
        // else
        //   System.out.println(" Graph is not a Forest");

    //Bipartite Graph
    //   String [] vis= new String[graph.length];
    //   for(int i=0;i<vis.length ;i++)
    //      {vis[i]="";
    //      }

    //      for(int i=0;i<vis.length ;i++)
    //      {
    //          if(! vis[i].equals(""))
    //          {
    //              Bipartiate(i) ;
    //              System.out.println( );
    //          }
    //      }

        Bipartiate(0);
    
      //articulation points
    //   for(int i=0;i<graph.length ;i++)
    //   {
    //       par[i]=i;
    //       dis[i]=-1;
    //       point[i]=false;
    //   }

    //   ArticulationPoints(0, -1);

    //   point[0]= (root > 1 ? true: false);
    //   for(int i=0;i<graph.length ;i++)
    //   {
    //       if(point[i])
    //       System.out.println(i);
    //   }
    //   ///******************* */

    }
     
}
   



   
