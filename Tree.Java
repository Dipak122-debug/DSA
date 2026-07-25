What is Binary Tree?
A binary tree is a data structure in which each node has at most two children, referred to as the left child and the right child. 
It is a hierarchical structure that consists of nodes connected by edges. The topmost node is called the root, and nodes with no children are called leaves. 
Binary trees are used in various applications such as searching, sorting, and representing hierarchical data.

//   public class TreeNode {
//       int val;
//       TreeNode left;
//       TreeNode right;
//       TreeNode(int x) { val = x; }
//   }


1. Lowest Common Ancestor of a Binary Tree
link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

solution
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root==null || root==p || root==q) return root; // If the current node is null or matches either p or q, return it as a potential ancestor.

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        // If both left and right are not null, it means p and q are found in different subtrees, so the current root is their lowest common ancestor.
        if(left!=null && right!=null) return root; 

        // If either left or right is not null, return the non-null value, which indicates that either p or q (or their ancestor) was found in that subtree.
        return left!=null ? left : right;

    }
}

TC - O(n) - We traverse the entire tree once, so the time complexity is O(n), where n is the number of nodes in the tree.
SC - O(h) - The space complexity is O(h), where h is the height of the tree, due to the recursive call stack. In the worst case (a skewed tree),
h can be equal to n, making the space complexity O(n). In a balanced tree, h is log(n), making the space complexity O(log n).

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2. Maximum Depth of Binary Tree
link - https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
solution
class Solution {
    public int maxDepth(TreeNode root) {

        if(root==null) return 0; // If the current node is null, return 0 as the depth.

        // Recursively calculate the maximum depth of the left and right subtrees.
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // The maximum depth of the current node is 1 (for the current node) plus the maximum depth of its subtrees.
        return Math.max(leftDepth,rightDepth)+1;

    }
}

TC - O(n) - We traverse the entire tree once, so the time complexity is O(n), where n is the number of nodes in the tree.
SC - O(h) - The space complexity is O(h), where h is the height of the tree, due to the recursive call stack. In the worst case (a skewed tree), 
h can be equal to n, making the space complexity O(n). In a balanced tree, h is log(n), making the space complexity O(log n).

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3. Righ Side View of Binary Tree
link - https://leetcode.com/problems/binary-tree-right-side-view/description/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> rightSideView = new ArrayList<>();
        if(root==null)return rightSideView;

         Queue<TreeNode> q = new LinkedList<>(); // Queue to perform level order traversal

        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();

            // Process all nodes at the current level
            while(size>0){ 
                TreeNode node = q.poll();
                if(size == 1) rightSideView.add(node.val);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
                size--;
            }
        }

        return rightSideView;
        
    }
}

Explanation of the solution:
1. We use a queue to perform a level order traversal of the binary tree.
2. For each level, we process all nodes and keep track of the last node at that level (the rightmost node).
3. We add the value of the rightmost node to the result list.

TC - O(n) - We traverse the entire tree once, so the time complexity is O(n), where n is the number of nodes in the tree.
SC - O(w) - The space complexity is O(w), where w is the maximum width of the tree, which is the maximum number of nodes at any level. 
In the worst case, this can be O(n) for a complete binary tree.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

4. Path Sum
link - https://leetcode.com/problems/path-sum/

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {

        return helper(root,targetSum,0);
    }

    public boolean helper(TreeNode root, int targetSum, int sum){

        if(root==null) return false;

        sum+=root.val;

        if(root.left==null && root.right==null){
            if(sum==targetSum) return true;
        }

        return helper(root.left,targetSum,sum) || helper(root.right,targetSum,sum);

    }
}

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

5. Path SUM 2
link - https://leetcode.com/problems/path-sum-ii/description/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> slate = new ArrayList<>();
        int sum = 0;
        helper(root,targetSum,sum,slate,res);
        return res;
        
    }

    public void helper(TreeNode root, int targetSum, int sum, List<Integer> slate, List<List<Integer>> res){

        if(root==null) return;

        sum=sum+root.val;

        slate.add(root.val);

        if(root.left==null && root.right==null){
            if(sum==targetSum){
                res.add(new ArrayList<>(slate));
            }
        }

        if(root.left!=null) helper(root.left,targetSum,sum,slate,res);
        if(root.right!=null) helper(root.right,targetSum,sum,slate,res);
        
        slate.remove(slate.size()-1); 

    }


}


