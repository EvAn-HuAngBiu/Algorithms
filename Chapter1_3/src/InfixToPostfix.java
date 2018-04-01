import edu.princeton.cs.algs4.StdOut;

public class InfixToPostfix {

    private static String toPostfix(String expr) {
        LinkedListStack<String> opers = new LinkedListStack<>(),
                vals = new LinkedListStack<>();
        for(int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            switch(c) {
                case '(': continue;
                case ')': vals.push(opers.pop()); break;
                case '+':
                case '-':
                case '*':
                case '/': {
                    //如果下一个运算符优先级高于或等于栈顶运算符优先级，则入栈
                    //否则将栈顶元素弹出并压入操作数栈，将下一个运算符入栈
                    if(opers.isEmpty()) { opers.push(String.valueOf(c)); break; }
                    else if(!isPriorOrEqualOperator(c, opers.getTop().charAt(0)))
                        vals.push(opers.pop());
                    opers.push(String.valueOf(c));
                    break;
                }
                default: if(isValid(String.valueOf(c))) vals.push(String.valueOf(c));
                break;
            }
        }
        //将剩余的操作符压入数据栈
        while(opers.size() > 0)
            vals.push(opers.pop());
        //vals栈内为逆波兰式的倒序，反转栈元素
        opers = new LinkedListStack<>();
        while(!vals.isEmpty())
            opers.push(vals.pop());
        //输出结果
        String result = "";
        for(String i : opers) result += i;
        return result;
    }

    private static boolean isPriorOrEqualOperator(char cmp, char src) {
        //cmp为待比较运算符, src为源运算符
        //判断运算符优先级
        if((cmp == '+' || cmp == '-') && (src == '*' || src == '/')) return false;
        return true;
    }

    private static boolean isValid(String s) {
        return s.matches("^[0-9a-zA-Z]+");  //使用正则表达式判断
    }

    public static void main(String[] args) {
        StdOut.println(toPostfix("(a+b)*c-(a+b)/e"));
    }
}
