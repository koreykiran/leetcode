package com.practice.Amazon;

import java.util.*;

public class SuggestedProducts {
    public static void main(String[] args) {
        String [] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        System.out.println(suggestedProductsTreeMap(products,"mouse"));
    }

    /**
     * The Binary Search and Trie method can be found here
     * https://leetcode.com/problems/search-suggestions-system/discuss/436151/JavaPython-3-Simple-Trie-and-Binary-Search-codes-w-comment-and-brief-analysis.
     */

    /***
     * Better Brute Force
     * TreeMap
     * Time: O( len(products) * len(searchWord) * ln(K)
     * space O(len(products)
     */
    public static List<List<String>> suggestedProductsTreeMap(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList();
        TreeMap<String,Integer> map = new TreeMap();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);
        int i=0;
        for(String product:products){
            map.put(product,i++);
        }
        String key="";
        for(char c: searchWord.toCharArray()){
            key+=c;
            String from = map.ceilingKey(key);
            String to = map.floorKey(key+"~");
            if(from == null || to == null) break;
            res.add(productsList.subList(map.get(from),Math.min(map.get(from)+3, map.get(to)+1)));
        }
        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }

    /***
     * Brute Force
     * Priority Queue
     * Time: O( len(products) * len(searchWord) * ln(K)
     * space O(K) == O(1)
     */

    public static List<List<String>> suggestedProductsBruteForce(String[] products, String searchWord) {
        PriorityQueue<String> queue = new PriorityQueue(Collections.reverseOrder());
        List<List<String>> res = new ArrayList();
        int k=3;
        for(int i=0;i<searchWord.length();i++){
            String prefix = searchWord.substring(0,i+1);
            for(String s:products){
                if(s.startsWith(prefix)){
                    queue.offer(s);
                }
                if(queue.size()>k){
                    queue.poll();
                }
            }

            List<String> list = new ArrayList<>();
            while(!queue.isEmpty()){
                list.add(0,queue.poll());
            }
            res.add(list);
            queue.clear();
        }

        return res;
    }
}
