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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> L1 = new ArrayList<>();
        List<Integer> L2 = new ArrayList<>();

        dfs(root1,L1);
        dfs(root2,L2);

        return L1.equals(L2);
    }

    void dfs(TreeNode root, List<Integer> leaves)
    {
        if(root==null) return;

        if(root.left == null && root.right == null)
        {
            leaves.add(root.val);
            return;
        }

        dfs(root.left,leaves);
        dfs(root.right,leaves);
    }
}