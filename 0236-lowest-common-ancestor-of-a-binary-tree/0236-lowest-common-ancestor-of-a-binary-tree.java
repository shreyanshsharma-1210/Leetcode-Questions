/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> ls1 = new ArrayList<>();
        List<TreeNode> ls2 = new ArrayList<>();

        findPath(root,p,ls1);
        findPath(root,q,ls2);

        TreeNode lca = null;

        for(int i = 0;i<ls1.size() && i<ls2.size();i++)
        {
            if(ls1.get(i) == ls2.get(i))
            {
                lca = ls1.get(i);
            }
            else
            {
                break;
            }
        }

        return lca;
        
    }

    public boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null)
        {
            return false;
        }

        path.add(root);

        if (root == target) 
        {
            return true;
        }

        if (findPath(root.left, target, path) || findPath(root.right, target, path)) 
        {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
}