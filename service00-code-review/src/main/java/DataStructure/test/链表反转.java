package DataStructure.test;

import DataStructure.code.liner.MyNode;

/**
 * @author: krest
 * @date: 2021/4/26 11:10
 * @description:
 */
public class 链表反转 {
    public static void main(String[] args) {
        MyNode node15 = new MyNode(5,null);
        MyNode node14 = new MyNode(4,node15);
        MyNode node13 = new MyNode(3,node14);
        MyNode node12 = new MyNode(2,node13);
        MyNode node11 = new MyNode(1,node12);

        System.out.println(node11);
        MyNode node1 = reverse(node11);
        System.out.println(node1);

    }
    /**
     * 迭代想法实现链表反转
     * @param
     */
    private static MyNode reverse(MyNode node) {
        MyNode pre=null,next,cur=node;
        while(cur != null){
            next = cur.getNextNode();
            cur.setNextNode(pre);
            pre=cur;
            cur=next;
        }
        return pre;
    }
}
