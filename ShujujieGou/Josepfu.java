package ShujujieGou;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.showBoy();
        //circleSingleLinkedList.pop(2);
        System.out.println("+++++++++++++++++++++++");
        circleSingleLinkedList.pop01(1,2,5);
    }
}
class CircleSingleLinkedList{
    //创建一个first节点
    private Boy first = new Boy(-1);
    public void add(int num){//添加几个小孩
        if (num < 1) {
            System.out.println("值不对");
            return;
        }
        Boy curBoy = null;//辅助指针，便于添加
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            //如果你是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//指向自己
                curBoy = first;//便于添加,永远是最后一个
            } else {
                curBoy.setNext(boy);//最后一个先指向添加的
                boy.setNext(first);//指向第一！
                curBoy = boy;//指针后移
            }

        }
    }
    public void showBoy(){
        //是否为空
        if (first == null) {
            System.out.println("没有人");
            return;
        }
        //辅助指针
        Boy curboy = first;
        while (true){
            System.out.println(curboy.getNo()+"号");
            if (curboy.getNext() == first) {
                break;
            }
            curboy = curboy.getNext();//后移
        }
    }
    public void pop(int num){
        //辅助指针,先指向最后一个的前一个
        //指向first后面那个
        Boy curboy = first;
        while (true){
            if (curboy.getNext() == first) {
                break;
            }
            curboy = curboy.getNext();//后移
        }
        //本人也要数一下,所以要减一
        while (first.getNext() != first) {
            for (int i = 0; i < num-1; i++) {
                curboy = curboy.getNext();//后移
                first = first.getNext();
            }
            //移除！！！
            System.out.print(first.getNo()+"---->");
            first = first.getNext();//后移
            curboy.setNext(first);
        }
        System.out.println(first.getNo());
    }

    /**
     *
     * @param startNO 从第几个开始
     * @param countNum 数几下
     * @param nums 最初有几个小孩
     */
    public void pop01(int startNO,int countNum,int nums){
        //先校验数据
        if (first == null || startNO < 1 || startNO > nums) {
            System.out.println("参数有误");
            return;
        }
        //辅助指针,先指向最后一个的前一个
        //指向first后面那个
        Boy curboy = first;
        while (true){
            if (curboy.getNext() == first) {
                break;
            }
            curboy = curboy.getNext();//后移
        }
        //报数前移动k-1
        for (int j = 0; j < startNO -1; j++) {
            curboy = curboy.getNext();//后移
            first = first.getNext();
        }
        while (first.getNext() != first) {
            for (int i = 0; i < countNum-1; i++) {
                curboy = curboy.getNext();//后移
                first = first.getNext();
            }
            //移除！！！
            System.out.print(first.getNo()+"---->");
            first = first.getNext();//后移
            curboy.setNext(first);
        }
        System.out.println("最后的是·"+first.getNo());
    }
}
class Boy{
    private int no;//编号
    private Boy next;//指向下一个

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}