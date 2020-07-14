package com.practice.Amazon;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TreasureIsland {
    public static int[][] directions={{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) {
        char [][] island = {
                {'O','D','O','O'},
                {'D','D','D','O'},
                {'O','O','O','O'},
                {'X','D','D','O'}};
        System.out.println(shortestRouteToTreasure(island));

    }

    public static int shortestRouteToTreasure(char [][] island){
        int count =0;
        int m = island.length;
        if(m==0)
            return count;
        int n =island[0].length;
        
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        Pair<Integer,Integer> p = new Pair(0,0);
        queue.offer(p);
        Set<Pair> visited = new HashSet();
        visited.add(p);

        while(!queue.isEmpty()){


            for(int k=queue.size();k>0;k--){
                p = queue.poll();
                int i =p.getKey();
                int j = p.getValue();
                for(int[] d:directions){
                    int r = i+d[0];
                    int c = j+d[1];

                    if(r<0 || r>= m || c<0 || c>=n || island[r][c] =='D' || visited.contains(new Pair(r,c))) continue;
                    if(island[r][c]=='X') return count+1;
                    queue.offer(new Pair(r,c));
                    visited.add(new Pair(r,c));
                }
            }
            count++;
        }

        return 0;
    }

}
