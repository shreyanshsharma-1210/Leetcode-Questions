
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = digitSum(nums[i]);
        }

        java.util.Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || map.get(arr[i][0]) == i) {
                continue;
            }

            int j = i;
            int size = 0;

            while (!visited[j]) {
                visited[j] = true;
                j = map.get(arr[j][0]);
                size++;
            }

            swaps += size - 1;
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
