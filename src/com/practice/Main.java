package com.practice;

public class Main {

    public static void main(String[] args) {
	// write your code here

        /**
         * Your MyHashSet object will be instantiated and called as such:
         * MyHashSet obj = new MyHashSet();
         * obj.add(key);
         * obj.remove(key);
         * boolean param_3 = obj.contains(key);
         */
        int key =1;
        MyHashSet obj = new MyHashSet();
        obj.add(key);
        obj.remove(key);
        boolean param_3 = obj.contains(key);
    }
}
