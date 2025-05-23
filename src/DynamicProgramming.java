import java.util.List;

public class DynamicProgramming {

    /*
            You are given an m x n integer array grid. There is a robot initially located at the top-left corner
            (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot
            can only move either down or right at any point in time.
            An obstacle and space are marked as 1 or 0 respectively in grid.
            A path that the robot takes cannot include any square that is an obstacle.
            Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

            Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
            Output: 2
            Explanation: There is one obstacle in the middle of the 3x3 grid above.
                There are two ways to reach the bottom-right corner:
                1. Right -> Right -> Down -> Down
                2. Down -> Down -> Right -> Right
     */

    private int helper(int[][] nums, int i, int j, int[][] tabs){
        if(i == nums.length - 1 && j == nums[0].length - 1 && nums[i][j] == 0){
            return 1;
        }
        if(nums[i][j] == 1){
            return 0;
        }
        if(tabs[i][j] != -1){
            return tabs[i][j];
        }
        if(j == nums[0].length - 1){
            return tabs[i][j] = helper(nums, i + 1, j, tabs);
        }
        if(i == nums.length - 1){
            return tabs[i][j] = helper(nums, i, j + 1, tabs);
        }
        else{
            int right = helper(nums, i, j + 1, tabs);
            int down = helper(nums, i + 1, j, tabs);
            return tabs[i][j] = right + down;
        }
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0].length == 1 && obstacleGrid.length == 1 && obstacleGrid[0][0] == 0){
            return 1;
        }
        else{
            int[][] tabs = new int[obstacleGrid.length][obstacleGrid[0].length];
            for(int x = 0; x < tabs.length; x++){
                for(int y = 0; y < tabs[0].length; y++){
                    tabs[x][y] = -1;
                }
            }
            return helper(obstacleGrid, 0, 0, tabs);
        }
    }

    /*
            You are given an integer array nums. You are initially positioned at the array's first index, and each
            element in the array represents your maximum jump length at that position.
            Return true if you can reach the last index, or false otherwise.

            Example 1:
                Input: nums = [2,3,1,1,4]
                Output: true
                Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

            Example 2:
                Input: nums = [3,2,1,0,4]
                Output: false
                Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
                         impossible to reach the last index.
     */

    private boolean helper(int[] nums, int idx, int[] tabs){
        if(idx >= nums.length - 1){
            tabs[tabs.length - 1] = 1;
            return true;
        }
        if(nums[idx] == 0){
            tabs[idx] = -1;
            return false;
        }
        if(tabs[idx] == -1){
            return false;
        }
        else{
            for(int x = nums[idx]; x > 0; x--){
                if(helper(nums, idx + x, tabs)){
                    tabs[idx] = 1;
                    return true;
                }
                else if(x == 1){
                    tabs[idx] = -1;
                }
            }
            return false;
        }
    }

    public boolean canJump(int[] nums) {
        if(nums.length == 1 && nums[0] != 0){
            return true;
        }
        else{
            int[] tabs = new int[nums.length];
            return helper(nums, 0, tabs);
        }
    }

    /*
            You are a professional robber planning to rob houses along a street. Each house has a certain amount of
            money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have
            security systems connected and it will automatically contact the police if two adjacent houses were broken
            into on the same night.
            Given an integer array nums representing the amount of money of each house, return the maximum amount of
            money you can rob tonight without alerting the police.

            Example 1:
                Input: nums = [1,2,3,1]
                Output: 4
                Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                Total amount you can rob = 1 + 3 = 4.
            Example 2:
                Input: nums = [2,7,9,3,1]
                Output: 12
                Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
                Total amount you can rob = 2 + 9 + 1 = 12.
     */

    private int helper(int[] nums, int[] tabs, int idx){
        if(idx == nums.length - 1){
            return tabs[idx] = nums[idx];
        }
        if(idx >= nums.length){
            return 0;
        }
        if(tabs[idx] != -1){
            return tabs[idx];
        }
        else{
            int maxLoot = Integer.MIN_VALUE;
            for(int x = idx; x < nums.length; x++){
                maxLoot = Math.max(maxLoot, nums[x] + helper(nums, tabs, x + 2));
            }
            return tabs[idx] = maxLoot;
        }
    }

    public int rob(int[] nums) {
        int[] tabs = new int[nums.length];
        for(int x = 0; x < tabs.length; x++){
            tabs[x] = -1;
        }
        return helper(nums, tabs, 0);
    }

    /*
            Given a triangle array, return the minimum path sum from top to bottom. For each step, you may move to an
            adjacent number of the row below. More formally, if you are on index i on the current row, you may move to
            either index i or index i + 1 on the next row.

            Example 1:
                Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
                Output: 11
                Explanation: The triangle looks like:
                   2
                  3 4
                 6 5 7
                4 1 8 3
                The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
            Example 2:
                Input: triangle = [[-10]]
                Output: -10
     */

    private int helper(List<List<Integer>> triangle, int[][] tabs, int x, int y){
        if(x == triangle.size() - 1 && y == triangle.get(x).size() - 1){
            return tabs[x][y] = triangle.get(x).get(y);
        }
        if(x >= triangle.size() || y >= triangle.get(x).size()){
            return 0;
        }
        if(tabs[x][y] != -1){
            return tabs[x][y];
        }
        else{
            int minSum = Integer.MAX_VALUE;
            for(int i = y; i < y + 2 && i < triangle.get(x).size(); i++){
                minSum = Math.min(minSum, triangle.get(x).get(i) + helper(triangle, tabs, x + 1, i));
            }
            return tabs[x][y] = minSum;
        }
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        else{
            int[][] tabs = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
            for(int x = 0; x < triangle.size(); x++){
                int s = triangle.get(x).size();
                for(int y = 0; y < s; y++){
                    tabs[x][y] = -1;
                }
            }
            return helper(triangle, tabs, 0, 0);
        }
    }

    public static void main(String[] args) {

    }

}
