package ShujujieGou;

public class DoubleLinkedListMain {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode01 hero1 = new HeroNode01(1,"宋江","及时雨");
        HeroNode01 hero2 = new HeroNode01(2,"吴用","智多星");
        HeroNode01 hero3 = new HeroNode01(3,"卢俊义","玉麒麟");
        HeroNode01 hero4 = new HeroNode01(4,"林冲","豹子头");

        //创建要给链表
        DoubleLinkedList dll = new DoubleLinkedList();//加入
        dll.addTail(hero1);
        dll.addTail(hero2);
        dll.addTail(hero3);
        dll.addTail(hero4);

        dll.list();

        // 修改
        HeroNode01 newHeroNode = new HeroNode01(4,"公孙胜","入云龙");
        dll.update(newHeroNode);
        System.out.println("修改后的链表情况");
        dll.list();
        // 删除
        dll.delete(3);
        System.out.println("删除后的链表情况~~");
        dll.list();
        //添加
        HeroNode01 n5 = new HeroNode01(5,"花荣","小李广");
        dll.add(hero3);
        dll.add(n5);
        dll.list();
    }
}
class DoubleLinkedList{
    //头节点
    private HeroNode01 head = new HeroNode01(0,"","");

    public HeroNode01 getHead() {
        return head;
    }
    //显示所有数据
    public void list(){
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("空了。。。");
            return;
        }
        HeroNode01 temp = head.next;//指向第一个数据
        //方案一
        while (temp != null){
            System.out.println(temp.no+"---"+temp.nickname+"---"+temp.name);
            temp = temp.next;
        }
    }
    //添加到最后
    public void addTail(HeroNode01 heroNode01){
        HeroNode01 temp = head;//指向
        while (temp.next != null){
            //来到最后一个
            temp = temp.next;
        }
        temp.next = heroNode01;
        heroNode01.pre = temp;
    }
    //根据序号添加
    public void add (HeroNode01 heroNode01){
        HeroNode01 temp = head.next;//指向
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while (temp.next != null){//要考虑到最后一个
            if (temp.next.no > heroNode01.no) {
                break;
            }
            if (temp.no == heroNode01.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            System.out.println("已经存在了");
        }else {
            //先加后一个的
            //万一是最后后一个！！！！！！！！！！
            if (temp.next != null) {
                temp.next.pre = heroNode01;
                heroNode01.next = temp.next;
            }
            //再加前一个的
            temp.next = heroNode01;
            heroNode01.pre = temp;
        }
    }
    //更改
    public void update(HeroNode01 heroNode01){
        HeroNode01 temp = head.next;//修改的位置
        while(true){
            if(temp == null) { //说明temp已经在链表的最后
                System.out.println("没得");
                break;
            }
            if(temp.no == heroNode01.no) { //位置找到
                break;
            }
            temp = temp.next;
        }
        temp.name = heroNode01.name;
        temp.nickname = heroNode01.nickname;
    }
    public void delete(int no){
        //直接找到
        HeroNode01 temp = head.next;
        while(true){
            if(temp == null) { //说明temp已经在链表的最后
                System.out.println("没得");
                break;
            }
            if(temp.no == no) { //位置找到，就在temp
                break;
            }
            temp = temp.next;
        }
        temp.pre.next = temp.next;
        //如果是最后一个节点，就不需要执行这个，
        //不然会有错误，所以必须有IF
        if (temp.next != null) {
            temp.next.pre = temp.pre;
        }
    }
}
class HeroNode01 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode01 pre;//前一个
    public HeroNode01 next;//指向下一个节点

    //创建构造器
    public HeroNode01(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode01{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' ;
    }
}