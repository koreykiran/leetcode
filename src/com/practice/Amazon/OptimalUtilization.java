package com.practice.Amazon;

import java.util.*;

import static java.util.Arrays.*;

public class OptimalUtilization {
    public static void main(String[] args) {
        int [][] a1 = {{1, 2}, {2, 4}, {3, 6}};
        int [][] b1 = {{1,2}};
        System.out.println(optimalUtilization(a1,b1,7));
        System.out.println("-----------------------------------------------");
        int [][] a2 = {{1, 3}, {2, 5}, {3, 7},{4,10}};
        int [][] b2 = {{1,2},{2,3},{3,4},{4,5}};
        System.out.println(optimalUtilization(a2,b2,10));
        System.out.println("-----------------------------------------------");
        int [][] a3 = {{1, 8}, {2, 7}, {3, 14}};
        int [][] b3 = {{1,5},{2,10},{3,14}};
        System.out.println(optimalUtilization(a3,b3,20));
        System.out.println("-----------------------------------------------");
        int [][] a4 = {{1, 8},{2,8}, {3, 15}, {4, 9}};
        int [][] b4 = {{1,8},{2,11},{3,12},{4,12}};
        System.out.println(optimalUtilization(a4,b4,20));
        System.out.println("-----------------------------------------------");
    }

    public static List<List<Integer>> optimalUtilization(int [][] A, int [][] B, int K){

        sort(A, Comparator.comparingInt(a -> a[1]));
        sort(B, Comparator.comparingInt(b -> b[1]));

        int aLen = A.length, bLen = B.length;
        int left =0, right = bLen-1;
        int minVal =Integer.MIN_VALUE;
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        while(left < aLen && right >=0){
            int val = A[left][1] + B[right][1];

            if(val>K){
                right --;
            }else{
                int r = right;
                map.putIfAbsent(val,new ArrayList<>());
                //check for duplicates
                while(r >=0 && B[r][1] == B[right][1]){
                    List<Integer> listForVal = new ArrayList<>();
                    listForVal.addAll(Arrays.asList(A[left][0],B[r--][0]));
                    map.get(val).add(listForVal);
                }
                minVal = Math.max(minVal,val);
                left++;
            }
        }
        return map.getOrDefault(minVal,new ArrayList<>());
    }
}
