package com.practice.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Time complexity : O(nlogn)
 * Other than the sort invocation, we do a simple linear scan of the list, so the runtime is dominated by the O(nlgn) complexity of sorting.
 *
 * Space complexity : O(1) (or O(n))
 * If we can sort intervals in place, we do not need more than constant additional space.
 * Otherwise, we must allocate linear space to store a copy of intervals and sort that.
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        LinkedList<int []> res = new LinkedList();
        for(int [] interval : intervals){

            if(res.isEmpty() || res.getLast()[1] < interval[0]){
                res.add(interval);
            }else{
                res.getLast()[1] = Math.max(res.getLast()[1],interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

}
