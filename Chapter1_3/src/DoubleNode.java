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
        //前驱结点
        Node prior = null;
        //后继结点
        Node next = null;
    }

    private Node first;
    private Node last;
    private int length;

    public DoubleNode() {
        //构造函数
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
        Node node = new Node();
        node.data = data;
        node.next = first;
        if (first != null) {
            first.prior = node;
        } else {
            last = node;
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
        Node popNode = first;
        first = popNode.next;
        int val = popNode.data;
        popNode = null;
        length--;
        if (isEmpty()) {
            //链表只有一个结点时，删除后链表为空
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
        Node node = new Node();
        node.data = data;
        node.prior = last;
        if(last != null) {
            last.next = node;
        } else {
            first = node;
        }
        last = node;
        length++;
    }

    /**
     * 删除表头结点
     *
     * @return 返回被删除结点的数据值
     */
    public int deleteRear() {
        if(isEmpty()) {
            throw new NullPointerException("List is empty");
        }
        Node oldLast = last;
        int val = oldLast.data;
        last = oldLast.prior;
        oldLast = null;
        length--;
        if(isEmpty()) {
            first = null;
        }
        return val;
    }
}
