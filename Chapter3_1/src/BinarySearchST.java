import java.util.ArrayList;

/**
 * Binary search symbol table based on arrays
 *
 * @author Evan
 * @date 26/05/2018 07:39
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    /**
     * keep keys by array
     * */
    private Key[] keys;
    /**
     * keep values by array
     * each key corresponds to a value
     * */
    private Value[] values;
    /**
     * save the length of symbol table
     * */
    private int n;

    /**
     * Constructed function
     * Initialize the capacity of the array
     *
     * @param capacity the capacity of the array
     *                 which is not allowed to resize
     * */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        n = 0;
    }

    /**
     * get the size of the symbol table
     * @return size of the symbol table
     */
    public int size() {
        return n;
    }

    /**
     * whether symbol table is empty
     * @return if {@code n} is zero then return true
     *         otherwise return false
     * */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Get the rank of the key
     * If the symbol table contains the key, it will return the position
     * of the key
     * Otherwise, it will return the least value position which is greater than key
     *
     * @param key the target key to find
     * @return the key position if the key exists or the minimum key position which
     *         is greater than key
     * */
    public int rank(Key key) {
        // use BinarySearch to find key
        // set the start position lo, and the end position hi
        int lo = 0, hi = n - 1;
        while (hi >= lo) {
            // set the middle position
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                // if key is less than mid, search left
                hi = mid - 1;
            } else if (cmp > 0) {
                // if key is greater than mid, search right
                lo = mid + 1;
            } else {
                // if equal, return
                return mid;
            }
        }
        // don't find, return the next position
        return lo;
    }

    /**
     * get value if contains key, else return null
     *
     * @param key the key which to search
     * @return {@code Value} if the symbol table contains the key
     *         {@code null} if the symbol table doesn't contain the key
     * */
    public Value get(Key key) {
        if (this.isEmpty()) {
            // empty table
            return null;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            // if the table contains the key
            return values[i];
        }
        // key doesn't exist
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
            // null key is not allowed in the table
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            // if set the value null, delete the key-value pair
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            // if the table contains the key, than update the value
            values[i] = value;
            return;
        }
        // otherwise, move the array elements and insert new pair
        for (int j = n; j > i; --j) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    /**
     * get the minimum key
     * */
    public Key min() {
        return (this.isEmpty() ? null : keys[0]);
    }

    /**
     * get the maximum key
     * */
    public Key max() {
        return (this.isEmpty() ? null : keys[n - 1]);
    }

    /**
     * get the key with index k
     * @param k the key index
     * @return {@code key} if k is a legal index (0 <= k <= n-1)
     *         {@code null} if k is out of boundary (k < 0 or k >= n)
     * */
    public Key select(int k) {
        return (k > n - 1 || k < 0 ? null : keys[k]);
    }

    /**
     * get the key which is greater or equal than given key
     *
     * @param key the key to get the ceiling value
     * @return {@code key} if the symbol table is not empty
     *         {@code null} if the symbol table is empty
     * */
    public Key ceiling(Key key) {
        return (this.isEmpty() ? null : keys[rank(key)]);
    }

    /**
     * get the key which is less or equal than given key
     *
     * @param key the key to get the floor value
     * @return {@code key} if the symbol table is not empty
     *         {@code null} if the symbol table is empty
     * */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            // if equal
            return keys[i];
        } else {
            // if the key is less than the first value, return null
            // because there is not a lesser value in the table
            return (i == 0 ? null : keys[i - 1]);
        }
    }

    /**
     * Delete the key-value pair in the symbol table
     * If the table is empty or the key doesn't exist,
     * nothing will be done but return
     *
     * Else, delete the key-value pair in the table
     *
     * @param key the key to be deleted
     * */
    public void delete(Key key) {
        if(this.isEmpty()) {
            // empty table
            return;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            // find the key-value pair
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            keys[n - 1] = null;
            values[n - 1] = null;
            n--;
        }
    }

    /**
     * get key iterable object
     *
     * @param i the start position
     * @param j the end position
     * */
    public Iterable<Key> keys(int i, int j) {
        ArrayList<Key> list = new ArrayList<>();
        for(; i < j; i++) {
            list.add(keys[i]);
        }
        return list;
    }

    public void display() {
        System.out.println("Key\tValue");
        for(int i = 0; i < n; i++) {
            System.out.println(keys[i] + "\t" + values[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        var st = new BinarySearchST<String, Integer>(20);
        st.put("S", 0);
        st.put("E", 1);
        st.put("B", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        st.put("E", 6);
        st.display();
        st.delete("R");
        st.display();
    }
}
