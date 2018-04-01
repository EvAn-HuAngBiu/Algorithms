import edu.princeton.cs.algs4.StdOut;

/**
 * Ex30类扩展了基本链表，添加了反转链表函数
 * 以递归、迭代、栈三种方式同时实现
 *
 * @author Landy
 * @date 2018/4/1
 */
public class Ex30 extends Ex19_20_21 {

    public Node reverseByIteration() {
        //以迭代方式反转链表

        //reverse保存反转链表的首结点
        Node reverse = null;
        //begin指示当前处理的结点
        Node begin = first;
        while(begin != null) {
            /*
            * 处理过程：
            * 先保存当前处理结点的下一个结点，即second
            * 再使begin下一个结点指向新链表中旧的首结点reverse，这样begin就变为了新的首结点
            * 将反转后新的首结点交给reverse保存, begin后移处理下一个结点, 下一个结点保存在second
            * */
            Node second = begin.next;
            begin.next = reverse;
            reverse = begin;
            begin = second;
        }
        return reverse;
    }

    public Node reverseByRecursion(Node node) {
        //以递归方式反转链表

        if(node == null) {
            //确定是否为空链表
            return null;
        }
        if(node.next == null) {
            //判断是否为最后一个结点
            return node;
        }
        Node second = node.next;
        //递归步骤, rest永远指向最后一个值即反转后的首结点
        //通过逐步向前确定下一个值来组成链表
        Node rest = reverseByRecursion(second);
        //反向链接
        second.next = node;
        node.next = null;
        return rest;
    }

    public Node reverseByStack() {
        //以栈的方式反转链表

        LinkedListStack<Node> stack = new LinkedListStack<>();
        Node current = first;
        if(current == null) {
            //空链表判定
            return null;
        }
        while(current != null) {
            //将结点入栈
            stack.push(current);
            current = current.next;
        }
        //保存新的头结点
        Node head = stack.getTop();
        while(stack.size() > 1) {
            Node first = stack.pop();
            Node last = stack.pop();
            first.next = last;
            last.next = null;
            stack.push(last);
        }
        //操作结束后栈内存在最后一个结点即尾结点
        return head;
    }

    public static void main(String[] args) {
        Ex30 list = new Ex30();
        list.setValue();
        list.display(list.reverseByIteration());    //反转
        list.setValue();
        list.display(list.reverseByRecursion(list.first));  //反转
        list.setValue();
        list.display(list.reverseByStack());    //反转
        list = new Ex30();
        list.first = list.new Node();
        list.first.data = 1;
        list.display(list.first);
        list.display(list.reverseByStack());    //单元素输出测试
    }
}
