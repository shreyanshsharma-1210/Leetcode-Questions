import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        for (int k = 1; k < n; k *= 2) {
            final int[] currentRank = rank;
            final int len = k;

            Integer[] order = new Integer[n];

            for (int i = 0; i < n; i++)
                order[i] = i;

            Arrays.sort(order, (a, b) -> {
                if (currentRank[a] != currentRank[b])
                    return Integer.compare(currentRank[a], currentRank[b]);

                int ra = a + len < n ? currentRank[a + len] : -1;
                int rb = b + len < n ? currentRank[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++)
                sa[i] = order[i];

            temp[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different = rank[a] != rank[b];

                int ra = a + len < n ? rank[a + len] : -1;
                int rb = b + len < n ? rank[b + len] : -1;

                if (ra != rb)
                    different = true;

                temp[b] = temp[a] + (different ? 1 : 0);
            }

            int[] swap = rank;
            rank = temp;
            temp = swap;

            if (rank[sa[n - 1]] == n - 1)
                break;

            if (k > n / 2)
                break;
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++)
            pos[sa[i]] = i;

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0)
                h--;
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            int maxLcp = Math.max(
                lcp[i],
                i + 1 < n ? lcp[i + 1] : 0
            );

            if (sa[i] + maxLcp + 1 <= n)
                ans = Math.min(ans, maxLcp + 1);
        }

        return ans;
    }
}