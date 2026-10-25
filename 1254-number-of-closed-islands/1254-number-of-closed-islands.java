class Solution {
    int m, n;
    boolean[][] seen;
    boolean closed;

    void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            closed = false;
            return;
        }

        if (grid[r][c] == 1 || seen[r][c]) {
            return;
        }

        seen[r][c] = true;

        if (r == 0 || c == 0 || r == m - 1 || c == n - 1) {
            closed = false;
        }

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        seen = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !seen[i][j]) {
                    closed = true;
                    dfs(grid, i, j);

                    if (closed) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}