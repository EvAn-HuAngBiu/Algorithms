/**
 * 初级排序算法
 *
 * @author Landy
 * @date 2018/4/24 8:09
 */

public class JuniorSort {

    private static boolean less(Comparable v, Comparable w) {
        // 返回v是否小于w
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // 交换两个元素
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int binarySearch(Comparable[] a, int start, int end, Comparable src) {
        while (start < end) {
            int mid = (start + end) / 2;
            Comparable mid_data = a[mid];
            if (less(src, mid_data)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void selectSort(Comparable[] a) {
        // 选择排序
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // 最小值索引, 每次排序后把第一个值作为最小值
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    // 如果当前索引对应的值小于最小值, 那么将其设为最小值索引
                    min = j;
                }
            }
            // 将当前的最小值置于数组头部
            exch(a, i, min);
        }
    }

    public static void selectSort_OPT1(Comparable[] a) {
        // 选择排序优化
        // 在一次遍历中同时查询最大值和最小值
        int N = a.length;
        for (int i = 0, j = N - 1; i < N / 2; i++, j--) {
            int min = i;
            int max = j;
            for (int k = i + 1; k <= j; k++) {
                if (less(a[k], a[min])) {
                    min = k;
                } else if (a[k].compareTo(a[max]) > 0) {
                    max = k;
                }
            }
            exch(a, i, min);
            exch(a, j, max);
        }
    }

    public static void insertSort(Comparable[] a) {
        // 插入排序
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void insertSort_OPT1(Comparable[] a) {
        // 经过一次优化的插入排序
        // 减少赋值次数
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = i;
            Comparable temp = a[i];
            while (j > 0 && less(temp, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    public static void insertSort_OPT2(Comparable[] a) {
        // 经过二次优化的插入排序
        // 折半插入排序
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];
            int pos = binarySearch(a, 0, i, temp);
            System.arraycopy(a, pos, a, pos + 1, i - pos);
            a[pos] = temp;
        }
    }

    public static void bubbleSort(Comparable[] a) {
        // 冒泡排序
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (less(a[j + 1], a[j])) {
                    exch(a, j, j + 1);
                }
            }
        }
    }

    public static void shellSort(Comparable[] a) {
        // 希尔排序
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            // 构建希尔增量序列
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 从第h + 1个元素开始和前h个元素比较
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        // 选择排序测试
        Integer[] num = {2, 7, 1, 6, 13, 9, 4, 8, 1};
        selectSort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 选择排序优化 测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        selectSort_OPT1(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 插入排序测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        insertSort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 冒泡排序测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        bubbleSort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 希尔排序测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        shellSort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 插入排序优化1 测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        insertSort_OPT1(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 插入排序优化2 测试
        num = new Integer[]{2, 7, 1, 6, 13, 9, 4, 8, 1};
        insertSort_OPT2(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
