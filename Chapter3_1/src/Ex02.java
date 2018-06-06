import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Symbol table based on unsorted array
 *
 * @author Evan
 * @date 27/05/2018 11:11
 */
public class Ex02 {
    public static void main(String[] args) {
        var st = new ArrayST<String, Integer>(20);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}

class ArrayST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Value get(Key key) {
        for (int i = 0; i < n; i++) {
            // search the whole table to find the key
            if (key.compareTo(keys[i]) == 0) {
                // found
                // Self-organizing lookup, see Ex22
                // move high-frequency elements to array headers
                Key temp = keys[i];
                keys[i] = keys[0];
                keys[0] = temp;
                Value temp2 = values[i];
                values[i] = values[0];
                values[0] = temp2;

                return values[i];
            }
        }
        // not found
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            // null key is not allowed in the table
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            // if set the value null, delete the key-value pair
            delete(key);
            return;
        }
        for (int i = 0; i < n; i++) {
            // search the whole table to find whether the key exists
            if (key.compareTo(keys[i]) == 0) {
                // found, update the value
                values[i] = value;
            }
        }
        // not found, insert new key-value pair
        if (n < keys.length) {
            keys[n] = key;
            values[n++] = value;
        }
    }

    public Iterable<Key> keys() {
        return new ArrayList<>(Arrays.asList(keys));
    }

    public void delete(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.compareTo(keys[i]) == 0) {
                keys[i] = keys[n - 1];
                values[i] = values[n - 1];
                keys[n - 1] = null;
                values[n - 1] = null;
                n--;
                return;
            }
        }
    }
}