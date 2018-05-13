import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author Evan
 * @version 1.0 2018/5/10 23:41
 */
public class Quick3Way {
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + 15) {
            // insert sort will work well when element is less than 15
            insert.insertSort_OPT2(a, lo, hi);
            return;
        }
        // lt save the left bound, numbers in [lo,lt-1] is less than v
        // i save the equal bound, numbers in [lt,i-1] is equal than v
        // gt save the right bound, numbers in [gt+1,hi] is greater than v
        // numbers in [i,gt] is unknown
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        // set numbers in [i,gt] is unknown
        // when index is greater than gt, break
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                QuickSort.exch(a, lt++, i++);
            } else if (cmp > 0) {
                QuickSort.exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void sort2(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = array[lo];
        while (true) {
            while (QuickSort.less(array[++i], v))
                if (i == hi) break;
            while (QuickSort.less(v, array[--j]))
                if (j == lo) break;
            // pointers cross
            if (i == j && array[i].compareTo(v) == 0) {
                QuickSort.exch(array, ++p, i);
            }
            if (i >= j) {
                break;
            }
            QuickSort.exch(array, i, j);
            if (array[i].compareTo(v) == 0) {
                QuickSort.exch(array, ++p, i);
            }
            if (array[j].compareTo(v) == 0) {
                QuickSort.exch(array, --q, j);
            }

        }
        //将相等的元素交换到中间
        i = j + 1;
        for (int k = lo; k <= p; k++) {
            QuickSort.exch(array, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            QuickSort.exch(array, k, i++);
        }
        sort2(array, lo, j);
        sort2(array, i, hi);
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[20000];
        for (int i = 0; i < 20000; i++) {
            nums[i] = 20000 - i;
        }
        StdRandom.shuffle(nums);
        var time = new Stopwatch();
        sort(nums, 0, nums.length - 1);
        StdOut.println(time.elapsedTime() + " ms");

        Integer[] nums2 = {4, 7, 1, 8, 3, 1, 4, 7, 5};
        sort2(nums2, 0, nums2.length - 1);
        for (var i : nums2) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
