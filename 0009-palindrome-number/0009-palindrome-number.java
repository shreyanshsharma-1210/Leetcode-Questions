class Solution {
    public boolean isPalindrome(int x) {
        int op =0;
        int temp = x;
        if(x<0) return false;

        else
        {
            
            while(x > 0)
            {
                int dig = x%10;
                x = x/10;
                op = op*10 +dig;
            } 
        }

        if(op == temp) return true;
        else return false;
    }
}