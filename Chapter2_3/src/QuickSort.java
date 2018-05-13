import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Quick sort class
 *
 * @author Evan
 * @version 1.0 2018/5/10 21:09
 */
public class QuickSort {

    public static int times = 0;

    public static boolean less(Comparable v, Comparable w) {
        // return whether v is less than w
        times++;
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        // exchange two elements
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort1(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            //insert.insertSort_OPT2(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort1(a, lo, j - 1);
        sort1(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        // v is the partition value
        var v = a[lo];
        while (true) {
            // find the first element which is greater than v from the left
            while (less(a[++i], v) && i < hi) ;
            // find the first element which is less than v from the right
            while (less(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[20000];
        for (int i = 0; i < 20000; i++) {
            nums[i] = 20000 - i;
        }
        // shuffling can significantly improve sorting speed
        StdRandom.shuffle(nums);
        var time = new Stopwatch();
        sort1(nums, 0, nums.length - 1);
        StdOut.println(time.elapsedTime() + " ms");

        Integer[] nums2 = {2, 6, 7, 9, 4, 5, 7, 3, 8, 1, 4};
        sort1(nums2, 0, nums2.length - 1);
        for (int i : nums2) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        Character[] c = {'E', 'A', 'S', 'Y', 'Q', 'U', 'E', 'S', 'T',
                'I', 'O', 'N'};
        sort1(c, 0, c.length - 1);
        for (var i : c) {
            System.out.print(i + " ");
        }
        System.out.println();

        times = 0;
        Integer[] nums3 = new Integer[7];
        for (int i = 0; i < nums3.length; i++) {
            nums3[i] = 1;
        }
        sort1(nums3, 0, nums3.length - 1);
        System.out.println(times);
    }
}

/**
 * Reference to insert sort
 *
 * @author Landy
 */
class insert {

    private static int binarySearch(Comparable[] a, int start, int end, Comparable src) {
        while (start < end) {
            int mid = (start + end) / 2;
            Comparable mid_data = a[mid];
            if (QuickSort.less(src, mid_data)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void insertSort_OPT2(Comparable[] a, int start, int end) {
        // 经过二次优化的插入排序
        // 折半插入排序
        for (int i = start; i <= end; i++) {
            Comparable temp = a[i];
            int pos = binarySearch(a, start, i, temp);
            System.arraycopy(a, pos, a, pos + 1, i - pos);
            a[pos] = temp;
        }
    }
}