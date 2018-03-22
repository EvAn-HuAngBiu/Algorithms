import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Ex04_6_7 {
    public static void main(String[] args) {
        String string1 = "hello", string2 = string1;
        string1 = "world";
        StdOut.println(string1 + "\n" + string2);
        circularRotation("ACTGACG","TGACGAC");
        StdOut.println(mystery("ABCDE"));

        //Binary Search
        StdOut.print("N: ");
        int N = StdIn.readInt();
        int[] list = new int[N];
        System.out.print("list: ");
        for (int i = 0; i < N; i++) {
            list[i] = StdIn.readInt();
        }
        Arrays.sort(list);
        System.out.print("key: ");
        int key = StdIn.readInt();
        Counter counter = new Counter("keys");
        StdOut.println(binarySearch(key, list, counter));
        StdOut.println(counter);
    }

    private static void circularRotation(String source, String target) {
        //Circular rotation
        if(source.length() == target.length() &&
                source.concat(source).contains(target))
            StdOut.println("It is a circular rotation");
        else
            StdOut.println("It is not a circular rotation");
    }

    private static String mystery(String s) {
        //Reserve string
        int N = s.length();
        if(N <= 1) return s;
        String a = s.substring(0, N/2),
                b = s.substring(N/2, N);
        return mystery(b) + mystery(a);
    }

    private static int binarySearch(int key, int[] list, Counter counter) {
        return binarySearch(key, list, 0, list.length - 1, counter);
    }

    private static int binarySearch(int key, int[] list,int lo,
                                    int hi, Counter counter) {
        if(lo > hi) return -1;
        counter.increment();
        int mid = lo + (hi - lo) / 2;
        if(key < list[mid])
            return binarySearch(key, list, lo, mid - 1, counter);
        else if(key > list[mid])
            return binarySearch(key, list, mid + 1, hi, counter);
        else return mid;
    }
}
