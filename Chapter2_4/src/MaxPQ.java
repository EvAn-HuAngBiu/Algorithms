import edu.princeton.cs.algs4.StdOut;

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

    public void display() {
        for (int i = 1; i <= N; i++) {
            StdOut.print(pq[i]);
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        var pq = new MaxPQ<Character>(20);
        pq.insert('P');
        pq.insert('R');
        pq.insert('I');
        pq.insert('O');
        StdOut.print(pq.delMax());
        pq.insert('R');
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert('I');
        StdOut.print(pq.delMax());
        pq.insert('T');
        StdOut.print(pq.delMax());
        pq.insert('Y');
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert('Q');
        pq.insert('U');
        pq.insert('E');
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert('U');
        StdOut.print(pq.delMax());
        pq.insert('E');

        StdOut.println();
        String str = "EASYQUESTION";
        var newpq = new MaxPQ<Character>(20);
        for (int i = 0; i < str.length(); i++) {
            newpq.insert(str.charAt(i));
        }
        newpq.display();
    }
}
