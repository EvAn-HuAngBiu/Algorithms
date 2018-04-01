import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedListStack<Item> implements Iterable<Item> {

    private class Node<T> {
        T item = null;
        Node<T> next = null;
    }// 结点定义
    private Node<Item> head = new Node<>(); //头结点, 不保存数据
    private int length = 0; //链表长度

    public boolean isEmpty() { return length == 0; }
    public int size() { return length; }
    public void push(Item item) {
        Node old = head.next;
        head.next = new Node();
        head.next.item = item;
        if(old != null)
            head.next.next = old;
        length++;
    }
    public Item pop() {
        if(isEmpty()) throw new NullPointerException("Stack is empty");
        Node<Item> old_first = head.next;
        head.next = old_first.next;
        Item val = old_first.item;
        old_first = null;
        length--;
        return val;
    }
    public Item getTop() { return head.next.item; }


    public Iterator<Item> iterator() {
        return new ListIterator<>(head.next);
    }


    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedListStack<String> s = new LinkedListStack<>();
        while(!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if(!item.equals("-"))
                s.push(item);
            else if(!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println(s.size() + " left on stack");
    }
}
