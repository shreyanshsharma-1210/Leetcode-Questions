class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int min = n;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int r = 0; r < d; r++) {
                        int l = i + r;
                        int h = j - d + 1 + r;

                        while (l < h) {
                            if (s.charAt(l) != s.charAt(h)) {
                                changes++;
                            }
                            l += d;
                            h -= d;
                        }
                    }

                    min = Math.min(min, changes);
                }

                cost[i][j] = min;
            }
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], n);
        }

        dp[0][0] = 0;

        for (int i = 2; i <= n; i++) {
            for (int p = 1; p <= k; p++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    dp[i][p] = Math.min(
                        dp[i][p],
                        dp[j][p - 1] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}