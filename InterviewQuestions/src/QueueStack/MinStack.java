// package QueueStack;

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
        this.stack = null;
    }

    public void push(int x) {
        if (this.stack == null) {
            this.min = x;
        } else {
            this.min = Math.min(x, this.min);
        }
        MSNode<Integer> newNode = new MSNode<Integer>(x, this.stack);
        this.stack = newNode;
        this.print();
    }

    public void pop() {
        if (this.stack != null) {
            int currVal = this.stack.getValue();
            this.stack = this.stack.getNext();
            if (currVal == this.min) {
                MSNode<Integer> currPtr = this.stack;
                if (currPtr != null) {
                    this.min = currPtr.getValue();
                }
                while (currPtr != null) {
                    this.min = Math.min(this.min, currPtr.getValue());
                    currPtr = currPtr.getNext();
                }
            }
        }
        this.print();
    }

    public int top() {
        if (this.stack == null) {
            return 0;
            // throw new Exception("Stack is empty");
        }
        System.out.println("top: " + this.stack.getValue());
        return this.stack.getValue();
    }

    public int getMin() {
        if (this.stack == null) {
            return 0;
            // throw new Exception("Stack is empty");
        }
        System.out.println("getMin: " + this.min);
        return this.min;
    }

    private void print() {
        System.out.print("Printing stack: ");
        MSNode<Integer> currPtr = this.stack;
        while (currPtr != null) {
            System.out.print(currPtr.getValue() + "\t");
            currPtr = currPtr.getNext();
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {{
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }}
}