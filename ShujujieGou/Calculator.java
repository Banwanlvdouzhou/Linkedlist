package ShujujieGou;

public class Calculator {
    public static void main(String[] args) {
        //
        String expression = "70+2*6-4";
        //一个数栈，一个符号栈
        Astack2 numStack = new Astack2(10);
        Astack2 operStack = new Astack2(10);
        //用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//每次扫描到的保存在ch里
        String keepNum = "";//用于拼接多位数
        while (true){
            //扫描expression，每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //根据ch类别不同，入不同的栈
            if (operStack.isOper(ch)) {//是不是运算符
                //是否栈空
                if (operStack.isEmpty()) {//空直接入栈
                    operStack.push(ch);
                }else {//不为空要进行比较
                    //如果当前操作符小于等于栈中的操作符，就要拿出两个数字
                    //算出结果进入数栈，符号也要入栈
                    if (operStack.priorty(ch) <= operStack.priorty(operStack.peak())) {
                        //与栈顶的相比
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //计算结果入数栈
                        numStack.push(res);
                        //符号也要入栈
                        operStack.push(ch);
                    }else {
                        //如果当前操作符大于栈中的操作符，直接入栈
                        operStack.push(ch);
                    }
                }
            }else {//如果是数字，则直接入栈
                //numStack.push(Integer.parseInt(String.valueOf(ch)));
                //方法一将char->String->int
                //numStack.push(ch - 48);
                //方法二：‘0’的ASCLL码是48，计算先自动强转换为int
                //numStack.push(ch - '0');


                //处理多位数
                keepNum+=ch;
                //如果已经是最后一位则直接入栈
                if (index == expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是什么
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果是运算符则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！！！！！
                        keepNum = "";//清空
                    }
                }
            }
            //指针后移
            index++;
            if (index == expression.length()) {
                //到头了
                break;
            }
        }
        //当扫描完毕，就顺序从取出
        //直到符号栈为空计算完毕
        while (true){
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            //计算结果入数栈
            numStack.push(res);
        }
        //将数栈的最后数pop出来就是结果
        int res2 = numStack.pop();
        System.out.println(res2);
    }
}
class Astack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数据放在数组里
    private int top = -1;//表示栈顶，初始为-1

    public Astack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //取出栈顶的元素,看一看
    public int peak(){
        return stack[top];
    }
    //判断是否栈满
    public boolean isFull(){
        return top == maxSize -1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int value){
        //先判断是否满了
        if (isFull()) {
            System.out.println("满了，，，");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("空了。。。");
        }
        int temp = stack[top];
        top--;
        return temp;
    }
    public void list(){
        if (isEmpty()) {
            System.out.println("栈空，没有数据了。");
            return;
        }
        for (int i = top; i > 0; i--) {
            System.out.println("stack["+i+"]="+stack[i]);
        }
    }
    //确定运算的优先级
    //优先级用数字来表示，数字越大，优先级越大
    public int priorty(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1, int num2,int oper){
        int res = 0;//计算结果
       switch (oper){
           case '+':
               res = num1 + num2;
               break;
           case '-'://必须是后减去前面!!!
               res = num2 - num1;
               break;
           case '*'://
               res = num2 * num1;
               break;
           case '/'://必须是后减去前面!!!
               res = num2 / num1;
               break;
           default:
               break;
       }
       return res;
    }

}