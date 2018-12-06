package QueueStack;

import java.util.LinkedList;

public class MinStack {
    private class MSNode<T> {
        private MSNode<T> next;
        private T value;

        public MSNode(T val, MSNode<T> next) {
            this.value = val;
            this.next = next;
        }

        public MSNode<T> getNext() {
            return this.next;
        }

        public T getValue() {
            return this.value;
        }
    }

    private int min;
    private MSNode<Integer> stack;

    public MinStack() {
        stack = null;
    }

    public void push(int x) {
        if (stack == null) {
            min = x;
        } else {
            min = Math.min(x, min);
        }
        MSNode<Integer> newNode = new MSNode<Integer>(x, stack);
        stack = newNode;
    }

    // public void pop() {
    //     if (stack)
    //     if (stack.isEmpty()) {
    //         return;
    //     }
    //     int removedVal = stack.removeLast();
    //     if (removedVal == min) {
    //         for (int x : stack) {
    //             min = Math.min(x, min);
    //         }
    //     }
    // }

    // public int top() {
    //     return stack.getLast();
    // }

    public static void main(String[] args) {{
        MinStack stack = new MinStack();
    }}
}