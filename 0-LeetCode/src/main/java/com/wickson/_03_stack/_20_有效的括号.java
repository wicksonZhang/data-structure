package com.wickson._03_stack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/valid-parentheses/description/
 */
public class _20_有效的括号 {

    public static void main(String[] args) {
        boolean valid = isValid("()");
        System.out.println("valid = " + valid);
    }

    public static boolean isValid(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char popChar = stack.pop();
                if (popChar == '(' && c != ')') return false;
                if (popChar == '[' && c != ']') return false;
                if (popChar == '{' && c != '}') return false;
            }
        }
        return stack.isEmpty();
    }

}
