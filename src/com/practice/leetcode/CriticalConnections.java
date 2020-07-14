package com.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b.
 * Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * Return all critical connections in the network in any order.
 *
 * Example 1:
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 * Time complexity: O(V+E)
 * Space Complexity: O(V+E)
 */
public class CriticalConnections {
    List<Integer> [] graph;
    int [] ids, low;
    boolean [] visited;
    int timer=0;
    List<List<Integer>> res;
    public static void main(String[] args) {
        CriticalConnections c1 = new CriticalConnections();
        List<List<Integer>> connections1 = c1.connections1();
        System.out.println(c1.criticalConnections(5,connections1));
        System.out.println("---------------------------------------------------------");
        CriticalConnections c2 = new CriticalConnections();
        List<List<Integer>> connections2 = c2.connections2();
        System.out.println(c2.criticalConnections(9,connections2));

    }

    public  List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        graph = new ArrayList[n];
        ids = new int[n];
        low = new int[n];
        visited =  new boolean[n];
        res = new ArrayList<>();
        this.buildGraph(connections);

        for(int i=0;i<n;i++){
            if(!visited[i])
                dfs(i,-1);
        }


        return res;
    }

    public void dfs(int node, int parent){
        visited[node]=true;
        ids[node] = low[node] = timer++;

        for(Integer nei: graph[node]){
            if(nei == parent) continue;
            if(!visited[nei]){
                dfs(nei,node);
                /***
                 * propagate the low link value within the cycle
                 * this is after the dfs call is finished and the call stack is going back
                 */
                low[node] = Math.min(low[node], low[nei]);
                if(ids[node]< low[nei]){
                    res.add(Arrays.asList(node,nei));
                }
            }else{
                /***
                 * node visits already visited node
                 * i.e we have found a back edge
                 * update the nodes low with the visited nodes id
                 */
                low[node] =  Math.min(low[node],ids[nei]);
            }

        }
    }
    public void buildGraph(List<List<Integer>> connections){

        for(int i=0;i<graph.length;i++){
            graph[i]= new ArrayList<>();
        }
        for(List<Integer> conn : connections){
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        for(int i=0;i<graph.length;i++){
            System.out.println(graph[i]);
        }
    }

    public  List<List<Integer>> connections1(){
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(0,2));
        connections.add(Arrays.asList(2,3));
        connections.add(Arrays.asList(0,3));
        connections.add(Arrays.asList(3,4));
        return connections;
    }

    public  List<List<Integer>> connections2(){
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(0,2));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,3));
        connections.add(Arrays.asList(2,5));
        connections.add(Arrays.asList(3,4));
        connections.add(Arrays.asList(5,6));
        connections.add(Arrays.asList(5,8));
        connections.add(Arrays.asList(6,7));
        connections.add(Arrays.asList(7,8));
        return connections;
    }

}
