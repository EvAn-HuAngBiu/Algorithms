/**
 * @author Evan
 * @version 1.0 2018/5/8 8:53
 */
public class QuickMerge {

    private static Comparable[] aux;
    private final static int CUTOFF = 15;

    public static void merge2(Comparable[] a, int lo, int mid, int hi) {
        // sequential copy front half
        System.arraycopy(a, 0, aux, 0, mid + 1);
        // reverse copy second half
        for (int i = hi, j = mid + 1; i > mid; i--, j++) {
            aux[j] = a[i];
        }
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (MergeSort.less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo < 15) {
            // Recursion finished
            insert.insertSort_OPT2(a, lo, hi);
            return;
        }
        // calculate middle index
        int mid = lo + (hi - lo) / 2;
        // sort each left part
        sort(a, lo, mid);
        // sort each right part
        sort(a, mid + 1, hi);
        // if a[mid] less or equal with a[mid + 1]
        // needn't call merge method to sort sorted order
        if (a[mid].compareTo(a[mid + 1]) <= 0) {
            return;
        }
        // sort total array
        merge2(a, lo, mid, hi);
    }

    public static void sortBU(Comparable[] a) {
        int N = a.length;
        // create assist array
        var aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge2(a, lo, lo + sz -1, Math.min(lo+sz+sz-1, N - 1));
            }
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        // if call merge2 directly, you need to make sure that
        // the two parts are partially sorted
        // otherwise, it will make errors
        Integer[] num = new Integer[]{2, 4, 5, 7, 1, 3, 6};
        aux = new Integer[num.length];
        merge2(num, 0, 3, 6);
        for (var i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // use sort
        num = new Integer[]{2, 7, 5, 4, 3, 6, 1};
        sort(num);
        for (var i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // use sortBU
        num = new Integer[]{2, 7, 5, 4, 3, 6, 1};
        sortBU(num);
        for (var i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

