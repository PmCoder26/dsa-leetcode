


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

    public static void main(String[] args) {

    }

}
