package com.practice.leetcode;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentElements {

    /**
     * Time Complexity: O(NlogN), where N is the length of words.
     * We count the frequency of each word in O(N) time,
     * then we sort the given words in O(NlogN) time.
     *
     * Space Complexity: O(N), the space used to store our candidates.
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingSort(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }

    /**
     * Time Complexity: O(Nlogk), where N is the length of words.
     * We count the frequency of each word in O(N) time, then we add N words to the heap, each in O(logk) time.
     * Finally, we pop from the heap up to k times. As k≤N, this is O(Nlogk) in total.
     *
     * Space Complexity: O(N), the space used to store our count.
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> count = new HashMap();
        for(String word:words){
            count.put(word,count.getOrDefault(word,0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<String>((o1, o2)-> count.get(o1).equals(count.get(o2))?       o1.compareTo(o2):count.get(o1)-count.get(o2));

        for(String word: count.keySet()){
            queue.offer(word);
            if(queue.size()>k)
                queue.poll();
        }
        List<String> res = new ArrayList<>();
        while(!queue.isEmpty()){
            res.add(queue.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
