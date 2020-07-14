package com.practice.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Solution:
 * Apply either dfs/bfs for the grid.
 * DFS:
 * loop through the grid start the dfs from the first '1' encountered
 * and increment the count for the number of dfs calls made from main method
 * dfs changes the value of the cell to '0' and intern calls dfs for the four neighbouring cells
 * if invalid cell and if the grid value is not '1' just return,
 * if valid cell they call dfs again for their neighbouring cells
 *
 * Time complexity : O(M×N) where M is the number of rows and N is the number of columns.
 * Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
 *
 * BFS:
 * loop through the grid start the bfs from the first '1' encountered
 * and increment the count for the number of bfs calls made from main method
 * Inside bfs:
 * change the value of the cell to '0'add the cell to the queue
 * loop through the queue and check if its neighbours are valid and the value ='1'
 * if yes change the value to '0' and add the cell to queue else skip
 * bfs stops when the queue is empty
 *
 * Time complexity : O(M×N) where M is the number of rows and N is the number of columns.
 * Space complexity :O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {

                    dfs(grid, i, j, m, n);
                    //bfs(grid,i,j,m,n);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i, j - 1, m, n);
    }

    public void bfs(char[][] grid, int i, int j, int m, int n) {
        grid[i][j] = '0';
        Queue<Integer> q = new ArrayDeque();
        int key = i * n + j;
        q.offer(key);

        while (!q.isEmpty()) {
            int cell = q.poll();
            int r = cell / n;
            int c = cell % n;

            if (r > 0 && grid[r - 1][c] == '1') {

                grid[r - 1][c] = '0';
                q.offer((r - 1) * n + c);

            }
            if (r < m - 1 && grid[r + 1][c] == '1') {

                grid[r + 1][c] = '0';
                q.offer((r + 1) * n + c);

            }
            if (c > 0 && grid[r][c - 1] == '1') {

                grid[r][c - 1] = '0';
                q.offer(r * n + c - 1);

            }
            if (c < n - 1 && grid[r][c + 1] == '1') {

                grid[r][c + 1] = '0';
                q.offer(r * n + c + 1);

            }

        }
    }
}
