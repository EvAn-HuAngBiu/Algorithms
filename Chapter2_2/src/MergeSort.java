import java.io.*;
import java.util.Random;

import static java.lang.System.out;

/**
 * @author Evan
 * @version 1.0 2018/5/5 21:25
 */
public class MergeSort {

    public static boolean less(Comparable v, Comparable w) {
        // return if v is less than w
        return v.compareTo(w) < 0;
    }

    /**
     * Merge sort version 1 (Original version)
     * It will merge and sort array between a[lo..mid] and a[mid+1..hi]
     *
     * @param a Result array and it includes two sorted arrays
     * @param aux Required assistant array
     * @param lo The first array begin position
     * @param mid The first array end position
     * @param hi The seconde array end position
     * */
    public static void merge1(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // copy source array to assist array
        System.arraycopy(a, 0, aux, 0, a.length);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // Every element in array between lo to mid is merged
                // Only need to append other elements in second array
                a[k] = aux[j++];
            } else if (j > hi) {
                // Same, every element in first array is merged
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        var aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * sort method, this method accept an unsorted array
     * use recursion to sort every child element
     *
     * @param a Array to sort
     * @param lo begin position
     * @param hi end position
     */
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            // Recursion finished
            return;
        }
        // calculate middle index
        int mid = lo + (hi - lo) / 2;
        // sort each left part
        sort(a, aux, lo, mid);
        // sort each right part
        sort(a, aux, mid + 1, hi);
        // if a[mid] less or equal with a[mid + 1]
        // needn't call merge method to sort sorted order
        if (a[mid].compareTo(a[mid + 1]) <= 0) {
            return;
        }
        // sort total array
        merge1(a, aux, lo, mid, hi);
    }

    public static void sortBU(Comparable[] a) {
        int N = a.length;
        // create assist array
        var aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge1(a, aux, lo, lo + sz -1, Math.min(lo+sz+sz-1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        // directly merge
        Integer[] num = new Integer[]{2, 7, 5, 4, 3, 6, 1};
        var aux = new Comparable[num.length];
        merge1(num, aux, 0, 3, 6);
        for (int i : num) {
            out.print(i + " ");
        }
        out.println();

        // unsorted array
        num = new Integer[]{2, 7, 5, 4, 3, 6, 1};
        sort(num);
        for (int i : num) {
            out.print(i + " ");
        }
        out.println();

        num = new Integer[]{2, 7, 5, 4, 3, 6, 1};
        sortBU(num);
        for (int i : num) {
            out.print(i + " ");
        }
        out.println();
    }
}
