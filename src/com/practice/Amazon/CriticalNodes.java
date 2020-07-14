package com.practice.Amazon;

import java.util.*;

public class CriticalNodes {
    List<Integer> [] graph;
    int [] ids, low;
    boolean [] visited;
    int timer=0;
    Set<Integer> res;
    int childCount;
    public static void main(String[] args) {
        CriticalNodes c1 = new CriticalNodes();
        System.out.println(c1.criticalConnections(5,c1.connections1()));
        System.out.println("---------------------------------------------------------");
        CriticalNodes c2 = new CriticalNodes();
        System.out.println(c2.criticalConnections(9,c2.connections2()));
        System.out.println("---------------------------------------------------------");
        CriticalNodes c3 = new CriticalNodes();
        System.out.println(c3.criticalConnections(7,c3.connections3()));
    }

    public  List<Integer> criticalConnections(int n, List<List<Integer>> connections) {

        graph = new ArrayList[n];
        ids = new int[n];
        low = new int[n];
        visited =  new boolean[n];
        res = new HashSet<>();
        this.buildGraph(connections);

        for(int i=0;i<n;i++){
            if(!visited[i]){
                childCount=0;
                dfs(i,i,-1);
                /***
                 * for the root node child count is 2 or greater then it becomes an articulation point
                 */
                if(childCount>1){
                    res.add(i);
                }
            }
        }


        return new ArrayList<>(res);
    }

    public void dfs(int root,int node, int parent){
        visited[node]=true;
        ids[node] = low[node] = timer++;
        if(parent == root) childCount++;
        for(Integer nei: graph[node]){
            if(nei == parent) continue;
            if(!visited[nei]){
                dfs(root,nei,node);
                /***
                 * propagate the low link value within the cycle
                 * this is after the dfs call is finished and the call stack is going back
                 */
                low[node] = Math.min(low[node], low[nei]);
                /***
                 * < constitutes for articulation point found via bridge
                 * = constitutes for articulation point found via cycle
                 */
                if(parent!= -1 && ids[node]<= low[nei]){
                    res.add(node); // add current node
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

    public  List<List<Integer>> connections3(){
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(0,2));
        connections.add(Arrays.asList(1,3));
        connections.add(Arrays.asList(2,3));
        connections.add(Arrays.asList(2,5));
        connections.add(Arrays.asList(5,6));
        connections.add(Arrays.asList(3,4));
        return connections;
    }

}
