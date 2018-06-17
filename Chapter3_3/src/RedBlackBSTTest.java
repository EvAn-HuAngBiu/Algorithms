
/**
 * @author Evan
 * @date 10/06/2018 17:01
 */
public class RedBlackBSTTest {
    public static void main(String[] args) {
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        bst.put("A", 1);
        bst.put("C", 1);
        bst.put("E", 1);
        bst.put("H", 1);
        bst.put("L", 1);
        bst.put("M", 1);
        bst.put("P", 1);
        bst.put("R", 1);
        bst.put("S", 1);
        bst.put("X", 1);

        for (var t : bst.keys()) {
            System.out.print(t);
        }
        System.out.println();
        System.out.println("Minimum Element : " + bst.min());
        System.out.println("Maximum Element : " + bst.max());
        System.out.println("Is 2-3 Tree: " + bst.is23());
        System.out.println("Is Balanced Tree: " + bst.isBalance());
        bst.deleteMin();
        for (var t : bst.keys()) {
            System.out.print(t);
        }
    }
}
