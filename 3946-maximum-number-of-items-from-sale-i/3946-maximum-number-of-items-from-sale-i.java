class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[items.length];
        int minPrice = Integer.MAX_VALUE;
        
        for(int i =0 ;i<items.length;i++)
        {
            minPrice = Math.min(minPrice, items[i][1]);
            
            for(int j =0 ;j<items.length;j++)
            {
                if(i == j) continue;
                else 
                {
                    if(items[j][0] % items[i][0] == 0)
                        {
                            free[i]++;
                        }
                }
            }
        }
        
        
        int[][] t = new int[n + 1][budget + 1];

        for (int b = 0; b <= budget; b++)
            t[0][b] = b / minPrice;

        for (int i = 1; i <= n; i++) {
            for (int b = 0; b <= budget; b++) {
                int price = items[i - 1][1];
                int take = 0;

                if (price <= b)
                    take = 1 + free[i - 1] + t[i - 1][b - price];

                int skip = t[i - 1][b];

                t[i][b] = Math.max(take, skip);
            }
        }

        return t[n][budget];
    }
}