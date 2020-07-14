package com.practice.Amazon;

import java.util.ArrayDeque;
import java.util.Queue;

public class ZombieMatrix {
    public static final int ZOMBIE=1;
    public static final int HUMAN=0;
    public static int[][] directions={{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) {
//        int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
//        int[][] grid = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
//        int[][] grid = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
        int[][] grid = {{}};
        System.out.println(minDays(grid));
    }
    private static int minDays(int[][] grid) {

        int ROWS = grid.length;
        if(ROWS ==0)
            return -1;
        int COLS = grid[0].length;
        int humanCount=0;
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                if(grid[i][j]==ZOMBIE){
                    q.offer(i*COLS+j);
                }else{
                    humanCount++;
                }
            }
        }
        if(humanCount==0) return 0;
        int count=0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size>0){
                size--;
                int cell = q.poll();
                int row = cell/COLS;
                int col = cell%COLS;
                for(int [] d:directions){
                    int r = row+d[0];
                    int c = col+d[1];
                    if(r< 0 || r>=ROWS || c<0 || c>=COLS || grid[r][c]!=HUMAN) continue;
                    grid[r][c]=ZOMBIE;
                    humanCount--;
                    q.offer(r*COLS+c);
                }
            }
            count++;
        }
        return humanCount==0?count-1:-1;
    }
}
