import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 比较排序的速度
 *
 * @author Landy
 * @version 1.0 2018/4/25 19:26
 */
public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        var timer = new Stopwatch();
        switch (alg) {
            case "QuickSort1":
                QuickSort.sort1(a, 0, a.length - 1);
                break;
            case "Quick3Way":
                Quick3Way.sort(a, 0, a.length - 1);
                break;
            case "ThreeSegments":
                Ex18_ThreeSegments.sort(a, 0, a.length - 1);
                break;
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        QuickSort.times = 0;
        double total = 0.0;
        var a = new Integer[N];
        for (int t = 0; t < T; t++) {
            //进行一次测试(生成一个数组并排序)
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform(2);
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "QuickSort1";
        String alg2 = "Quick3Way";
        String alg3 = "ThreeSegments";

        int N = 1000;    // 数组长度
        int T = 1000;    // 数组数量
        double t1 = timeRandomInput(alg1, N, T);
        double t1Avg = QuickSort.times / T;
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);

        StdOut.printf("QuickSort1 : %.4f s, Compare times : %.4f\n", t1, t1Avg);
        StdOut.printf("Quick3Way : %.4f s\n", t2);
        StdOut.printf("3Segments : %.4f s\n", t3);

    }
}
