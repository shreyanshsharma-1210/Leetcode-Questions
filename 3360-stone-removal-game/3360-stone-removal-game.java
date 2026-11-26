class Solution {
    public boolean canAliceWin(int n) {
        if(n<10)
        {
            return false;
        }
        int count = 10;
        
        while(count <= n){
            System.out.println("n" + n);
               System.out.println(count);
            if(n-count < (count-1)){
               if((count-1) %2 == 0)
               {
                    return false;
               }
               else 
               {
                return true;
               }
               
                
                
            }
            else{
                    n -= count;
                    count -= 1;                    
                }
        }
        
        return true;
    }
}