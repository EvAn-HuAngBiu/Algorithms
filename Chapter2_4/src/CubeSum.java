import com.sun.jdi.PathSearchingVirtualMachine;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Evan
 * @version 1.0 19/05/2018 23:16
 */
public class CubeSum implements Comparable<CubeSum> {
    private int sum;
    private int i;
    private int j;

    public CubeSum(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = i * i * i + j * j * j;
    }

    @Override
    public int compareTo(CubeSum that) {
        return Integer.compare(this.sum, that.sum);
    }

    @Override
    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        var pq = new MinPQ<CubeSum>(n + 1);
        for (int i = 0; i <= n; i++) {
            pq.insert(new CubeSum(i, 0));
        }

        while (!pq.isEmpty()) {
            var s = pq.delMin();
            StdOut.println(s);
            if (s.j < n) {
                pq.insert(new CubeSum(s.i, s.j + 1));
            }
        }
    }
}
