
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        List<Integer>[] newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int value = stack[--top];

            if (value >= 0) {
                int u = value;
                int c = s.charAt(u) - 'a';

                previous[u] = last[c];

                if (last[c] != -1) {
                    newParent[u] = last[c];
                }

                last[c] = u;

                stack[top++] = ~u;

                for (int v : tree[u]) {
                    stack[top++] = v;
                }
            } else {
                int u = ~value;
                last[s.charAt(u) - 'a'] = previous[u];
            }
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] order = new int[n];
        top = 0;

        stack[top++] = 0;
        int count = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : newTree[u]) {
                stack[top++] = v;
            }
        }

        for (int i = count - 1; i > 0; i--) {
            int u = order[i];
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
}
