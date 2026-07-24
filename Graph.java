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

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. DFS -> Depth First Search
depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) 
and explores as far as possible along each branch before backtracking.

solution
class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();// number of vertices
        ArrayList<Integer> ans = new ArrayList<>();// to store the result of DFS traversal
        boolean[] visited = new boolean[v];// to keep track of visited vertices
        
        dfsHelper(0,adj,visited,ans);// start DFS from vertex 0
        
        
        return ans;
    }
    
    public void dfsHelper(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited,ArrayList<Integer> ans){
        // mark the current node as visited and add it to the result list
        visited[node]=true;
        ans.add(node);

        // recursively visit all the unvisited neighbors of the current node
        for(int neighbour : adj.get(node)){
            if(!visited[neighbour]){
                dfsHelper(neighbour,adj,visited,ans);
              }
        }
    }
TC - O(V + E) where V is the number of vertices and E is the number of edges in the graph.
V+E is derived - In DFS, we visit each vertex once and explore all its adjacent vertices. Therefore, the time complexity is O(V + E),
where V is the number of vertices and E is the number of edges in the graph.
SC - O(V) for the visited array and the recursion stack used for DFS. For each vertex, there is recusrion call, so the maximum depth 
of the recursion stack can be equal to the number of vertices in the graph.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
3. Number of Islands
link - https://leetcode.com/problems/number-of-islands/description/ 
solution
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == '1'){
                    count++; // Increment the count of islands when a land cell ('1') is found
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    /* Depth-First Search helper function */

    public void dfs(char[][] grid, int i, int j){
        // Check for out-of-bounds indices and if the current cell is water ('0')
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] != '1'){
            return;
        }
        
        // Mark the current cell as visited by changing it to water ('0')
        grid[i][j] = '0';
        
        // Recursively visit all adjacent cells (up, down, left, right)
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}

 why count is incremented 1 times thru recursive calll ?
    -> The count is incremented only once for each island because the DFS function marks all connected land cells ('1') as visited (by changing them to '0'). When the DFS is called for a land cell, 
    it explores all adjacent land cells recursively, effectively "sinking" the entire island. Therefore, when the DFS completes, all cells of that island have been marked as visited, 
    and the count is only incremented once for that entire island.

TC - O(M*N) where M is the number of rows and N is the number of columns in the grid. Each cell is visited once during the DFS traversal.
SC - O(M*N) in the worst case, where the grid is filled with land cells
M*N --> recursion stack space used for DFS traversal in the worst case when the entire grid is filled with land cells.    

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
4. Max Area of Island
link - https://leetcode.com/problems/max-area-of-island/description/
solution
class Solution {
    public int maxAreaOfIsland(int[][] grid) {

        int row=grid.length;
        int col = grid[0].length;
        int ans=0;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int[] count  = new int[1]; // Use an array to keep track of the area of the current island
                if(grid[i][j]==1){
                    dfs(grid,i,j,row,col,count);
                    ans=Math.max(ans,count[0]); // Update the maximum area found so far
                }
            }
        }

        return ans;
        
    }
    // Depth-First Search helper function to explore the island and count its area
    private void dfs(int[][] grid, int i, int j, int row, int col, int[] count){
        if(i<0 || i>=row || j<0 || j>=col || grid[i][j]==0){
            return;
        }

        grid[i][j]=0;// Mark the current cell as visited by changing it to water (0)
        count[0]++; //Increment the area count for the current island

        // Recursively visit all adjacent cells (up, down, left, right)
        dfs(grid,i,j+1,row,col,count);
        dfs(grid,i-1,j,row,col,count);
        dfs(grid,i,j-1,row,col,count);
        dfs(grid,i+1,j,row,col,count);
    }

}

5. Word Search
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


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

6. Rotten Oranges
link - https://leetcode.com/problems/rotting-oranges/description/   

solution
class Solution {
    // Function to check if the given coordinates (x, y) are within the bounds of the grid
    public boolean isSafe(int x, int y, int row, int col){
        return(x>=0 && x<row && y>=0 && y<col);
        }
  
    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>(); // Queue to store the coordinates of rotten oranges

        int freshOranges=0, min=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                // Count the number of fresh oranges and add the coordinates of rotten oranges to the queue
                if(grid[i][j]==1) freshOranges++; 
                
