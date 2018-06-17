import java.util.ArrayList;

/**
 * Left-side Red-Black-Binary-Search-Tree
 *
 * @author Evan
 * @date 10/06/2018 11:01
 */
public class RedBlackBST <Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int n;
        boolean color;

        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return (x == null ? BLACK : x.color == RED);
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return (x == null ? 0 : x.n);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null) {
            return new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Value get(Key key) {
        return get(root, key);
    }

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

    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node node) {
        if (node == null) {
            return true;
        }
        if (isRed(node.right)) {
            return false;
        }
        if (isRed(node) && isRed(node.left)) {
            return false;
        }
        return is23(node.left) && is23(node.right);
    }

    public boolean isBalance() {
        int black = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalance(root, black);
    }

    private boolean isBalance(Node node, int black) {
        if (node == null) {
            return black == 0;
        }
        if (!isRed(node)) {
            black--;
        }
        return isBalance(node.left, black) && isBalance(node.right, black);
    }

    private void flipColors_Delete(Node h) {
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    private Node moveRedLeft(Node node) {
        flipColors_Delete(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors_Delete(h);
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        return balance(h);
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if(key.compareTo(h.key) == 0 && h.right == null) {
                return null;
            }
            if (key.compareTo(h.key) == 0) {
                h.value = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMax(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }
}
