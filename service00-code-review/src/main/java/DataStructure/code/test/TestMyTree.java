package DataStructure.code.test;

import DataStructure.code.tree.MyBinaryTree;
import DataStructure.code.tree.MyTree;
import DataStructure.code.tree.MyTreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: krest
 * @date: 2021/1/3 13:59
 * @description:
 */
public class TestMyTree {
    public static void main(String[] args) {

        Map<String,String> map=new HashMap<>();

        // create nodes
        MyTreeNode node7 = new MyTreeNode(7,null,null);
        MyTreeNode node6 = new MyTreeNode(6,null,node7);
        MyTreeNode node3 = new MyTreeNode(3,null,null);
        MyTreeNode node2 = new MyTreeNode(2,node3,node6);
        MyTreeNode node5 = new MyTreeNode(5,null,null);
        MyTreeNode node4 = new MyTreeNode(4,null,node5);
        MyTreeNode node1 = new MyTreeNode(1,node4,node2);


        // create tree
        MyTree tree = new MyBinaryTree(node1);

        // test_conf tree code
        System.out.println(tree.toString());
        System.out.println(tree.isEmpty());
        //preOrder
        tree.preOrderByRecursion();
        System.out.println();

        //inOrder
        tree.inOrderByRecursion();
        System.out.println();

        //inOrder
        tree.postOrderByRecursion();
        System.out.println();

        // getTrr height
        System.out.println(tree.getHeight());

        // getTrr node number
        System.out.println(tree.size());


        // get node
        System.out.println(tree.findKey(7));

        // levelQueue
        tree.levelOrderByStack();
        System.out.println();

        // in order Stack
        tree.inOrderByStack();
        System.out.println();

    }
}
