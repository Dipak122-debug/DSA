1. BFS of binary tree is a traversal technique that visits all the nodes of a binary tree level by level, starting from the root node and moving down to the leaf nodes. It uses a queue data structure to keep track of the nodes to be visited next.

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // v -> number of vertices
        // adj -> adjacency list representation of the graph
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        boolean visited[] = new boolean[V];
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        q.offer(0);
        visited[0]=true;
        
        // Traverse the graph using BFS
        while(!q.isEmpty()){
            
            Integer tmp = q.poll();
            
            ans.add(tmp);
            // Traverse all the adjacent nodes of the current node
            for(Integer i : adj.get(tmp)){
                if(!visited[i]){
                    q.offer(i);
                    visited[i]=true;
                }
            }
        }
        return ans;
    }
}

TC - O(V + E) where V is the number of vertices and E is the number of edges in the graph.
V+E is derived - In BFS, we visit each vertex once and explore all its adjacent vertices. Therefore, the time complexity is O(V + E), where V is the number of vertices and E is the number of edges in the graph.
SC - O(V) for the visited array and the queue used for BFS.

2. DFS -> Depth First Search
depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) 
and explores as far as possible along each branch before backtracking.


word Search
link - https://leetcode.com/problems/word-search/description/

solution
class Solution {   
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board,word,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, int i, int j, int idx){
        if(idx == word.length()){
            return true;
        }
        
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j] != word.charAt(idx)){
            return false;
        }
        
        char temp = board[i][j];
        board[i][j] = '#';
        
        boolean found = dfs(board,word,i+1,j,idx+1) || dfs(board,word,i-1,j,idx+1) || dfs(board,word,i,j+1,idx+1) || dfs(board,word,i,j-1,idx+1);
        
        board[i][j] = temp;
        
        return found;
    }
}