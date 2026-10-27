class Solution {
    public String[] createGrid(int m, int n) {
        char[][] arr = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '#';
            }
        }

        if (m == 1) {
            for (int j = 0; j < n; j++) {
                arr[0][j] = '.';
            }
        } else if (n == 1) {
            for (int i = 0; i < m; i++) {
                arr[i][0] = '.';
            }
        } else {
            for (int j = 0; j < n - 1; j++) {
                arr[0][j] = '.';
            }

            arr[1][n - 2] = '.';
            arr[1][n - 1] = '.';

            for (int i = 2; i < m; i++) {
                arr[i][n - 1] = '.';
            }
        }

        String[] str = new String[m];

        for (int i = 0; i < m; i++) {
            str[i] = new String(arr[i]);
        }

        return str;
    }
}