import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public void push(Item item) {
        if(N == a.length) this.resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        assert N - 1 >= 0 : "Too less elements";
        Item temp = a[--N];
        a[N] = null;
        if(N <= a.length / 4) this.resize(a.length / 2);
        return temp;
    }

    public boolean isEmpty() {return N == 0;}

    public int size() {return N;}

    public void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        for(int i = 0; i < (N > size ? size : N); i++)
            temp[i] = a[i];
        a = temp;
    }

    public Iterator<Item> iterator() { return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() {}
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) s.push(item);
            else if(!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
