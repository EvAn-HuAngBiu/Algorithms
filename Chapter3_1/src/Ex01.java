import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Evan
 * @date 27/05/2018 10:56
 */
public class Ex01 {
    public static void main(String[] args) {
        var st = new BinarySearchST<String, Double>(20);
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);

        int n = 0;
        double total = 0.0;
        for (; !StdIn.isEmpty(); n++) {
            String grade = StdIn.readString();
            total += st.get(grade);
        }
        StdOut.println("GPA: " + total / n);
    }
}
