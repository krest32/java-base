package DataStructure.code.tree;

import java.util.*;

/**
 * @author: krest
 * @date: 2021/1/3 14:02
 * @description: MyBinaryTree implements class
 */
public class MyBinaryTree implements MyTree {

    /**
     * number of node
     */
    private int size;
    private int count =0 ;

    @Override
    public String toString() {
        return "MyBinaryTree{" +
                "size=" + size +
                ", rootNode=" + rootNode +
                '}';
    }

    /**
     * Root Node
     */
    private MyTreeNode rootNode;

    public MyBinaryTree(MyTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public int size() {
        return this.size(rootNode);
    }

    public int size(MyTreeNode node) {
        if (node!=null){
            int left = this.size(node.leftChild);
            int right = this.size(node.rightChild);
            return left+right+1 ;
        }else {
            return 0;
        }
    }


    @Override
    public boolean isEmpty() {
        return rootNode==null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object object) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public int getHeight() {
        return this.getHeight(rootNode);
    }

    private int getHeight(MyTreeNode node){
        if (node!=null){
            int leftHeight = this.getHeight(node.leftChild);
            int rightHight = this.getHeight(node.rightChild);
            return leftHeight>rightHight ? leftHeight+1 :rightHight+1 ;
        }else {
            return 0;
        }
    }

    @Override
    public Object findKey(Object value) {
        return this.findKey(value,rootNode);
    }

    public Object findKey(Object value,MyTreeNode node) {
        if(node==null){
            return null;
        }else if(node != null && node.value == value) {
            return node;
        }else{
            MyTreeNode node1 =(MyTreeNode)this.findKey(value,node.leftChild);
            MyTreeNode node2 =(MyTreeNode)this.findKey(value,node.rightChild);
            if (node1 !=null && node1.value == value){
                return node1;
            }else if (node2 !=null && node2.value == value){
                return node2;
            } else {
                return null;
            }
        }

    }

    @Override
    public void preOrderByRecursion() {
        // root node
        if (rootNode!=null){
            System.out.print(rootNode.value+" ");
            MyBinaryTree leftTree = new MyBinaryTree(rootNode.leftChild);
            leftTree.preOrderByRecursion();
            MyBinaryTree rightTree = new MyBinaryTree(rootNode.rightChild);
            rightTree.preOrderByRecursion();
        }

    }

    @Override
    public void inOrderByRecursion() {
        // root node
        this.inOrderByRecursion(rootNode);
    }

    private void inOrderByRecursion(MyTreeNode node) {
        // root node
        if (node != null) {
            // life child order
            this.inOrderByRecursion(node.leftChild);

            // root order
            System.out.print(node.value+" ");

            // right child order
            this.inOrderByRecursion(node.rightChild);
        }
    }

    @Override
    public void postOrderByRecursion() {
        this.postOrderByRecursion(rootNode);
    }

    private void postOrderByRecursion(MyTreeNode node) {
        // root node
        if (node != null) {
            // life child order
            this.postOrderByRecursion(node.leftChild);

            // right child order
            this.postOrderByRecursion(node.rightChild);

            // root order
            System.out.print(node.value+" ");


        }
    }

    @Override
    public void preOrderByStack() {

    }

    @Override
    public void inOrderByStack() {
        // Deque是双端队列（栈操作推荐使用） -> 中序非递归方法，但是整体的逻辑会非常复杂
        Deque<MyTreeNode> stack = new LinkedList<>();
        MyTreeNode current = rootNode;
        while(current!=null || !stack .isEmpty()){
            while (current !=null ){
                stack.push(current);
                current = current.leftChild;
            }
            if (!stack.isEmpty()){
                current= stack.pop();
                System.out.print(current.value+" ");
                current = current.rightChild;
            }
        }
    }

    @Override
    public void postOrderByStack() {

    }

    @Override
    public void levelOrderByStack() {
        if (rootNode!= null){
            Queue<MyTreeNode> queue = new LinkedList<>();
            queue.add(rootNode);
            while (queue.size()!=0){
                int len =queue.size();
                for (int i = 0; i < len; i++) {
                    MyTreeNode temp = queue.poll();

                    System.out.print(temp.value+" ");
                    if (temp.leftChild!=null) {
                        queue.add(temp.leftChild);
                    }
                    if (temp.rightChild!=null) {
                        queue.add(temp.rightChild);
                    }
                }
            }
        }
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
}
