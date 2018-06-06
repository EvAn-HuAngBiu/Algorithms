import java.lang.reflect.Parameter;
import java.util.Stack;

/**
 * BST without recursion
 *
 * @author Evan
 * @date 04/06/2018 22:25
 */
public class Ex13<Key extends Comparable<Key>, Value> {
    private Node root;
    private int n;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public Value get(Key key) {
        var p = root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp == 0) {
                return p.value;
            }
            p = (cmp > 0 ? p.right : p.left);
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        Node prior = null, x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.value = value;
            } else {
                prior = x;
                x = (cmp > 0 ? x.right : x.left);
            }
        }
        if (key.compareTo(prior.key) > 0) {
            prior.right = new Node(key, value);
        } else {
            prior.left = new Node(key, value);
        }
        n++;
    }

    private void inOrderPrintNonrecursion() {
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                System.out.print(p.key);
                p = p.right;
            }
        }
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<>();
        st.put("E", 1);
        st.put("D", 5);
        st.put("Q", 2);
        st.put("A", 3);
        st.put("J", 4);
        st.put("T", 6);
        st.put("M", 7);
        st.put("S", 8);
        System.out.println(st.rank("J"));
    }
}
