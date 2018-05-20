import edu.princeton.cs.algs4.StdOut;

/**
 * Heapsort class
 *
 * @author Evan
 * @version 1.0 18/05/2018 09:13
 */
public class HeapSort {
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    private static void sink(Comparable[] a, int k, int i) {
        while (2 * k <= i) {
            int j = 2 * k;
            if (j < i && less(a, j, j + 1)) {
                j++;
            }
            if (less(a, k, j)) {
                exch(a, k, j);
            }
            k = j;
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = N / 2; i >= 1; i--) {
            sink(a, i, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public static void main(String[] args) {
        String[] str = {"S", "O", "R", "T", "E", "X",
                "A", "M", "P", "L", "E"};
        sort(str);
        for (var i : str) {
            StdOut.print(i);
        }
        StdOut.println();
    }
}
