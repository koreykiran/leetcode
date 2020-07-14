package com.practice.Amazon;

import java.util.Arrays;

public class ReorderLogs {
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
