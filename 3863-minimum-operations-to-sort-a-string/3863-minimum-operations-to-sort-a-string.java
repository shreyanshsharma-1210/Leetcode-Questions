class Solution {
    public int minOperations(String s) {
        int n = s.length();

        if (n <= 1 || isSorted(s)) {
            return 0;
        }

        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            minChar = (char) Math.min(minChar, s.charAt(i));
            maxChar = (char) Math.max(maxChar, s.charAt(i));
        }

        if (n == 2) {
            return -1;
        }

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        boolean minInPrefix = false;
        boolean maxInSuffix = false;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInPrefix = true;
            }
        }

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInSuffix = true;
            }
        }

        if (minInPrefix || maxInSuffix) {
            return 2;
        }

        return 3;
    }

    private boolean isSorted(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}