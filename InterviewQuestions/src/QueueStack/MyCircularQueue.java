package QueueStack;

//input type? integer
//input range? [0, 1000]
//# of operation [1, 1000]
//what happens if it is empty and try to get/dequeue? return -1/false (error)
//what happens if it is full and try to enqueue? return false (error)

/** Array implementation of circular queue */
public class MyCircularQueue {
    private int[] queue;
    private int start, end;

    public static final int QUEUE_EMPTY = -1;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k];
        start = end = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (this.isFull()) {
            return false;
        }
        if (start == -1 && end == -1) {
            start = end = 0;
        } else {
            end++;
            end = end % queue.length;
        }
        queue[end] = value;

        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (start == end && !this.isEmpty()) {
            //last item, successful dequeue and reset start/end
            start = end = -1;
            return true;
        }
        if (this.isEmpty()) {
            return false;
        }
        start++;
        start = start % queue.length;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (this.isEmpty()) {
            return QUEUE_EMPTY;
        }
        return queue[start];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (this.isEmpty()) {
            return QUEUE_EMPTY;
        }
        return queue[end];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (start == -1 && end == -1);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        // return (end > start ? end : end + queue.length) - start + 1 == queue.length;
        return ((end + 1 )% queue.length) == start;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
        System.out.println(circularQueue.enQueue(6));// return true
        System.out.println(circularQueue.Rear());// return 3
        System.out.println(circularQueue.Rear());// return 3
        System.out.println(circularQueue.deQueue());// return true
        System.out.println(circularQueue.enQueue(5));// return true
        System.out.println(circularQueue.Rear());// return 3
    }
}