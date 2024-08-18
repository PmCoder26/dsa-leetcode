import java.util.*;

public class Arrays {

    /*
            You are given a large integer represented as an integer array digits, where
            each digits[i] is the ith digit of the integer. The digits are ordered from
            most significant to the least significant in left-to-right order. The large
            integer does not contain any leading 0's. Increment the large integer by one
            and return the resulting array of digits.
     */

    public int[] plusOne(int[] digits) {
        if (digits.length == 1 && digits[0] < 9) {
            digits[0] = digits[0] + 1;
            return digits;
        }
        if (digits.length == 1 && digits[0] == 9) {
            return new int[]{1, 0};
        } else {
            int carry = 0;
            int tempSum = 0;
            int ele = -1;
            boolean start = true;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (start) {
                    if (digits[i] + 1 != 10) {
                        digits[i] = digits[i] + 1;
                        break;
                    } else {
                        carry = 1;
                    }
                    start = !start;
                }
                tempSum = digits[i] + carry;
                if (tempSum == 10) {
                    ele = digits[i];
                    digits[i] = tempSum % 10;
                    carry = tempSum / 10;
                } else {
                    digits[i] = tempSum;
                    carry = 0;
                    break;
                }
            }
            if (carry != 0) {
                ele += carry;
                if (ele != 10) {
                    digits[0] = carry;
                    return digits;
                } else {
                    int[] temp = new int[digits.length + 1];
                    for (int x = 0; x < digits.length; x++) {
                        temp[x + 1] = digits[x];
                    }
                    temp[0] = carry;
                    return temp;
                }
            }
            return digits;
        }
    }

    /*
            Given an integer array nums, move all 0's to the end of it while
            maintaining the relative order of the non-zero elements. Note that
            you must do this in-place without making a copy of the array.
     */

    public void moveZeroes(int[] nums) {
        for(int x=0; x<nums.length-1; x++){
            for(int y=0; y<nums.length-1-x; y++){
                if(nums[y]==0 && nums[y+1]!=0){
                    int temp=nums[y];
                    nums[y]=nums[y+1];
                    nums[y+1]=temp;
                }
            }
        }
    }

    /*
            An array is monotonic if it is either monotone increasing or monotone decreasing.
            An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
            An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
            Given an integer array nums, return true if the given array is monotonic,
            or false otherwise.
     */

    public boolean isMonotonic(int[] nums) {
        if(nums.length == 1){
            return true;
        }
        else{
            boolean isIncrease = false;
            boolean notStarted = true;
            boolean result = true;
            for(int x = 0; x < nums.length - 1; x++){
                if(x == 0 || notStarted){
                    if(nums[x] < nums[x + 1]){
                        isIncrease = true;
                        notStarted = false;
                        continue;
                    }
                    else if(nums[x] > nums[x + 1]){
                        notStarted = false;
                        continue;
                    }
                }
                else{
                    if(isIncrease){
                        if(nums[x] > nums[x + 1]){
                            result = false;
                            break;
                        }
                    }
                    else{
                        if(nums[x] < nums[x + 1]){
                            result = false;
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }

    /*
            Given an m x n integer matrix 'matrix', if an element is 0,
            set its entire row and column to 0's. You must do it in place.
     */

    private static class Info{          // part 1.
        int i;
        int j;
        public Info(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public void setZeroes(int[][] matrix) {     // part 2.
        int m = matrix[0].length;
        int n = matrix.length;
        if(m == 1 && n == 1){
            return;
        }
        else{
            ArrayList<Info> grids = new ArrayList<>();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(matrix[i][j] == 0){
                        grids.add(new Info(i, j));
                    }
                }
            }
            while(!grids.isEmpty()){
                Info temp = grids.removeFirst();
                // zero the row.
                for(int j = 0; j < m; j++){
                    matrix[temp.i][j] = 0;
                }
                // zero the column.
                for(int i = 0; i < n; i++){
                    matrix[i][temp.j] = 0;
                }
            }
        }
    }


    public static void main(String[] args) {

    }
}
