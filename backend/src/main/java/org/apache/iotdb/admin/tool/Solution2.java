package org.apache.iotdb.admin.tool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: wangzhijun
 * @Date: 2024/02/05/16:52
 * @Description:
 */
public class Solution2 {

    Stack<Integer> stack = new Stack<>();
    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
     return stack.peek();
    }

    public int min() {
        int min = Integer.MAX_VALUE;
        for(Integer e: stack){
            if(e<min){
                min = e;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.push(-1);
        solution2.push(2);
        System.out.println(solution2.min());
        System.out.println(solution2.top());
        solution2.pop();
        solution2.push(1);
        System.out.println(solution2.top());
        System.out.println(solution2.min());

    }
}
