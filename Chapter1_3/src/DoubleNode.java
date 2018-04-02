import edu.princeton.cs.algs4.StdOut;

/**
 * 双向链表类, 每个结点都含有一个指向前驱元素和后继元素的引用
 * 包含方法：在表头、表尾插入、删除结点, 在指定结点之前、之后插入、删除指定结点
 * 和若干调试用的静态方法和实例方法
 * <p>
 * 注意：该类没有实现迭代器，但是内置了输出函数display
 * 头元素的前驱和尾元素的后继均为null
 *
 * @author Landy
 * @date 2018/4/1
 */
public class DoubleNode {
    private class Node {
        int data;
        // 前驱结点
        Node prior = null;
        // 后继结点
        Node next = null;

        private Node(int value) {
            data = value;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public DoubleNode() {
        // 构造函数
        first = last = null;
        length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    /**
     * 在表头插入一个结点
     *
     * @param data 插入结点的数据值，只支持int型数据
     */
    public void insertFront(int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            last = node;
        } else {
            node.next = first;
            first.prior = node;
        }
        first = node;
        length++;
    }

    /**
     * 删除表头结点
     *
     * @return 返回被删除结点的数据值
     */
    public int deleteFront() {
        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }
        Node oldFirst = first;
        first = oldFirst.next;
        int val = oldFirst.data;
        oldFirst = null;
        length--;
        if (isEmpty()) {
            // 当链表只有一个结点且被删除时置空链表
            last = null;
        }
        return val;
    }

    /**
     * 在表尾插入一个结点
     *
     * @param data 插入结点的数据值，只支持int型数据
     */
    public void insertRear(int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            first = node;
        } else {
            node.prior = last;
            last.next = node;
        }
        last = node;
        length++;
    }

    /**
     * 删除表尾结点
     *
     * @return 返回被删除结点的数据值
     */
    public int deleteRear() {
        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }
        Node oldLast = last;
        int val = oldLast.data;
        last = oldLast.prior;
        oldLast = null;
        length--;
        if (isEmpty()) {
            first = null;
        }
        return val;
    }

    /**
     * 在索引为index的结点前插入新的结点，新结点的数据值为data
     *
     * @param index 目标结点索引值
     * @param data  插入的新数据值
     */
    public void insertBefore(int index, int data) {
        if (index <= 0 || index > length) {
            // 索引值不合法
            throw new IndexOutOfBoundsException("Index is illegal");
        } else if (index == 1) {
            // 在表头插入，直接调用函数不再重写
            this.insertFront(data);
            return;
        }
        Node current = first, newNode = new Node(data);
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        newNode.prior = current.prior;
        newNode.next = current;
        current.prior.next = newNode;
        current.prior = newNode;
        length++;
    }

    /**
     * 在索引为index的结点后插入新的结点，新结点的数据值为data
     *
     * @param index 目标结点索引值
     * @param data  插入的新数据值
     */
    public void insertAfter(int index, int data) {
        if(index <= 0 || index > length) {
            // 索引不合法
            throw new IndexOutOfBoundsException("Index is illegal");
        } else if (index == length) {
            // 在表尾插入，直接调用函数不再重写
            this.insertRear(data);
            return;
        }
        Node current = first, newNode = new Node(data);
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        newNode.prior = current;
        newNode.next = current.next;
        current.next.prior = newNode;
        current.next = newNode;
        length++;
    }

    /**
     * 删除索引为index的结点并返回结点数据
     *
     * @param index 要删除的结点索引
     * @return 返回要删除的结点的数据
     * */
    public int delete(int index) {
        if (index <= 0 || index > length) {
            // 索引不合法
            // 包含空链表的情形，空链表索引必定不合法
            throw new IndexOutOfBoundsException("Index is illegal");
        } else if (index == 1) {
            return this.deleteFront();
        } else if (index == length) {
            return  this.deleteRear();
        }
        Node current = first;
        for(int i = 1; i < index; i++) {
            current = current.next;
        }
        int val = current.data;
        length--;
        current.prior.next = current.next;
        current.next.prior = current.prior;
        current = null;
        return val;
    }

    /**
     * 输出链表的所有值
     */
    public void display() {
        Node current = first;
        while (current != null) {
            StdOut.print(current.data + " ");
            current = current.next;
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // 测试用例
        DoubleNode list = new DoubleNode();
        list.insertFront(3);
        StdOut.println(list.deleteFront());
        list.insertRear(5);
        list.insertRear(7);
        StdOut.println(list.deleteRear());
        list.insertFront(3);
        list.insertRear(9);
        list.insertBefore(3, 7);    // 在索引前插入
        list.insertBefore(1, 1);    // 尝试在首位插入
        list.display();
        list.insertAfter(3, 6);    // 在索引后插入
        list.insertAfter(list.length, 11);    // 尝试在末尾插入
        list.display();
        list.delete(3);
        list.display();
        list.delete(1);
        list.display();
        list.delete(list.size());
        list.display();
    }
}
