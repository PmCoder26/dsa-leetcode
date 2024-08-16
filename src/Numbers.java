

public class Numbers {

    /*
            Given two non-negative integers low and high.
            Return the count of odd numbers between low and high (inclusive).
     */

    public int countOdds(int low, int high) {
        if(low == high){
            if(low % 2 == 0){
                return 0;
            }
            else{
                return 1;
            }
        }
        else{
            int count = (high - low) / 2;
            if(low % 2 != 0 || high % 2 != 0){
                count++;
            }
            return count;
        }
    }

    public static void main(String[] args) {

    }
}
