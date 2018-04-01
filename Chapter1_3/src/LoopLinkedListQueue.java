import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 以环形链表实现的队列数据结构
 * 只保留一个指向last的结点, first即为last.next
 * 链表为空时last为null, 不保存first
 * 实现了可迭代Iterable接口
 *
 * @author Landy
 * @date 2018/04/01
 */

public class LoopLinkedListQueue<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item date;
        Node<Item> next = null;
    }

    private Node<Item> last = null;
    private int length = 0;

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    /**
     * 将一个新元素item添加到队列中
     *
     * @param item 需要添加的元素
     **/
    public void enqueue(Item item) {
        // 如果链表为空
        if (last == null) {
            last = new Node<>();
            last.date = item;
            // 头尾元素指向同一个结点
            last.next = last;
        } else {
            Node<Item> oldLast = last;
            last = new Node<>();
            last.date = item;
            last.next = oldLast.next;
            oldLast.next = last;
        }
        length++;
    }

    /**
     * 将队首元素移出队列
     *
     * @return 队首元素的值
     **/
    public Item dequeue() {
        //空链表
        if (isEmpty()) {
            return null;
        }
        /*
         * oldFirst保存出队结点, 若链表非空, 则oldFirst必定不为空
         * 当链表长度为1是, oldFirst即为last
         * */
        Node<Item> oldFirst = last.next;
        last.next = last.next.next;
        --length;
        if (isEmpty()) this.last = null;
        Item val = oldFirst.date;
        oldFirst = null;
        return val;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator<>(last.next, length);
    }

    /**
     * 迭代器Iterator的类定义
     **/
    private class QueueIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        //保存长度辅助迭代
        private int length;

        public QueueIterator(Node<Item> node, int length) {
            current = node;
            this.length = length;
        }

        @Override
        public boolean hasNext() {
            return length > 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NullPointerException();
            Item val = current.date;
            current = current.next;
            length--;
            return val;
        }
    }

    public static void main(String[] args) {
        // 测试用例
        LoopLinkedListQueue<Integer> queue = new LoopLinkedListQueue<>();
        queue.enqueue(5);
        //单元素出队测试
        StdOut.println(queue.dequeue());
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i * 2);
        }
        for (int i : queue) StdOut.println(i);
    }
}
