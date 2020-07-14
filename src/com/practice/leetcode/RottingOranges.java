package com.practice.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 */
public class RottingOranges {
    public static int EMPTY =0;
    public static int FRESH =1;
    public static int ROTTEN =2;
    public static int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}};
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        if(m==0)
            return 0;
        int n = grid[0].length;
        int freshCount=0;
        Queue<int[]> q = new ArrayDeque();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==ROTTEN){
                    q.offer(new int[]{i,j});
                }else if(grid[i][j]==FRESH){
                    freshCount++;
                }
            }
        }
        if(freshCount==0) return 0;
        int count=0;
        while(!q.isEmpty()){

            for(int i = q.size();i>0;i--){
                int [] cell = q.poll();
                for(int [] d :DIRECTIONS){
                    int r = cell[0]+d[0];
                    int c = cell[1]+d[1];
                    if(r<0 || r>=m || c<0 || c>=n || grid[r][c]!=FRESH) continue;
                    grid[r][c]=ROTTEN;
                    q.offer(new int[]{r,c});
                    freshCount--;
                }
            }
            count++;
        }
        return freshCount==0?count-1:-1;
    }
}
