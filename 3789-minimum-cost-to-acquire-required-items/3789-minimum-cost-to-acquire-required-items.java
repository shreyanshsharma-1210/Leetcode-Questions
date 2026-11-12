class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        if (c1 + c2 <= cb) {
            return n1 * c1 + n2 * c2;
        }

        long common = Math.min(n1, n2);
        long cost = common * cb;
        long rem1 = n1 - common;
        long rem2 = n2 - common;

        if (rem1 > 0) {
            cost += rem1 * Math.min(c1, cb);
        }

        if (rem2 > 0) {
            cost += rem2 * Math.min(c2, cb);
        }

        return cost;
    }
}