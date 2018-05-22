import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Evan
 * @version 1.0 21/05/18 08:54
 */
public class Ex02 {
    public static void main(String[] args) {
        String[] strs = StdIn.readAllStrings();
        int N  = strs.length;

        for (int i = 0; i < N; i++) {
            String first = strs[i];
            for (int j = i + 1; j < N; j++) {
                String second = strs[j];
                for (int k = 0; k < N; k++) {
                    if ((first + second).equals(strs[k]) ||
                            (second + first).equals(strs[k])) {
                        StdOut.print(strs[k] + " ");
                    }
                }
            }
        }
        StdOut.println();
    }
}
