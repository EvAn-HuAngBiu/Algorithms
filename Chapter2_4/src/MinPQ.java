import java.util.NoSuchElementException;

/**
 * @author Evan
 * @version 1.0 2018/5/17 23:29
 */
public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MinPQ(int maxN) {
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
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) {
                j++;
            }
            if (!less(k, j)) {
                exch(j, k);
            }
            k = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key k) {
        pq[++N] = k;
        swim(N);
    }

    public Key delMin() {
        Key k = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return k;
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return pq[1];
    }

    public static void main(String[] args) {
        MinPQ<Double> p = new MinPQ<>(20);
        p.insert(3.2);
        p.insert(5.6);
        p.insert(2.1);
        p.insert(4.37);
        System.out.println(p.delMin());
    }
}
