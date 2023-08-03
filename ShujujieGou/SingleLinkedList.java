package ShujujieGou;


import java.util.Stack;

public class SingleLinkedList {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"吴用","智多星");
        HeroNode hero3 = new HeroNode(3,"卢俊义","玉麒麟");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建要给链表
        LinkedList linkedList1 = new LinkedList();//加入
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);
        linkedList1.add02(hero3);
        linkedList1.add02(hero4);
        linkedList1.add02(hero1);
        linkedList1.add02(hero2);
        linkedList1.list();

        HeroNode hero5 = new HeroNode(4,"asd","dsa");
        linkedList1.update(hero5);
        linkedList1.list();

        linkedList1.delet(2);
        linkedList1.delet(4);
        linkedList1.list();

//        count(linkedList1.getHead());
//        System.out.println(find(linkedList1.getHead(),2));
//        reverse(linkedList1.getHead());
//        reversePrint(linkedList1.getHead());
        LinkedList linkedList2 = new LinkedList();
        linkedList2.add02(hero2);
        linkedList2.add02(hero4);
        HeroNode and = and(linkedList1.getHead(), linkedList2.getHead());
        System.out.println("==================");
        LinkedList linkedList3 = new LinkedList();
        linkedList3.add(and);
        linkedList3.list();
    }
    //有效节点数，不包含头节点
    public static int count(HeroNode heroNode){
        int length = 0;//长度
        //heroNode头节点
        while (heroNode.next != null){
            length++;
            heroNode = heroNode.next;
        }
        //System.out.println(length);
        return length;
    }
    //倒数第K个节点
    //1。编写一个方法，接收head节点，同时接收一个index
    //2。index 表示是倒数第index个节点
    //3 .先把链表从头到尾遍历，得到链表的总的长度 count
    //4。得到size 后，我们从链表的第一个开始遍历(size-index)个
    //方法:获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点东宋
    public static HeroNode find(HeroNode heroNode,int index){
        if (heroNode.next == null){
            return null;//链表为空则无
        }
        if (index <= 0||index > count(heroNode)){
            return null;//不合理的index丢出
        }
        int num = count(heroNode) - index;
        heroNode = heroNode.next;
        for (int i = 0; i < num; i++) {
            heroNode = heroNode.next;
            num--;
        }
        return heroNode;
    }
    //1先定义一个节点reverseHead=new HeroNode();
    //2.从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
    //3.原来的链表的head.next=reverseHead.next
    public static void reverse(HeroNode heroNode){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(heroNode.next == null||heroNode.next.next == null) {
            return ;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = heroNode.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        while (cur != null){
            next = cur.next;//当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新链表的最前端
            reverseHead.next = cur;//挂在链表上
            cur = next;//向后移动
        }
        //将head.next 指向 reverseHead.next ，实现单链表的反转
        heroNode.next = reverseHead.next;
    }
    //倒着打印
    public static void reversePrint(HeroNode heroNode){
        if (heroNode.next == null) {
            return;//空链表不打印
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode cur = heroNode.next;
        while (cur != null){
            heroNodes.push(cur);//入栈
            cur = cur.next;//后移
        }
        System.out.println("===================");
        while (heroNodes.size() > 0){
            System.out.println(heroNodes.pop());
        }
    }
    //合并
    public static HeroNode and(HeroNode h1,HeroNode h2){
        HeroNode head = new HeroNode(0,"","");
        HeroNode point = head;
        HeroNode cur01 = h1.next;//指向第一个
        HeroNode cur02 = h2.next;
        while (cur01 != null && cur02 != null){
            if (cur01.no < cur02.no) {
                point.next = cur01;
                point = point.next;
                cur01 = cur01.next;
            }else {
                point.next = cur02;
                point = point.next;
                cur02 = cur02.next;
            }
        }
        if (cur01 == null) {
            point.next = cur02;
        }
        if (cur02 == null) {
            point.next = cur01;
        }
        return head.next;
    }
}
//用于管理HeroNode
class LinkedList {
    //先初始化一个头节点，头节点不用动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");
    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表的最后节点
    //思路，当不考虑编号顺序时
    // 1， 找到当前链表的最后节点
    // 2。将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;//遍历链表，找到最后
        //方案一
        while(temp.next != null){
            //下一个，直到末尾
            temp = temp.next;
        }
        temp.next = heroNode;//加入
        //方案二
//        while(true) {//找到链表的最后
//            if (temp.next == null) {
//                break;
//            }
//            //如果没有找到最后，将temp后移
//            temp = temp.next;
//        }
//        //当退出while循环时，temp就指向了链表的最后
//        // 将最后这个节点的next 指向 新的节点
//        temp.next = heroNode;
    }
    //指定处添加一个数据
    //添加后仍然保持顺序
    public void add02(HeroNode heroNode){
        HeroNode temp = head;//是要添加位置的前一个节点
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next == null) { //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true; //说明编号存在
                break;
            }
        temp = temp.next;
        }
        if (flag == true) {
            System.out.println("已经存在了");
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    //修改。。。
    public void update(HeroNode heroNode){
        HeroNode temp = head.next;//修改的位置
        while(true){
            if(temp == null) { //说明temp已经在链表的最后
                System.out.println("没得");
                break;
            }
            if(temp.no == heroNode.no) { //位置找到
                break;
            }
            temp = temp.next;
        }
        temp.name = heroNode.name;
        temp.nickname = heroNode.nickname;
    }
    public void delet(int no){
        HeroNode temp = head;//必须是前一个
        while(true){
            if(temp.next == null) { //说明temp已经在链表的最后
                System.out.println("没得");
                break;
            }
            if(temp.next.no == no) { //位置找到，就在temp的后面
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }
    //显示所有数据
    public void list(){
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("空了。。。");
            return;
        }
        HeroNode temp = head.next;//指向第一个数据
        //方案一
        while (temp != null){
            System.out.println(temp.no+"---"+temp.nickname+"---"+temp.name);
            temp = temp.next;
        }
        //方案二
//        while (true){
//            if (temp == null) {
//                break;
//            }
//            System.out.println(temp);
//            temp = temp.next;
//        }
    }
}
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //创建构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' ;
    }
}