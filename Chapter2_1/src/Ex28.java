import edu.princeton.cs.algs4.StdOut;
import java.util.Random;

/**
 * 验证主键仅可能是两个值得时候，插入排序和选择排序的性能
 *
 * @author Evan
 * @version 1.0 2018/5/4 19:43
 */
public class Ex28 {
    public static double timeRandomInput(String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        double total = 0.0;
        var a = new Integer[N];
        Random random = new Random();
        for (int t = 0; t < T; t++) {
            // 进行一次测试(生成一个数组并排序)
            for (int i = 0; i < N; i++) {
                // 随机数只可能是0和1
                a[i] = random.nextInt(2);
            }
            total += SortCompare.time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        // 测试数组初始化
        int N = 65536, T = 1;    // 数组长度2048, 测试次数1

        // 插入排序
        String alg1 = "Insertion";
        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        StdOut.printf("Insertion sort: %.4f s\n", t1);

        //选择排序
        String alg2 = "Selection";
        double t2 = SortCompare.timeRandomInput(alg2, N, T);
        StdOut.printf("Selection sort: %.4f s\n", t2);
    }
}
