
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Find maximum factor
        int maxFactor = 0;

        for(int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
        }

        // Frequency of each factor
        int[] freq = new int[maxFactor + 1];

        for(int[] item : items) {
            freq[item[0]]++;
        }

        // Count all items whose factors are multiples
        int[] gain = new int[maxFactor + 1];

        for(int i = 1; i <= maxFactor; i++) {

            for(int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }

            gain[i]--;
        }

        // Sort by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        int minPrice = items[0][1];

        long remaining = budget;
        long ans = 0;

        // Buy boosted copies greedily
        for(int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if(price > 2L * minPrice) {
                break;
            }

            long available = gain[factor];

            long take = Math.min(
                available,
                remaining / price
            );

            ans += 2 * take;
            remaining -= take * price;
        }

        // Buy normal copies using remaining budget
        ans += remaining / minPrice;

        return (int) ans;
    }
}
