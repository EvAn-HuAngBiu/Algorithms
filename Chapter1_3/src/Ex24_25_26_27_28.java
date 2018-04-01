import edu.princeton.cs.algs4.StdOut;

public class Ex24_25_26_27_28 extends Ex19_20_21 {

    /**
     * 这个类继承自Ex19_20_21以获取结点及部分函数实现
     * 成员函数可直接使用在所有链式结构中
     *
     * @author landy
     * @date 2018/04/01
     * */

    public void removeAfter(Node node) {
        // 删除node结点的后续结点
        if(node == null || node.next == null) { return; }
        Node temp = node.next;
        node.next = temp.next;
        temp = null;
    }

    public void insertAfter(Node position, Node source) {
        // 将source结点插入在position结点之后
        source.next = position.next;
        position.next = source;
    }

    public void remove(int key) {
        // 移除链表中键为key的结点
        Node current = first;
        // 记录当前结点索引
        int t = 1;
        while(current != null) {
            if (current.data == key) { delete(t); }
            current = current.next;
            t++;
        }
    }

    public int max() {
        // 返回链表中键最大的结点的值
        if (first != null) {
            int maxValue = first.data;
            Node current = first.next;
            while (current != null) {
                if (current.data > maxValue) maxValue = current.data;
                current = current.next;
            }
            return maxValue;
        } else {
            return 0;
        }
    }

    public int maxByRecursion(Node node, int nowMax) {
        // 以递归形式返回链表中键最大的值
        if(node == null) return nowMax;
        if(node.data > nowMax) {
            return maxByRecursion(node.next, node.data);
        } else {
            return maxByRecursion(node.next, nowMax);
        }
    }

    public static void main(String[] args) {
        // 测试用例
        Ex24_25_26_27_28 list = new Ex24_25_26_27_28();
        list.setValue();
        // 删除第二个结点, 即元素4
        list.removeAfter(list.first);
        list.display(list.first);
        Node node = list.new Node();
        node.data = 7;
        list.insertAfter(list.first.next, node);
        list.display(list.first);
        // 删除元素0
        list.remove(0);
        list.display(list.first);
        StdOut.println(list.max());
        StdOut.println(list.maxByRecursion(list.first, 0));
    }
}
