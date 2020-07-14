package com.practice.leetcode;

import java.util.Arrays;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.
 * Then, either:
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 *
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 * Constraints:
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderDataLogFiles {

    public static void main(String[] args) {
        String [] data = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        reorderLogFiles(data);
        printData(data);
    }

    public static void printData(String [] data){
        Arrays.stream(data).forEach(System.out::println);
    }

    /**
     * Time Complexity: O(NlogN), where N is the total content of logs.
     * Space Complexity: O(N) Comparator Uses merge sort which takes O(N) Space
     */
    public static String[] reorderLogFiles(String[] logs) {

        Arrays.sort(logs,(log1, log2)->{
            String [] splitLog1 = log1.split(" ",2);
            String [] splitLog2 = log2.split(" ",2);

            boolean isDigit1 = Character.isDigit(splitLog1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(splitLog2[1].charAt(0));

            if(!isDigit1 && !isDigit2){
                // both logs are letter logs
                // compare the log strings if equal compare the log ids
                // else compare the log strings
                return splitLog1[1].equals(splitLog2[1]) ? splitLog1[0].compareTo(splitLog2[0]) : splitLog1[1].compareTo(splitLog2[1]) ;
            }
            else if(!isDigit1)
                return -1; // first log is a letter log so that is smaller
            else if(!isDigit2)
                return 1; // second log is digit log so that is greater
            return 0; // both logs are digit logs so they are equal

        });
        return logs;
    }
}
