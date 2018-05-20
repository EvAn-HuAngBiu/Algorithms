import java.util.Scanner;

/**
 * @author Evan
 * @version 1.0 20/05/2018 08:01
 */
public class Ex28 implements Comparable<Ex28> {
    private int x;
    private int y;
    private int z;
    private double distance;

    public Ex28(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public int compareTo(Ex28 that) {
        return Double.compare(this.distance, that.distance);
    }

    @Override
    public String toString() {
        return String.valueOf(distance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();    // total elements
        int m = scanner.nextInt();   // top m elements
        MinPQ<Ex28> pq = new MinPQ<>(n - m);
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt(),
                    y = scanner.nextInt(),
                    z = scanner.nextInt();
            Ex28 s = new Ex28(x, y, z);
            if (pq.size() == n - m && s.compareTo(pq.min()) <= 0) {
                System.out.println(s);
            } else if (pq.size() == n - m && s.compareTo(pq.min()) > 0) {
                System.out.println(pq.delMin());
                pq.insert(s);
            } else {
                pq.insert(s);
            }
        }
    }
}
