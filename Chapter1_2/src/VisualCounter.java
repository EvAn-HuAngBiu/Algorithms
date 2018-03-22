import edu.princeton.cs.algs4.*;

public class VisualCounter {
    private int N;
    private int max;
    private int currentTime = 0;
    private int currentAbs = 0;

    public VisualCounter(int N, int max) {
        this.N = N;
        this.max = max;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(0, 0);
    }

    public void increase() {
        if(currentTime + 1 > N) {
            StdOut.println("Too much operation");
            return;
        }
        if(currentAbs + 1 > max) {
            StdOut.println("Too big number");
            return;
        }
        currentTime++;
        currentAbs++;
        StdDraw.point(currentTime, currentAbs);
    }

    public void decrease() {
        if(currentTime + 1 > N) {
            StdOut.println("Too much operation");
            return;
        }
        if(currentAbs - 1 > -max) {
            StdOut.println("Too small number");
            return;
        }
        currentTime++;
        currentAbs--;
    }

    public int getCount() {
        return currentTime;
    }

    public static void main(String[] args) {
        VisualCounter counter = new VisualCounter(20, 5);
        for (int i = 0; i < 10; i++) {
            counter.increase();
        }
        for (int i = 0; i < 15; i++) {
            counter.decrease();
        }
        for (int i = 0; i < 10; i++) {
            counter.increase();
        }
        StdOut.println(counter.getCount());
    }
}
