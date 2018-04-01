import edu.princeton.cs.algs4.StdOut;

public class Ex19_20_21 {

    /**
    * 这个类是对LinkedListQueue和LinkedListStack的扩展
    * 类成员函数可以直接移植到上述类中
    * 类没有构造泛型, 用Int数据代替所有数据
    * @author:landy
    * */

    //为了方便继承类实现，可见性设为Protected
    protected class Node {
        int data;
        Node next = null;
    }

    protected Node first;

    public void deleteLastNode() {
        //删除链表尾结点
        if(first == null) return;   //空链表
        if(first.next == null) {
            first = null;    //只有一个结点
            return;
        }
        Node current = first;
        //如果下两个结点为空那么current指示倒数第二个结点
        //只需移除current的下一个结点即可删除
        while(current.next.next != null)
            current = current.next;
        current.next = null;
    }

    public int delete(int k) {
        //删除第k个结点
        if(k == 1) {
            //删除首结点
            Node temp = first;
            int val = temp.data;
            first = first.next;
            temp = null;
            return val;
        }
        Node priorTarget = first;
        int current = 1;
        while(current++ < k-1 && priorTarget != null)
            priorTarget = priorTarget.next;
        if(priorTarget == null || priorTarget.next == null)
            //索引越界
            throw new IndexOutOfBoundsException("Target index is inaccessible");
        Node target = priorTarget.next;
        priorTarget.next = target.next;
        int val = target.data;
        target = null;
        return val;
    }

    public boolean find(int key) {
        //链表类成员函数，不需要提供目标链表
        Node current = first;
        while(current != null) {
            if(current.data == key) return true;
            current = current.next;
        }
        return false;
    }

    public void display(Node node) {
        //打印链表内容
        Node current = node;
        while(current != null) {
            StdOut.print(current.data + " ");
            current = current.next;
        }
        StdOut.println();
    }

    public void setValue() {
        //临时数值初始化函数
        //初始化6个值，最后一个为多余的0用于测试删除尾结点
        first = new Node();
        Node current = first;
        for(int i = 1; i <= 5; i++) {
            current.data = i * 2;
            current.next = new Node();
            current = current.next;
        }
    }

    public static void main(String[] args) {
        //测试用例
        //构造链表
        Ex19_20_21 list = new Ex19_20_21();
        list.setValue();    //赋值
        list.display(list.first);
        list.deleteLastNode();  //删除尾部的多余0
        list.display(list.first);
        list.delete(3); //删除索引为3的元素, 即删除数字6
        list.display(list.first);
        StdOut.println(list.find(4));
        StdOut.println(list.find(6));
    }
}
