
class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j][0]: ways entering (i,j) while moving right
        // dp[i][j][1]: ways entering (i,j) while moving down
        int[][][] dp = new int[m + 1][n + 1][2];

        // Starting position
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int fromRight = dp[i][j][0];
                int fromDown = dp[i][j][1];

                if (grid[i][j] == 0) {
                    // Empty cell: move right
                    dp[i][j + 1][0] =
                        (int) ((dp[i][j + 1][0] + (long) fromRight
                              + fromDown) % MOD);

                    // Empty cell: move down
                    dp[i + 1][j][1] =
                        (int) ((dp[i + 1][j][1] + (long) fromRight
                              + fromDown) % MOD);

                } else {
                    // Mirror: moving right redirects down
                    dp[i + 1][j][1] =
                        (int) ((dp[i + 1][j][1] + (long) fromRight)
                              % MOD);

                    // Mirror: moving down redirects right
                    dp[i][j + 1][0] =
                        (int) ((dp[i][j + 1][0] + (long) fromDown)
                              % MOD);
                }
            }
        }

        return (int) (((long) dp[m - 1][n - 1][0]
                     + dp[m - 1][n - 1][1]) % MOD);
    }
}
