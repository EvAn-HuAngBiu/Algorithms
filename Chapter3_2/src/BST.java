import java.util.ArrayList;
import java.util.Stack;

/**
 * Binary search tree
 *
 * @author Evan
 * @date 29/05/2018 07:48
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    /**
     * Tree node define
     * n: The sum of nodes in sub-tree
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    /**
     * External Interface:
     * Get the total number of nodes in the current tree
     *
     * @return the size of the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Internal Interface:
     * Get the number of nodes in the specified node tree
     *
     * @param node node which you want to get the child nodes sum
     * @return the size of the node
     */
    private int size(Node node) {
        return (node == null ? 0 : node.n);
    }

    /**
     * Return whether the tree is empty
     * */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * External Interface:
     * Get the value of specified key
     *
     * @param key the value of the key you want to get
     * @return if {@code key} exists, then return the corresponding value
     * else return null
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Internal Interface:
     * Get the value of specified key from designated node by recursion
     *
     * @param n   the beginning node
     * @param key the value of the key you want to get
     * @return if {@code key} exists, then return the corresponding value
     * else return null
     */
    private Value get(Node n, Key key) {
        if (n == null) {
            // not exist, return null
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            // if key is greater, search the right
            return get(n.right, key);
        } else if (cmp < 0) {
            // if key is lesser, search the left
            return get(n.left, key);
        } else {
            // found, return
            return n.value;
        }
    }

    /**
     * External Interface:
     * Insert the key-value pair if not exist or
     * Update the value if the key-value pair existed
     *
     * @param key the key you want to insert or update
     * @param value the value you want to insert or update
     *              if {@code value} is null, delete the key-value pair
     * @exception IllegalArgumentException if {@code key} is null
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (value == null) {
            //delete()
            return;
        }
        // reset the root node
        root = put(root, key, value);
    }

    /**
     * Internal Interface:
     * Insert the key-value pair if not exist or
     * Update the value if the key-value pair existed by recursion
     * from specified node.
     *
     * @param key the key you want to insert or update
     * @param value the value you want to insert or update
     * @param node the beginning node
     * @return the updated node
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            // if node is null, create a new node and return
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            // if key is greater, add to the right
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            // if key is lesser, add to the left
            node.left = put(node.left, key, value);
        } else {
            // if found, update the value
            node.value = value;
        }
        // increase the depth of the node
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key max() {
        if(this.isEmpty()) {
            return null;
        } else {
            return max(root).key;
        }
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    public Key min() {
        if (this.isEmpty()) {
            return null;
        } else {
            return min(root).key;
        }
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        return (x == null ? null : x.key);
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return floor(node.left, key);
        } else if (cmp == 0) {
            return node;
        }
        Node t = floor(node.right, key);
        return (t == null ? node : t);
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        return (x == null ? null : x.key);
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return ceiling(node.right, key);
        } else if (cmp == 0) {
            return node;
        }
        Node t = ceiling(node.left, key);
        return (t == null ? node : t);
    }

    public Key select(int k) {
        if (root == null || k >= root.n || k < 0) {
            // empty tree or illegal index
            return null;
        } else {
            Node t = select(root, k);
            return (t == null ? null : t.key);
        }
    }

    private Node select(Node node, int k) {
        int t = size(node.left);
        if (t > k) {
            return select(node.left, k);
        } else if (t < k) {
            return select(node.right, k - t - 1);
        } else {
            return node;
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null) {
            return -1;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(key, node.left);
        } else if (cmp > 0) {
            return rank(key, node.right) + size(node.left) + 1;
        } else {
            return size(node.left);
        }
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        if (root == null) {
            return;
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    private Node delete(Key key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return delete(key, node.left);
        } else if (cmp > 0) {
            return delete(key, node.right);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        inOrderPrint(node.left);
        System.out.print(node.key);
        inOrderPrint(node.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<>();
        keys(root, list, lo, hi);
        return list;
    }

    private void keys(Node x, ArrayList<Key> list, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key),
                cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, list, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            list.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, list, lo, hi);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public double avgCompares() {
        int paths = avgCompares(root, 0);
        //return paths / size() + 1;
        return paths;
    }

    private int avgCompares(Node node, int len) {
        if (node == null) {
            return 0;
        }
        int path = len;
        path += avgCompares(node.left, len + 1);
        path += avgCompares(node.right, len + 1);
        return path;
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<>();
        st.put("S", 1);
        st.put("R", 5);
        st.put("E", 2);
        st.put("X", 3);
        st.put("A", 4);
        st.put("C", 6);
        st.put("H", 7);
        st.put("M", 8);
//        System.out.println(st.floor("G"));
//        System.out.println(st.ceiling("G"));
//        System.out.println(st.size(st.root));
//        System.out.println(st.select(6));
//        System.out.println(st.rank("B"));
//        st.inOrderPrintNonrecursion();
//        System.out.println();
//        for(var i : st.keys("F", "T")) {
//            System.out.print(i);
//        }
//        System.out.println();
//        System.out.println(st.height());
        System.out.println(st.size());
        System.out.println(st.avgCompares());
    }
}
