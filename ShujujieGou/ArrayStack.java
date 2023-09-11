package ShujujieGou;

import java.util.Scanner;

public class ArrayStack {
    public static void main(String[] args) {
//        Astack astack = new Astack(4);
//        String key = "";
//        boolean loop = true;//控制是否退出菜单
//        Scanner scanner = new Scanner(System.in);
//        while (loop){
//            System.out.println("show:表示显示栈");
//            System.out.println("exit:退出程序");
//            System.out.println("push:添加");
//            System.out.println("pop:出栈");
//            key = scanner.next();
//            switch (key){
//                case "show":
//                    astack.list();
//                    break;
//                case "push":
//                    System.out.println("输入一个数：");
//                    int value = scanner.nextInt();
//                    astack.push(value);
//                    break;
//                case "pop":
//                    try {
//                        int res = astack.pop();
//                        System.out.println("出栈的数据是"+res);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "exit":
//                    scanner.close();
//                    loop = false;
//                    break;
//                default:
//                    break;
//            }
//        }
        LinkedListStack ls = new LinkedListStack();
        ls.push(1);
        ls.push(2);
        ls.pop();
        ls.printStack();
    }
}
class Astack{
    private int maxSize;//栈的大小
    private int[] stack;//数据放在数组里
    private int top = -1;//表示栈顶，初始为-1

    public Astack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
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
}
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class LinkedListStack {
    private Node top; // 栈顶节点

    public LinkedListStack() {
        this.top = null; // 初始化为空栈
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int value) {//头插法
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = top.value;
        top = top.next;
        return value;
    }

    public int peek() {//顶端数据
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return top.value;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}