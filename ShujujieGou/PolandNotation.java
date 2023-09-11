package ShujujieGou;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1。1+((2+3)x4)-5 => 转成 1 2 3 + 4 x + 5 -
        //2。因为直接对str 进行操作，不方便，因此 先将“1+((2+3)x4)-5”=》中级的表达式对应的List/
        // 即"1+((2+3)x4)-5”=> ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List =》后缀表达式对应的的List
        //     即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => [1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression); //[1, +, (, (, 2, +, 3, ), x, 4, ), -, 5]
        System.out.println(infixExpression);

        List<String> strings = parseSuffixExpression(infixExpression);
        System.out.println(strings);//后缀表达式

        // (3+4)*5-6
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1，先将"3 4 +5 x 6 - => 放到ArrayList中
        // 2。将 ArrayList 传递给一个方法，配合栈 完成计算
        List<String> listString = getListString(suffixExpression);
        System.out.println(calculate(listString));

    }
    //将一个逆波兰表达式，依次将数据和运算符 放入到 ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] s = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String e: s ){
            list.add(e);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    //*
    //1)从左至右扫描，将3和4压入堆栈;
    //2)遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈;
    //3)将5入栈;
    //4)接下来是x运算符，因此弹出5和7，计算出7x5=35，将35入栈;
    //5)将6入栈;
    //6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        ////遍历 1s
        for(String item: ls) {
            //这里使用正则表达式来职出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            }  else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item){
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");//要是String

            }
        }
        return Integer.parseInt(stack.pop());
    }
    //将一个中缀表达式转化成对应的List
    public static List<String> toInfixExpression(String s){
        //定义一个List,存放内容
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历
        String str;// 作对多位数的拼接工作
        char c;//没遍历一个字符，就放入到c
        do {
            //如果c是一个符号，就放到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {//如果是一个数，还要考虑到多位数
                str = ""; //先将str 置成 “”                   1-10
                //    不能超过总字符长度！！！
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }
    //3.将得到的中缀表达式对应的List =》后缀表达式对应的的List
    //     即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => [1,2,3,+,4,*,+,5,-]
    public static List<String> parseSuffixExpression(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        //说明 s2这个栈 在整个转换过程中，没有pop操作，而且后面还会逆序输出
        //因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2

        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for (String item: ls){
            //如果是一个数，加入S2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                //  peek的方法就是弹出栈顶元素
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//!!!将（ 弹出，消除小括号
            } else  {
                //当item的优先级小于等于s1栈顶运算符，
                //将s1栈顶的运算符弹出并加入到s2中，
                //转到与s1中新的栈顶运算符相比较
                //创建一个类用来比较优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需将item压入栈
                s1.push(item);
            }
        }
        //将S1剩余的运算符依次弹出并加入到s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//因为本身添加就是在list中，无需倒序输出
    }
}
//编写一个类Operation 可以返回一个运算符 对应的优先级
class Operation{
    private static int ADD = 1;//加减乘除
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //编写一个方法，返回对应数字的优先级
    public static int getValue(String operation){
        int rel = 0;
        switch (operation){
            case "+":
                rel = ADD;
                break;
            case "-":
                rel = SUB;
                break;
            case "*":
                rel = MUL;
                break;
            case "/":
                rel = DIV;
                break;
            default:
                System.out.println("不存在这个运算符");
                break;
        }
        return rel;
    }
}