import edu.princeton.cs.algs4.StdOut;

public class EvaluatePostfix {

    private static Double evaluate(String expr) {
        //这个函数不能处理非数值表达式
        //最终结果将解析为Double
        LinkedListStack<Double> vals = new LinkedListStack<>();
        for(int i = 0; i < expr.length(); i++) {
            String ele = String.valueOf(expr.charAt(i));
            if(isDigit(ele)) vals.push(Double.parseDouble(ele));
            else if(isOperator(ele)) {
                Double num1 = vals.pop();
                switch (ele) {
                    case "+": vals.push(num1 + vals.pop()); break;
                    case "-": vals.push(vals.pop() - num1); break;
                    case "*": vals.push(num1 * vals.pop()); break;
                    case "/": vals.push(vals.pop() / num1); break;
                    default: throw new IllegalArgumentException();
                }
            }
            else throw new IllegalArgumentException();
        }
        return vals.getTop();
    }

    private static boolean isDigit(String s) {
        return s.matches("^[0-9]+");  //使用正则表达式判断
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static void main(String[] args) {
        StdOut.println(evaluate("12+3*12+4/-"));
    }
}
