import edu.princeton.cs.algs4.StdOut;

/**
 * @author Evan
 * @date 27/05/2018 12:30
 */
public class Ex03 {
    public static void main(String[] args) {
        var st = new OrderedSequentialSearchST<String, Integer>();
        st.put("S", 0);
        st.put("E", 1);
        st.put("B", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        st.put("E", 6);
        st.put("A", 9);
        st.delete("L");
        st.delete("H");
        st.delete("S");
        st.display();
    }
}

class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first = null;
    private int n = 0;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Value get(Key key) {
        Node p = first;
        while (p != null) {
            if (key.compareTo(p.key) == 0) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        Node prior = null, p = first;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp == 0) {
                p.value = value;
                return;
            } else if (cmp < 0) {
                break;
            }
            prior = p;
            p = p.next;
        }
        if (prior == null) {
            first = new Node(key, value, first);
        } else {
            prior.next = new Node(key, value, prior.next);
        }
        ++n;
    }

    public void delete(Key key) {
        Node p = first, prior = null;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                return;
            }
            prior = p;
            p = p.next;
        }
        if (prior != null) {
            prior.next = p.next;
            p = null;
            --n;
        }
    }

    public void display() {
        Node p = first;
        while (p != null) {
            StdOut.println(p.key + " " + p.value);
            p = p.next;
        }
    }
}
