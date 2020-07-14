package com.practice;

import java.util.ArrayList;
import java.util.List;

class MyHashSet {
    List<Integer>[] hashSet;
    int M;
    /** Initialize your data structure here. */
    public MyHashSet() {
        M=1000;
        hashSet = new ArrayList[M];
    }
    public int convertKey(int key){
        return key%M;
    }
    
    public void add(int key) {
        int index = convertKey(key);
        System.out.println(index);
         List<Integer>bucket=hashSet[index];
         if(bucket==null){
             bucket = new ArrayList<Integer>();
             hashSet[index] = bucket;
         }
         bucket.add(key);
    }
    
    public void remove(int key) {
        int index = convertKey(key);
        List<Integer>bucket=hashSet[index];
        if(bucket==null || bucket.isEmpty()){
            return;
        }
        for(int i=0;i<bucket.size();i++){
            if(bucket.get(i) == key){
                bucket.remove(i);        
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = convertKey(key);
        List<Integer>bucket=hashSet[index];
        if(bucket==null || bucket.isEmpty()){
            return false;
        }
        for(int i=0;i<bucket.size();i++){
            if(bucket.get(0) == key){
                return true;
            }
        }
        return false;
    }
}