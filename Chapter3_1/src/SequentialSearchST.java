import java.util.ArrayList;
import java.util.Queue;

/**
 * Symbol table based on linked list
 *
 * @author Evan
 * @date 23/05/2018 18:59
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> {
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

    /**
     * get value if contains key, else return null
     *
     * @param key the key which to search
     * @return {@code Value} if the symbol table contains the key
     *         {@code null} if the symbol table doesn't contain the key
     * */
    public Value get(Key key) {
        for (var x = first; x != null; x = x.next) {
            // scan the table to match the key
            if (x.key.compareTo(key) == 0) {
                // if find, return the value
                return x.value;
            }
        }
        // if not find, return null
        return null;
    }

    /**
     * put a key-value pair to the symbol table
     * if the key exists, it will update the value
     *
     * @param key the key to add or update
     * @param value the value to add or update
     * if {@code value} is null, then delete the key-value pair if exists
     * @throws IllegalArgumentException if key is null
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        // x points to the first node
        var x = this.first;
        while (x != null) {
            // scan the table to find whether key exists
            if (x.key.compareTo(key) == 0) {
                // if exists, update the value
                x.value = value;
                return;
            }
            x = x.next;
        }
        // if not exist, create node at the top
        x = new Node(key, value, first);
        first = x;
        ++n;
    }

    /**
     * delete the key-value pair according the key in the symbol table
     * if key doesn't exist, do nothing and return
     *
     * @param key the key to be deleted
     * */
    public void delete(Key key) {
        // prior points to the prior node
        // current points to the current node
        Node prior = null, current = first;
        while (current != null && current.key.compareTo(key) != 0) {
            // find if exists the node with the key
            prior = current;
            current = current.next;
        }
        if (current == null) {
            // don't find the node
            return;
        } else {
            if (prior == null) {
                // delete the first node
                first = current.next;
            } else {
                // delete the normal node
                prior.next = current.next;
            }
            current = null;
            --n;
        }
    }

    /**
     * return the size of the symbol table
     * */
    public int size() {
        return n;
    }

    /**
     * to judge if key exists in the symbol table
     *
     * @param key the key needs to be found
     * */
    public boolean contains(Key key) {
        for (var x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) == 0) {
                // if contain, return true
                return true;
            }
        }
        // else, return false
        return false;
    }

    /**
     * return if the table is empty
     * */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * return the iterable object contains all keys
     * */
    public Iterable<Key> keys() {
        ArrayList<Key> list = new ArrayList<>();
        for (var x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }

    public void display() {
        System.out.println("Key\tValue");
        for (var x = first; x != null; x = x.next) {
            System.out.println(x.key + "\t" +x.value);
        }
    }

    public static void main(String[] args) {
        var st = new SequentialSearchST<String, Integer>();
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        st.put("E", 6);
        //st.display();
        System.out.println(st.get("A"));
        st.delete("H");
        st.display();
    }

}
