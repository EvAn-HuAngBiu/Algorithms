/**
 * Standard Binary Search Tree
 *
 * @author Evan
 * @date 14/06/2018 15:26
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Save root node
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;      // left child
        Node right;     // right child
        Node parent;    // parent node
        boolean color;

        Node(Key key, Value value, boolean color, Node left, Node right, Node parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private void rotateLeft(Node x) {
        // 左旋
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            // 如果存在左结点, 则设置左结点的父节点值
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            // 如果是根结点, 直接设置旋转后的结点为根结点
            this.root = y;
        } else if (x.parent.left == x) {
            // 若x是原来的左结点
            x.parent.left = y;
        } else {
            // 若x是原来的右结点
            x.parent.right = y;
        }
        // x挂到y的左边
        y.left = x;
        // 设置x的父节点
        x.parent = y;
    }

    private void rotateRight(Node x) {
        // 右旋，同左旋
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void setRed(Node x) {
        // 将结点涂红
        if (x != null) {
            x.color = RED;
        }
    }

    private void setBlack(Node x) {
        // 将结点涂黑
        if (x != null) {
            x.color = BLACK;
        }
    }

    private boolean isRed(Node x) {
        // 判断是否为红结点
        return (x != null && x.color);
    }

    private void transplant(Node u, Node v) {
        // 将结点v替换结点u的位置
        if (u.parent == null) {
            // 如果u是根结点, 设置v
            this.root = v;
        } else if (u == u.parent.left) {
            // 如果u是左子节点, 设置v
            u.parent.left = v;
        } else {
            // 如果u是右子节点, 设置v
            u.parent.right = v;
        }
        if (v != null) {
            // 如果v非空, 设置v的父节点
            v.parent = u.parent;
        }
    }

    public void insert(Key key, Value value) {
        // 保存父节点
        Node y = null;
        // 插入结点位置
        Node x = this.root;
        // 寻找插入位置
        while (x != null) {
            y = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                // 如果找到则退出
                break;
            }
        }
        if (x != null) {
            // 如果x为空即不在叶子结点, 那么说明key存在, 更新值即可
            x.value = value;
            return;
        }
        // 否则, 创建新节点, 新节点为红结点
        Node z = new Node(key, value, RED, null, null, null);
        // 设置父节点
        z.parent = y;
        if (y == null) {
            // 若为根结点
            this.root = z;
        } else if (key.compareTo(y.key) < 0) {
            // z在左侧
            y.left = z;
        } else {
            // z在右侧
            y.right = z;
        }
        // 调整
        insertFixUp(z);
    }

    private void insertFixUp(Node z) {
        // 只当插入结点的父节点为红结点时需要调整, 若为黑结点则不需要调整
        // 黑结点下方插入红结点不影响性质
        while (isRed(z.parent)) {
            // 当插入结点的父节点位于祖父节点左侧时
            if (z.parent == z.parent.parent.left) {
                // y保存插入结点z的叔结点
                Node y = z.parent.parent.right;
                if (isRed(y)) {
                    // 如果叔结点为红, 只需要将z的父节点和叔结点涂黑
                    // z上移到祖父结点即可
                    setBlack(y);
                    setBlack(z.parent);
                    setRed(z.parent.parent);
                    z = z.parent.parent;
                } else {
                    // 若叔结点为黑, 则需要判断z的祖父节点、z的父节点、z结点是否在一条线上
                    if (z == z.parent.right) {
                        // 若不是, 则转成一条线
                        // 以z的父节点为轴左旋即可使三者共线
                        z = z.parent;
                        rotateLeft(z);
                    }
                    // 共线时, 交换父节点和祖父结点颜色并以祖父节点为轴右旋即可
                    setBlack(z.parent);
                    setRed(z.parent.parent);
                    rotateRight(z.parent.parent);
                }
                // 当插入结点的父节点位于祖父节点的右侧时同理, 将左改成右, 左旋改右旋即可
            } else {
                Node y = z.parent.parent.left;
                if (isRed(y)) {
                    setBlack(y);
                    setBlack(z.parent);
                    setRed(z.parent.parent);
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rotateRight(z);
                    }
                    z = z.right;
                    setBlack(z.parent);
                    setRed(z.parent.parent);
                    rotateLeft(z.parent.parent);
                }
            }
        }
        setBlack(this.root);
        root.parent = null;
    }

    public void delete(Key key) {
        // 删除操作
        // z寻找要删除的结点
        Node z = root;
        while (z != null) {
            int cmp = key.compareTo(z.key);
            if (cmp < 0) {
                z = z.left;
            } else if (cmp > 0) {
                z = z.right;
            } else {
                break;
            }
        }
        if (z == null) {
            // 找不到该节点, 无法删除
            return;
        }
        // y始终指示被删除的结点
        Node y = z;
        // x指示被删除结点的替换结点
        Node x = null;
        // yOriginalColor保存被删除结点的颜色
        // 后续根据这个颜色是否为黑来判断是否需要调整, 为红则不需要调整
        boolean yOriginalColor = y.color;
        if (z.left == null) {
            // 只存在右子节点或无子节点
            x = z.right;
            transplant(z, x);
        } else if (z.right == null) {
            // 只存在左子节点
            x = z.left;
            transplant(z, x);
        } else {
            // 同时存在左右子节点, 用后继结点替换
            // 寻找后继结点, 此时后继结点变成要被删除的结点
             y = y.right;
            while (y.left != null) {
                y = y.left;
            }
            // 修改颜色
            yOriginalColor = y.color;
            // 修改替换节点
            x = y.right;
            if (y.parent != z) {
                // 若后继结点的父节点不是原来要被删除的结点
                // 用替换节点x替换后继结点y的位置
                transplant(y, y.right);
                // 后继结点y替换被删除结点z的位置
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK) {
            // 若删除了一个黑结点则需要调整
            deleteFixUp(x, z.parent);
        }
        y = null;
    }

    private void deleteFixUp(Node x, Node p) {
        // x为要调整的结点, p为被删除结点的父节点
        // p的存在只是为了防止x为NULL时会导致x.parent无法判断的问题
        // 四种情况中, 第二种情况修改了x, 所以只在第二种情况中修改p即可

        // 若x不是根结点, 且x为黑结点时需要调整
        while (x != root && !isRed(x)) {
            // 当x为左子节点时
            if (x == p.left) {
                // w保存x的兄弟结点
                Node w = p.right;
                if (isRed(w)) {
                    // Case1: x的兄弟结点为红色的
                    setBlack(w);
                    setRed(p);
                    rotateLeft(p);
                    w = p.right;
                }
                if (!isRed(w.left) && !isRed(w.right)) {
                    // Case2: x的兄弟结点为黑色的且两个子节点也是黑色的
                    setRed(w);
                    x = p;
                    p = x.parent;
                } else {
                    if (!isRed(w.right)) {
                        // Case3: x的兄弟结点w时黑色的, w的左孩子时红色的, w的右孩子时黑色的
                        setBlack(w.right);
                        setRed(w);
                        rotateRight(w);
                        w = w.parent;
                    }
                    // Case4: x的兄弟节点w时黑色的, w的右孩子时红色的
                    w.color = w.parent.color;
                    setBlack(w.parent);
                    setBlack(w.right);
                    rotateLeft(p);
                    x = root;
                }
                // 当x为右子节点时, 同理, 左改右即可, 左旋改右旋即可
            } else {
                Node w = p.left;
                if (isRed(w)) {
                    setBlack(w);
                    setRed(p);
                    rotateRight(p);
                    w = w.parent.left;
                }
                if (!isRed(w.left) && !isRed(w.right)) {
                    setRed(w);
                    x = p;
                    p = x.parent;
                } else {
                    if (!isRed(w.left)) {
                        setBlack(w.left);
                        setRed(w);
                        rotateLeft(w);
                        w = w.parent;
                    }
                    w.color = w.parent.color;
                    setBlack(w.parent);
                    setBlack(w.left);
                    rotateRight(p);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }
}
