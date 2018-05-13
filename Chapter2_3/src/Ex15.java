import edu.princeton.cs.algs4.Quick;

/**
 * Matching screw and nut problem
 *
 * Upper letters represent nuts while lower case represent screw
 *
 * Using Quick-Sort method can line a list
 *
 * When compare two letters, I stipulate the lower case and upper case of
 * a letter represent same number, like 'A' and 'a', I think they are equal
 *
 * Problem: The order would be a little strange due to my Quick-Sort algorithm
 * can't recognize the upper case and lower case well. It may print like 'AabBcCDd'
 * rather than 'AaBbCcDd' or 'aAbBcCdD'. And I compare two screw or two nuts, it's illegal.
 *
 * @author Evan
 * @version 1.0 2018/5/12 9:19
 */
public class Ex15 {
    private static boolean cmp(Character v, Character w) {
        // compare two elements
        // if one is upper while others is letter
        // then compare their corresponding values
        Character v_trans = v, w_trans = w;
        if (v >= 'a' && v <= 'z')
            v_trans = (char)(v - 32);
        if (w >= 'a' && w <= 'z')
            w_trans = (char)(w - 32);
        return v_trans < w_trans;
    }

    private static void exch(Character[] a, int i, int j) {
        // exchange two elements
        var t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort1(Character[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort1(a, lo, j - 1);
        sort1(a, j + 1, hi);
    }
    private static int partition(Character[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        // v is the partition value
        var v = a[lo];
        while (true) {
            // find the first element which is greater than v from the left
            while (cmp(a[++i], v) && i < hi) ;
            // find the first element which is less than v from the right
            while (cmp(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            QuickSort.exch(a, i, j);
        }
        QuickSort.exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Character[] c = {'d', 'A', 'a', 'C', 'B', 'e', 'D', 'E', 'f', 'b', 'c', 'F'};
        sort1(c, 0, c.length - 1);
        for (var i : c) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
