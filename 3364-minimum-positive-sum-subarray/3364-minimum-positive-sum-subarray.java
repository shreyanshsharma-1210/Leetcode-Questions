import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = -1;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        for (int len = l; len <= r; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len;
                int currentSum = prefix[j] - prefix[i];

                if (currentSum > 0) {
                    if (minSum == -1 || currentSum < minSum) {
                        minSum = currentSum;
                    }
                }
            }
        }

        return minSum;
    }
}