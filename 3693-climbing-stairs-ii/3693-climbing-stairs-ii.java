class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        long[] dp = new long[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // costs is 1-indexed in the problem description, so costs[i - 1] corresponds to step i
            long stepCost = costs[i - 1];

            // Option 1: Jump from i - 1 (jump length 1, (1)^2 = 1)
            long minPrev = dp[i - 1] + 1L;

            // Option 2: Jump from i - 2 (jump length 2, (2)^2 = 4)
            if (i >= 2) {
                minPrev = Math.min(minPrev, dp[i - 2] + 4L);
            }

            // Option 3: Jump from i - 3 (jump length 3, (3)^2 = 9)
            if (i >= 3) {
                minPrev = Math.min(minPrev, dp[i - 3] + 9L);
            }

            dp[i] = stepCost + minPrev;
        }

        return (int) dp[n];
    }
}