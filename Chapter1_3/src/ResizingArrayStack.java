import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a;   //栈元素
    private int N = 0;  //栈元素数量

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    public void resize(int size) {
        //将栈大小调整为size
        Item[] temp = (Item[]) new Object[size];
        for(int i = 0; i < temp.length; i++)
            temp[i] = a[i];
        a = temp;
    }
    public void push(Item item) {
        if(N == a.length) resize(2 * N);
        a[N++] = item;
    }
    public Item pop() {
        Item item = a[--N];
        a[N] = null;    //此时N已经递减，指向尾元素
        if(N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }
    public Iterator<Item> iterator() { return new ReverseArrayIterator(); }
    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[i--]; }
        public void remove() {}
    }

    public static void main(String[] args) {

    }
}
