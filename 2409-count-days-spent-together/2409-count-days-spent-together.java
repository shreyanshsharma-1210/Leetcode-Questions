class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int aa = 0;
        int la = 0;
        int ba = 0;
        int lb = 0;

        int m_aa = Integer.parseInt(arriveAlice.substring(0,2));
        aa = Integer.parseInt(arriveAlice.substring(3));
        for(int i = 0 ; i<m_aa-1; i++){
            aa += month[i];
        }

        int m_la = Integer.parseInt(leaveAlice.substring(0,2));
        la = Integer.parseInt(leaveAlice.substring(3));
        for(int i = 0 ; i<m_la-1; i++){
            la += month[i];
        }

        int m_ba = Integer.parseInt(arriveBob.substring(0,2));
        ba = Integer.parseInt(arriveBob.substring(3));
        for(int i = 0 ; i<m_ba-1; i++){
            ba += month[i];
        }

        int m_lb = Integer.parseInt(leaveBob.substring(0,2));
        lb = Integer.parseInt(leaveBob.substring(3));
        for(int i = 0 ; i<m_lb-1; i++){
            lb += month[i];
        }

        return Math.max(0, Math.min(lb ,la) - Math.max(aa, ba)+1);
    }
}