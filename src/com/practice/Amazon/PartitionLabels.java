package com.practice.Amazon;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels("abcdbaefghe"));
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels("abccaddbeffe"));
    }

    /***
     * Time Complexity: O(N), where N is the length of S.
     * Space Complexity: O(1) to keep data structure last of not more than 26 characters.
     * O(N): for Character array
     */
    public static List<Integer> partitionLabels(String S){
        List<Integer> res = new ArrayList<>();

        int[] last = new int[26];
        char[] chArr = S.toCharArray();

        //Populate last index for each character
        for(int i=0;i<chArr.length; i++){
            last[chArr[i] - 'a']= i;
        }

        int beginPart =0, endPart =0;
        for(int i=0;i<chArr.length; i++){
            endPart = Math.max(endPart, last[chArr[i] - 'a']);
            if(i == endPart){
                res.add(endPart - beginPart +1);
                beginPart= endPart +1;
            }
        }

        return res;
    }
}
