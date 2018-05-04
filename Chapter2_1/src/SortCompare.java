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
            case "Insertion":
                JuniorSort.insertSort(a);
                break;
            case "Insertion_OPT1":
                JuniorSort.insertSort_OPT1(a);
                break;
            case "Insertion_OPT2":
                JuniorSort.insertSort_OPT2(a);
                break;
            case "Selection":
                JuniorSort.selectSort(a);
                break;
            case "Selection_OPT1":
                JuniorSort.selectSort_OPT1(a);
                break;
            case "Bubble":
                JuniorSort.bubbleSort(a);
                break;
            case "Shell":
                JuniorSort.shellSort(a);
                break;
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput (String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        double total = 0.0;
        var a = new Double[N];
        for(int t = 0; t < T; t++) {
            //进行一次测试(生成一个数组并排序)
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Bubble";
        String alg4 = "Shell";
        String alg5 = "Insertion_OPT1";
        String alg6 = "Selection_OPT1";
        String alg7 = "Insertion_OPT2";

        int N = 2000;    // 数组长度
        int T = 2000;    // 数组数量
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);
        double t4 = timeRandomInput(alg4, N, T);
        double t5 = timeRandomInput(alg5, N, T);
        double t6 = timeRandomInput(alg6, N, T);
        double t7 = timeRandomInput(alg7, N, T);


        StdOut.printf("Insertion : %.4f s\n", t1);
        StdOut.printf("Selection : %.4f s\n", t2);
        StdOut.printf("Bubble : %.4f s\n", t3);
        StdOut.printf("Shell : %.4f s\n", t4);
        StdOut.printf("Insertion with optimization 1 : %.4f s\n", t5);
        StdOut.printf("Selection with optimization 1 : %.4f s\n", t6);
        StdOut.printf("Insertion with optimization 2 : %.4f s\n", t7);

    }
}
