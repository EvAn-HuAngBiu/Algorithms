/**
 * @author Evan
 * @version 1.0 2018/5/4 19:21
 */
public class Ex24 {
    /**
     * 带哨兵的插入排序
     * @date 2018/5/4
     * @param arr 待排序的可比较数组
     * */
    public static void insertSortWithSentry(Comparable[] arr) {
        // 数组arr的0号元素为哨兵位不参与排序
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            arr[0] = arr[i];
            int j = i;
            while (JuniorSort.less(arr[0], arr[j - 1])) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = arr[0];
        }
    }

    public static void main(String[] args) {
        Integer[] num = {0, 2, 7, 1, 6, 13, 9, 4, 8, 1};
        insertSortWithSentry(num);
        for (int i = 1; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
        System.out.println();


    }
}
