package com.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum:
 * <p>
 * Given array of integers, return indices of two numbers such that they add up to a specific target
 * <p>
 * Example:
 * Input: Nums = [2,7,11,15] target =9
 * Output: [0,1]
 * Because nums[0]+nums[1] = 2+7 =9
 * <p>
 * Solution: As we loop array check if  the complement I.e. target – num  exists in the hashMap
 * if yes return complement's index and the current index
 * if not add the current number and it's index to hashMap
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * <p>
 * Algorithm:
 * {@code
 * For every num in nums
 *  Comp = target – nums
 *  If hashmap contains comp
 *      Return [comp index, current num index]
 *  Else
 *      Add (current num, current num index) to hashMap
 * If no such pair return[-1,-1]
 * }
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> hashMap = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (hashMap.containsKey(comp)) {
                return new int[]{hashMap.get(comp), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