                // Add the coordinates of rotten oranges to the queue for BFS traversal
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }
            }
        }

        // Perform BFS to rot the fresh oranges
        // why freshOranges>0 is used in the while loop condition?
        // The condition `freshOranges > 0` is used in the while loop to ensure that the BFS continues only as long as there are fresh oranges left to rot. 
        // If there are no fresh oranges remaining, there's no need to continue the BFS, and the loop can terminate early. This helps optimize the algorithm 
        // by avoiding unnecessary iterations once all fresh oranges have been processed.
        
        while(!q.isEmpty() && freshOranges>0){

            min++;

            int size = q.size();

            for(int i=0;i<size;i++){
                 int[] cell= q.poll();
                 int x = cell[0];
                 int y= cell[1];
                // Check the four adjacent cells (up, down, left, right) for fresh oranges and rot them if found
                 if(isSafe(x,y+1,n,m) && grid[x][y+1]==1){
                    grid[x][y+1]=2;
                    q.offer(new int[]{x,y+1});
                    freshOranges--;
                 }
                 if(isSafe(x-1,y,n,m) && grid[x-1][y]==1){
                    grid[x-1][y]=2;
                    q.offer(new int[]{x-1,y});
                    freshOranges--;
                 }
                 if(isSafe(x,y-1,n,m) && grid[x][y-1]==1){
                    grid[x][y-1]=2;
                    q.offer(new int[]{x,y-1});
                    freshOranges--;
                 }
                 if(isSafe(x+1,y,n,m) && grid[x+1][y]==1){
                    grid[x+1][y]=2;
                    q.offer(new int[]{x+1,y});
                    freshOranges--;
                 }
                
            }
        }

        // After the BFS traversal, check if there are any fresh oranges left. If there are no fresh oranges remaining, return the minimum time taken to rot all oranges.
        return freshOranges==0 ? min : -1;
        
    }

    TC - O(M*N) where M is the number of rows and N is the number of columns in the grid. Each cell is visited once during the BFS traversal.
    SC - O(M*N) in the worst case, where the grid is filled with rotten oranges. The queue used for BFS can hold all the rotten oranges in the grid at once.
}


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

What is topological sorting?
Topological sorting is a linear ordering of the vertices in a directed acyclic graph (DAG) such that for every directed edge (u, v), vertex u comes before vertex v in the ordering. In other words, 
it provides a sequence in which tasks can be performed given their dependencies.

Example: Consider a set of tasks with dependencies, such as "Task A must be completed before Task B." A topological sort of these tasks would provide an order in which 
the tasks can be completed while respecting the dependencies.

example of topological sorting:
Consider the following directed acyclic graph (DAG):    

0 → 1 → 3
 \       ↑
  \     /
   → 2

topological sort of the above graph could be: 
0, 2, 1, 3 or 0, 1, 2, 3. 

Kahn's algorithm is a popular method for performing topological sorting. It works by repeatedly removing nodes with no incoming edges (i.e., nodes with no dependencies) and adding them to the sorted order.

class Solution {
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {

        int[] indegree = new int[V];

        // Calculate indegree
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++; // Increment the indegree of the neighbor node for each edge in the graph
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Add nodes with indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int[] topo = new int[V]; // Array to store the topological order of the vertices
        int index = 0; // Index to keep track of the position in the topo array

        while (!queue.isEmpty()) {

            int node = queue.poll();
            topo[index++] = node;
            // For each neighbor of the current node, decrement its indegree and add it to the queue if its indegree becomes 0
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;

                // If the indegree of the neighbor becomes 0, add it to the queue for processing
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        return topo;
    }
}

1. course schedule
link - https://leetcode.com/problems/course-schedule/description/

solution
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph of course dependencies
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph based on prerequisites
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            adj.get(prereq).add(course); // Add an edge from prereq to course
        }

        // Perform topological sorting using Kahn's algorithm
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Initialize a queue to store courses with no prerequisites (indegree 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0; // Count of courses that can be completed

        while (!queue.isEmpty()) {
            int node = queue.poll(); // Remove a course with no prerequisites from the queue
            count++;// Increment the count of completed courses

            // For each neighbor (course that depends on the current course), decrement its indegree and add it to the queue if its indegree becomes 0
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If count equals numCourses, it means all courses can be completed
        return count == numCourses;
    }
}

TC - O(V + E) where V is the number of courses (vertices) and E is the number of prerequisites (edges) in the graph. Each course and its prerequisites are processed once during the topological sorting.
SC - O(V) for the indegree array and the queue used for Kahn's algorithm
