/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = 0;
    public int longestZigZag(TreeNode root) {
        dfs(root,0,true);
        dfs(root,0,false);
        return max;
    }

    void dfs(TreeNode root,int count, boolean goLeft)
    {
        if(root == null) return;

        max = Math.max(max,count);

        if(goLeft)
        {
            dfs(root.left,count+1,false);
            dfs(root.right,1,true);
        }
        else
        {
            dfs(root.right,count+1,true);
            dfs(root.left,1,false);
        }
    }
}