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
            case "Merge": MergeSort.sort(a); break;
            case "MergeBU": MergeSort.sortBU(a); break;
            case "QuickMerge": QuickMerge.sort(a); break;
            case "QuickMergeBU": QuickMerge.sortBU(a); break;
            case "Improvement": Ex11.sort(a); break;
            case "LessSquare": Ex12.sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        double total = 0.0;
        var a = new Double[N];
        //for (int t = 0; t < T; t++) {
            // 进行一次测试(生成一个数组并排序)
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        //}
        return total;
    }

    public static void main(String[] args) {
        int N = 10000;    // 数组长度
        int T = 1000;    // 数组数量

        String alg1 = "Merge";
        String alg2 = "MergeBU";
        String alg3 = "QuickMerge";
        String alg4 = "QuickMergeBU";
        String alg5 = "Improvement";
        String alg6 = "LessSquare"
                ;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);
        double t4 = timeRandomInput(alg4, N, T);
        double t5 = timeRandomInput(alg5, N, T);
        double t6 = timeRandomInput(alg6, N, T);

        StdOut.printf("Merge: %.4f\n", t1);
        StdOut.printf("MergeBU: %.4f\n", t2);
        StdOut.printf("QuickMerge: %.4f\n", t3);
        StdOut.printf("QuickMergeBU: %.4f\n", t4);
        StdOut.printf("Improvement: %.4f\n", t5);
        StdOut.printf("LessSquare: %.4f\n", t6);

    }
}
