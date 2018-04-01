import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex04 {

    public static boolean Parentheses(String arg) {
        //内部栈将实例化LinkedListStack
        LinkedListStack<String> stack = new LinkedListStack<>();
        for(int i = 0; i < arg.length(); i++) {
            String elem = String.valueOf(arg.charAt(i));
            if("([{".contains(elem))
                stack.push(String.valueOf(elem));
            else if(")]}".contains(elem)) {
                String popitem = stack.pop();
                switch (popitem) {
                    case "(": if(elem.equals(")")) continue; else return false;
                    case "[": if(elem.equals("]")) continue; else return false;
                    case "{": if(elem.equals("}")) continue; else return false;
                    default: return false;
                }
                }
            else return false;
            }
            return true;
        }


    public static void main(String[] args) {
        StdOut.println(Parentheses("[()]{}{[()()]()}"));
    }
}
