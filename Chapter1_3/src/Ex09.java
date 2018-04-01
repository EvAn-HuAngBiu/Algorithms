import edu.princeton.cs.algs4.StdOut;

public class Ex09 {

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private static boolean isDigit(String s) {
        return s.matches("^[0-9]+");  //使用正则表达式判断
    }

    private static String complement(String s) {
        LinkedListStack<String> oper = new LinkedListStack<>(), //存储运算符的栈
                vals = new LinkedListStack<>(); //存储操作数的栈
        for(int i = 0; i < s.length(); i++) {
            String temp = String.valueOf(s.charAt(i));
            if(temp.equals(")")) {
                String v = vals.pop();
                vals.push("(" + v + oper.pop() + vals.pop() + ")");
            }
            else if(isOperator(temp)) {
                oper.push(temp);
            }
            else if(isDigit(temp)) {
                vals.push(temp);
            }
        }
        String result = "";
        while(oper.size() != 0 || vals.size() != 0) {
            String temp = vals.pop();
            if(vals.size() == 0) return temp;
            result += vals.pop() + oper.pop() + temp;
        }
        return result;
    }

    public static void main(String[] args) {
        String expr = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        StdOut.println(complement(expr));
    }
}
