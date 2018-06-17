/**
 * @author Evan
 * @date 16/06/2018 21:41
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
        rbt.insert(11, 1);
        rbt.insert(2, 1);
        rbt.insert(14, 1);
        rbt.insert(1, 1);
        rbt.insert(7, 1);
        rbt.insert(15, 1);
        rbt.insert(5, 1);
        rbt.insert(8, 1);
        rbt.insert(4, 1);
        rbt.delete(4);
        System.out.println();
    }
}
