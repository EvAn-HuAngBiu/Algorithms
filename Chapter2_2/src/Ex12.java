/**
 * @author Evan
 * @version 1.0 2018/5/8 17:33
 */
public class Ex12 {

    private final static int M = 10;

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N / M; i++) {
            insert.insertSort_OPT2(a, i * M, i * M + 9);
        }
        QuickMerge.sort(a);
    }

    public static void main(String[] args) {
        Integer[] num = {20, 18, 16, 12, 14, 8, 4, 10, 6, 2, 0, 9, 13, 17, 7, 19, 3, 5, 11, 1};
        sort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
