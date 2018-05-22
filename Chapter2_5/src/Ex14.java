import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author Evan
 * @version 1.0 21/05/18 11:23
 */
public class Ex14 {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Domain[] domains = new Domain[n];
        String[] urls = StdIn.readAllStrings();
        for (int i = 0; i < n; i++) {
            domains[i] = new Domain(urls[i]);
        }
        Arrays.sort(domains);
        for (var i : domains) {
            StdOut.println(i);
        }
    }
}

class Domain implements Comparable<Domain> {
    private String[] field;
    private int n;

    public Domain(String url) {
        this.field = url.split("\\.");
        n = field.length;
    }

    @Override
    public String toString() {
        if (n == 0) {
            return null;
        }
        String s = field[0];
        for (int i = 1; i < n; i++) {
            s = field[i] + "." + s;
        }
        return s;
    }

    @Override
    public int compareTo(Domain that) {
        int len = Math.min(this.n, that.n);
        for (int i = len - 1; i >= 0; i--) {
            String s = this.field[i], t = that.field[i];
            int cmp = s.compareTo(t);
            if (cmp < 0) {
                return -1;
            } else if (cmp > 0) {
                return 1;
            }
        }
        return this.n - that.n;
    }
}
