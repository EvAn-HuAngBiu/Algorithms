import java.util.Stack;

/**
 * Non-recursive Quick-Sort
 *
 * @author Evan
 * @version 1.0 2018/5/12 22:47
 */
public class Ex20 {

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // stack save the calling order
        var stack = new Stack<Integer>();
        stack.push(lo);
        stack.push(hi);
        while (!stack.empty()) {
            int right = stack.pop();
            int left = stack.pop();
            if (left < right) {
                int j = QuickSort.partition(a, left, right);
                stack.push(left);
                stack.push(j - 1);

                stack.push(j + 1);
                stack.push(right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {4, 5, 1, 7, 2, 3, 0, 6};
        sort(nums, 0, nums.length - 1);
        for (var i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
