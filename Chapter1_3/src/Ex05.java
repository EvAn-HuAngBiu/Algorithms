import edu.princeton.cs.algs4.*;

public class Ex05 {
    public static void main(String[] args) {
        //进制转换
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        int N = 50; //要转换的数
        while(N > 0) {
            //将N%2和N/2的数字改为x即可转换为x进制
            stack.push(N % 2);
            N /= 2;
        }
        stack.forEach(StdOut::print);
        StdOut.println();
    }
}
