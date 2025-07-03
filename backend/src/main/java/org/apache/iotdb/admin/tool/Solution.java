package org.apache.iotdb.admin.tool;

import java.util.*;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

//    public void push(int node) {
//       stack1.add(0, node);
//    }
//
//    public int pop() {
//        Integer pop = stack1.pop();
//        System.out.println(pop);
//       return pop;
//    }
//
//    //  ABC
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.push(1);
//        solution.push(2);
//        solution.pop();
//
//        solution.pop();
//    }

    // A,B,C->C,B,A
    public void push(int node) {
        stack1.push(node);
        stack1.addAll(stack2);
        stack2.clear();
        stack2.addAll(stack1);
        stack1.clear();
    }
//
   public int pop() {
        Integer pop = stack2.pop();
        System.out.println(pop);
        return pop;
   }
//
//    //  ABC
//
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(2);
        solution.push(3);
        solution.pop();
        solution.push(1);
        solution.pop();
        solution.pop();

        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    }
}