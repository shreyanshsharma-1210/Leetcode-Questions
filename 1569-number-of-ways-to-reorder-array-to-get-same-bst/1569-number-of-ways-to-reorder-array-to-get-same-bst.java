class Solution {
    static final long MOD = 1_000_000_007L;
    long[][] nCr = new long[1001][1001];

    public int numOfWays(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            nCr[i][0] = nCr[i][i] = 1;
            for (int j = 1; j < i; j++) {
                nCr[i][j] = (nCr[i - 1][j - 1] + nCr[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return (int) ((solve(list) - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = solve(left);
        long rightWays = solve(right);

        long combinations = nCr[left.size() + right.size()][left.size()];

        return (((combinations * leftWays) % MOD) * rightWays) % MOD;
    }
}