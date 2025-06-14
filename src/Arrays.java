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

    /*
            You are given an m x n integer matrix 'matrix' with the following two properties:
            Each row is sorted in non-decreasing order.
            The first integer of each row is greater than the last integer of the previous row.
            Given an integer target, return true if target is in matrix or false otherwise.
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        if(m == 1 && n == 1){
            if(matrix[0][0] == target){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            int i = 0, j = 0;
            while(i < n && j < m){
                if(matrix[i][j] == target){
                    return true;
                }
                else{
                    if(target == matrix[i][m - 1]){
                        return true;
                    }
                    else{
                        if(target > matrix[i][m - 1]){
                            i++;
                            j = 0;
                        }
                        else{
                            j++;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
        You are given an array of integers and an integer k. Your task is to rotate the array to the right by k steps,
        where k is a non-negative integer. This means that after each step, the last element of the array is moved to
        the front.You are required to implement this operation in-place, meaning that the rotation should be done without
        using any additional arrays.
        Input Format:
            The first line contains the integer, n (the size of the array).
            The next line contains the integer k (the number of rotations).
            The next n lines contain the elements of the array.
        Constraints:
            1 ≤ n ≤ 10 The value of k can be greater than n. In such cases, perform k % n rotations.
        Output Format:
        Return a space-separated string of the rotated array elements.
     */

    private static void rotateArr(int[] nums, int k){
        if(k == 0 || k >= nums.length){
            return;
        }
        else{
            // if unable to understand the logic then dry-run it.
            int n = nums.length;
            int netK = k % n;
            int i = 0, j = n - netK;
            while(j < n){       // interchange the last netK integers with the first netK integers.
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
            }
            for(int x = 0; x < (n - netK)/2; x++){      // reverse the array portion(after the current netK numbers)
                int temp = nums[netK + x];
                nums[netK + x] = nums[n - 1 - x];
                nums[n - 1 - x] = temp;
            }
            for(int x = 0; x < netK / 2; x++){          // reverse the array portion(after the current netK numbers) of size of netK.
                int temp = nums[netK + x];
                nums[netK + x] = nums[netK + netK - 1 - x];
                nums[netK + netK - 1 - x] = temp;
            }
        }
    }

    /*
            You are given an array of integers, nums. Your task is to find 3 numbers whose product is the maximum and return the product.
            Input Format:
                The first line contains the integer, n (the size of the array). The next n lines contain the elements of the array.
            Constraints
                1 ≤ n ≤ 10⁴ −10⁹ ≤ nums[i] ≤ 10⁹
            Output Format:
                Return a single number, the maximum product.
     */

    private static void findMaxProduct(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int x = 0; x < n; x++){
            nums[x] = sc.nextInt();
        }
        int product = 1;
        if(n <= 3){
            int x = 0;
            while(x < n){
                product *= nums[x++];
            }
        }
        else{
            int x = 3;
            int n1 = nums[0];
            int n2 = nums[1];
            int n3 = nums[2];
            product = n1 * n2 * n3;
            while(x < n){
                int p1 = n2 * n3 * nums[x];
                int p2 = n1 * n3 * nums[x];
                int p3 = n1 * n2 * nums[x];
                int temp = Math.max(p1, Math.max(p2, p3));
                if(temp > product){
                    if(temp == p1){
                        n1 = n2;
                        n2 = n3;
                        n3 = nums[x];
                    }
                    else if(temp == p2){
                        n2 = n3;
                        n3 = nums[x];
                    }
                    else{
                        n3 = nums[x];
                    }
                    product = temp;
                }
                x++;
            }
        }
        System.out.println(product);
    }

    /*
            Given two integer arrays nums1 and nums2, return an array of their intersection.
            Each element in the result must be unique and you may return the result in any order.

            Example 1:
                Input: nums1 = [1,2,2,1], nums2 = [2,2]
                Output: [2]
            Example 2:
                Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
                Output: [9,4]
            Explanation: [4,9] is also accepted.
     */
    private boolean contains(int[] arr, int ele) {
        for (int e : arr) {
            if (e == ele) return true;
        }
        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        ArrayList<Integer> list = new ArrayList();
        Set<Integer> set = new HashSet();

        if (l1 <= l2) {
            for (int x = 0; x < l1; x++) {
                int ele = nums1[x];
                if (!set.contains(ele) && contains(nums2, ele)) {
                    set.add(ele);
                }
            }
        } else {
            for (int x = 0; x < l2; x++) {
                int ele = nums2[x];
                if (!set.contains(ele) && contains(nums1, ele)) {
                    set.add(ele);
                }
            }
        }

        int[] ans = new int[set.size()];
        int x = 0;
        for (int e : set) {
            ans[x++] = e;
        }
        return ans;
    }

    /*
            Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers,
            return the list of integers that are present in each array of nums sorted in ascending order.

            Example 1:
                Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
                Output: [3,4]
                Explanation:
                The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6]
                are 3 and 4, so we return [3,4].
            Example 2:
                Input: nums = [[1,2,3],[4,5,6]]
                Output: []
                Explanation:
                    There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
     */

    private void helper(int[][] nums, int idx, int target, List<Integer> list) {
        if(idx >= nums.length) {
            list.add(target);
        }
        else {
            for(int  y = 0; y < nums[idx].length; y++) {
                if(nums[idx][y] == target) {
                    helper(nums, idx + 1, target, list);
                }
            }
        }
    }

    public List<Integer> intersection(int[][] nums) {
        List<Integer> list = new ArrayList<>();
        for(int x = 0; x < nums[0].length; x++) {
            helper(nums, 1, nums[0][x], list);
        }
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {

    }
}
