import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Evan
 * @version 1.0 2018/5/12 10:30
 */
public class Ex18_ThreeSegments {

    private static Comparable getMid(Comparable[] a, int lo, int hi) {
        int mid = (lo + hi) / 2;
        if (QuickSort.less(a[mid], a[lo])) {
            QuickSort.exch(a, mid, lo);
        }
        if (QuickSort.less(a[hi], a[lo])) {
            QuickSort.exch(a, hi, lo);
        }
        if (QuickSort.less(a[hi], a[mid])) {
            QuickSort.exch(a, hi, mid);
        }
        QuickSort.exch(a, mid, hi - 1);
        return a[hi - 1];
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo - 1, j = hi - 1;
        // v is the partition value
        var v = getMid(a, lo, hi);
        while (true) {
            // find the first element which is greater than v from the left
            while (QuickSort.less(a[++i], v)) ;
            // find the first element which is less than v from the right
            while (j > lo && QuickSort.less(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            QuickSort.exch(a, i, j);
        }
        QuickSort.exch(a, hi - 1, i);
        return i;
    }

    private static void compareArray() {
        // Compare
        // Positive order array
        Integer[] pos = new Integer[10000];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = i;
        }
        long start = System.currentTimeMillis();
        // use standard Quick-Sort (without insert sort)
        QuickSort.sort1(pos, 0, pos.length - 1);
        double standardTime = System.currentTimeMillis() - start;

        // use Quick3Way-Sort
        for (int i = 0; i < pos.length; i++) {
            pos[i] = i;
        }
        start = System.currentTimeMillis();
        Quick3Way.sort(pos, 0, pos.length - 1);
        double q3wTime = System.currentTimeMillis() - start;

        // use 3Segments-Sort
        for (int i = 0; i < pos.length; i++) {
            pos[i] = i;
        }
        start = System.currentTimeMillis();
        sort(pos, 0, pos.length - 1);
        double tsTime = System.currentTimeMillis() - start;

        System.out.printf("Standard : %.4f, Quick3Way : %.4f, 3Segments : %.4f",
                standardTime, q3wTime, tsTime);
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[8];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        StdRandom.shuffle(nums);
        sort(nums, 0, nums.length - 1);
        for (int i : nums) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        compareArray();
    }
}
