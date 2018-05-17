/**
 * Priority queue
 *
 * @author Evan
 * @version 1.0 2018/5/14 22:12
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        // swim element to keep ordered
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        // sink element to keep ordered
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (less(k, j)) {
                exch(k, j);
                k = j;
            }
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key val = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return val;
    }

    public static void main(String[] args) {
        var pq = new MaxPQ<Integer>(20);
        pq.insert(12);
        pq.insert(8);
        pq.insert(4);
        pq.insert(9);
        pq.insert(1);
        pq.insert(13);
    }
}
