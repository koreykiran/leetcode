package com.practice.Amazon;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConnectRopes {
    static int index = 0;
    public static void main(String[] args) {
        int[] sticks={2,4,3};
        System.out.println(connectSticks(sticks));
        System.out.println(connectSticksPriorityQueue(sticks));
    }

    public static int connectSticksPriorityQueue(int[] sticks) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(sticks.length);
        for (int stick: sticks) {
            pq.offer(stick);
        }
        int cost = 0;
        while (pq.size() != 1) {
            int temp = pq.poll() + pq.poll();
            cost += temp;
            pq.offer(temp);
        }
        return cost;
    }

    public static int connectSticks(int[] sticks) {

        Queue<Integer> queue = new PriorityQueue();

        Arrays.sort(sticks);

        int cost=0;
        while(index<sticks.length || queue.size()>1){
            int a = getMin(sticks,queue);
            int b = getMin(sticks,queue);
            if(a == -1 || b == -1) break;
            queue.offer(a+b);
            cost+=a+b;
        }

        return cost;
    }
    public static int getMin(int sticks[], Queue<Integer> queue){
        if(index < sticks.length && queue.size()>0){
            if(sticks[index] < queue.peek()){
                return sticks[index++];
            }else{
                return queue.poll();
            }
        }else if(index < sticks.length){
            return sticks[index++];
        }else if(queue.size()>0){
            return queue.poll();
        }else{
            return -1;
        }
    }
}
