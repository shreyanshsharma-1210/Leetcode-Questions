
import java.util.*;

class Solution {

    List<Integer>[] adj;
    int[][] up;
    int[] depth, tin, tout, baseMask;
    int timer, LOG;
    Fenwick bit;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int mask) {
            while (i <= n) {
                tree[i] ^= mask;
                i += i & -i;
            }
        }

        int query(int i) {
            int ans = 0;
            while (i > 0) {
                ans ^= tree[i];
                i -= i & -i;
            }
            return ans;
        }

        void rangeUpdate(int l, int r, int mask) {
            update(l + 1, mask);
            update(r + 2, mask);
        }

        int pointQuery(int index) {
            return query(index + 1);
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        baseMask = new int[n];

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        int[] subtreeSize = new int[n];

        Arrays.fill(parent, -1);

        int top = 0, count = 0;
        stack[top++] = 0;
        parent[0] = 0;
        baseMask[0] = 1 << (s.charAt(0) - 'a');

        while (top > 0) {
            int u = stack[--top];

            tin[u] = count;
            order[count++] = u;

            up[0][u] = parent[u];

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                baseMask[v] = baseMask[u]
                        ^ (1 << (s.charAt(v) - 'a'));

                stack[top++] = v;
            }
        }

        Arrays.fill(subtreeSize, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            subtreeSize[parent[u]] += subtreeSize[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + subtreeSize[u] - 1;
        }

        bit = new Fenwick(n);

        char[] chars = s.toCharArray();
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                if (chars[u] == c) continue;

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                bit.rangeUpdate(tin[u], tout[u], delta);
                chars[u] = c;

            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int lca = getLCA(u, v);

                int maskU = baseMask[u]
                          ^ bit.pointQuery(tin[u]);

                int maskV = baseMask[v]
                          ^ bit.pointQuery(tin[v]);

                int pathMask = maskU ^ maskV
                             ^ (1 << (chars[lca] - 'a'));

                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
