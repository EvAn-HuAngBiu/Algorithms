import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class LinkedListQueue<Item> implements Iterable<Item> {

    private class Node {
        Item item = null;
        Node next = null;
    }
    private Node head = new Node();  //头结点为空，不保存数据
    private Node tail = new Node();
    private int length = 0; //队列长度, 默认为0

    public boolean isEmpty() { return length == 0; }
    public int size() { return length; }
    public void enqueue(Item item) {
        Node old_tail = tail;
        tail = new Node();
        tail.item = item;
        if(isEmpty()) head.next = tail;
        else old_tail.next = tail;
        length++;
    }

    public Item dequeue() {
        if(length == 0) throw new NullPointerException("Queue is empty");
        Node temp = head.next;
        head.next = temp.next;
        Item val = temp.item;
        temp = null;
        length--;
        return val;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head.next;
        public boolean hasNext() { return current == null; }
        public void remove() {}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<String> q = new LinkedListQueue<>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) q.enqueue(item);
            else if(!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println(q.size() + " left on queue");
    }
}
