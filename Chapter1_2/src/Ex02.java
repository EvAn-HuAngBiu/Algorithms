import edu.princeton.cs.introcs.*;
import edu.princeton.cs.algs4.Interval1D;

public class Ex02 {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[N];
        for(int i = 0; i < N; i++)
            intervals[i] = new Interval1D(StdIn.readDouble(), StdIn.readDouble());
        if(N > 2) {
            for(int i = 0; i < N - 1; i++) {
                for(int j = i + 1; j < N; j++)
                    if(intervals[i].intersects(intervals[j]))
                        StdOut.printf("Line {0} intersects line {1}", i, j);
            }
        }

    }
}
