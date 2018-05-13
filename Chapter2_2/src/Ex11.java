import edu.princeton.cs.algs4.Merge;

import java.util.Random;

/**
 * Improvement of basic merge sort algorithm
 *
 * @author Evan
 * @version 1.0 2018/5/8 15:50
 */
public class Ex11 {

    public static void merge3(Comparable[] src,Comparable[] dest, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dest[k] = src[j++];
            } else if (j > hi) {
                dest[k] = src[i++];
            } else if (MergeSort.less(src[j], src[i])) {
                dest[k] = src[j++];
            } else {
                dest[k] = src[i++];
            }
        }
    }

    public static void sort(Comparable[] src, Comparable[] dest, int lo, int hi) {
        if (hi - lo < 15) {
            // Recursion finished
            insert.insertSort_OPT2(dest, lo, hi);
            return;
        }
        // calculate middle index
        int mid = lo + (hi - lo) / 2;
        // sort each left part
        sort(dest, src, lo, mid);
        // sort each right part
        sort(dest, src, mid + 1, hi);
        // if a[mid] less or equal with a[mid + 1]
        // needn't call merge method to sort sorted order
        if (src[mid].compareTo(src[mid + 1]) > 0) {
            merge3(src, dest, lo, mid, hi);
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        System.arraycopy(a, 0, aux, 0, a.length);
        sort(aux, a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] num = new Integer[50];
        Random random = new Random();
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(100);
        }
        sort(num);
        for (var i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


/**
 * Reference to insert sort
 *
 * @author Landy
 */
class insert {
    public static boolean less(Comparable v, Comparable w) {
        // 返回v是否小于w
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        // 交换两个元素
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int binarySearch(Comparable[] a, int start, int end, Comparable src) {
        while (start < end) {
            int mid = (start + end) / 2;
            Comparable mid_data = a[mid];
            if (less(src, mid_data)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void insertSort_OPT2(Comparable[] a,int start, int end) {
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