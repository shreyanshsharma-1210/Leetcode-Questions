
class Solution {
    public int[] plusOne(int[] digits) {
       int n = digits.length-1;
       for(int i = n ; i>=0;i--){
        if(digits[i] < 9){
            digits[i] = digits[i]+1;
            return digits;
        }

            digits[i] = 0;
       }
       int[] result = new int[n+2];
       result[0] = 1;
       return result;
    }
}