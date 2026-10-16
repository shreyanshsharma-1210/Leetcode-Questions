class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, sq, lz;

    void apply(int o, int len, long v) {
        sq[o] = (sq[o] + (2 * v % MOD) * sum[o] + (v * v % MOD) * len) % MOD;
        sum[o] = (sum[o] + v * len) % MOD;
        lz[o] += v;
    }

    void push(int o, int l, int r) {
        if (lz[o] != 0) {
            int mid = (l + r) >> 1;
            apply(o << 1, mid - l + 1, lz[o]);
            apply(o << 1 | 1, r - mid, lz[o]);
            lz[o] = 0;
        }
    }

    void update(int o, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(o, r - l + 1, 1);
            return;
        }
        push(o, l, r);
        int mid = (l + r) >> 1;
        if (ql <= mid) update(o << 1, l, mid, ql, qr);
        if (qr > mid) update(o << 1 | 1, mid + 1, r, ql, qr);
        sum[o] = (sum[o << 1] + sum[o << 1 | 1]) % MOD;
        sq[o] = (sq[o << 1] + sq[o << 1 | 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lz = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(1, 0, n - 1, last[x] + 1, i);
            ans = (ans + sq[1]) % MOD;
            last[x] = i;
        }
        return (int) ans;
    }
}