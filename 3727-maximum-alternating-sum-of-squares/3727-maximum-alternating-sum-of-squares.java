class Solution {
    public long maxAlternatingSum(int[] nums) {
        for(int i = 0;i<nums.length;i++)
        {
            nums[i] = Math.abs(nums[i]);
        }

        Arrays.sort(nums);

        int p = 0;
        int q = nums.length-1;

        long sum = 0;

        while(p<q)
        {
            sum += nums[q]*nums[q] - nums[p]*nums[p];
            p++;
            q--;
        }

        if(p==q)
        {
            sum += nums[p]*nums[p];
        }

        return sum;
    }
}