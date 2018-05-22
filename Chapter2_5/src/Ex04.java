import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * @author Evan
 * @version 1.0 21/05/18 09:23
 */
public class Ex04 {
    /**
     * Delete repeated element from array by using Set
     * <p>
     * Set is a Non-repeatable container
     * If you add a existing element to a set, it will not work
     *
     * @param a target array, it will not be changed
     * @return array without duplicate elements
     */
    private static String[] dedup_Set(String[] a) {
        // Use Arrays.asList() to transform an array to a list
        Set<String> set = new HashSet<>(Arrays.asList(a));
        // Transform back to an array
        return set.toArray(new String[]{});
    }

    /**
     * Delete repeated element from array by using List
     * <p>
     * Use contain method to check if the element is existing
     *
     * @param a target array, it will not be changed
     * @return array without duplicate elements
     */
    private static String[] dedup_List(String[] a) {
        ArrayList<String> list = new ArrayList<>();
        for (var i : a) {
            if (!list.contains(i)) {
                // if the element don't exists, add
                list.add(i);
            }
        }
        return list.toArray(new String[]{});
    }

    /**
     * Delete repeated element from array by scanning
     * <p>
     * Use contain method to check if the element is existing
     *
     * @param a target array, it will not be changed
     * @return array without duplicate elements
     */
    private static String[] dedup(String[] a) {
        int count = 0,
                N = a.length;
        String[] temp = new String[N];
        for (var str : a) {
            int j = 0;
            for (; j < count; j++) {
                if (temp[j].equals(str)) {
                    break;
                }
            }
            if (j == count) {
                temp[count++] = str;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public static void main(String[] args) {
        String[] strs = StdIn.readAllStrings();
        StdOut.println(Arrays.toString(dedup_Set(strs)));
        StdOut.println(Arrays.toString(dedup_List(strs)));
        StdOut.println(Arrays.toString(dedup(strs)));
    }
}
