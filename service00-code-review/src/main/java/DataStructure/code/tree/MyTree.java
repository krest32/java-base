package DataStructure.code.tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author: krest
 * @date: 2021/1/3 14:01
 * @description:
 */
public interface MyTree<E> extends Collection<E> {

    /**
     * 检查树的大小
     * @return 数的大小
     */
    @Override
    int size();


    /**
     * 检查树是否为空
     * @return 返回值
     */
    @Override
    boolean isEmpty();


    /**
     * 确认集合中元素是否存在
     * @param o 包含元素
     * @return  方法boolean
     */
    @Override
    boolean contains(Object o);


    /**
     * 检查树的大小
     * @return 数的大小
     */
    @Override
    Iterator<E> iterator();


    /**
     *  添加元素
     * @param e 添加的元素
     * @return 添加结果
     */
    @Override
    boolean add(E e);

    /**
     *  添加元素
     * @param o 添加的元素
     * @return 添加结果
     */
    @Override
    boolean remove(Object o);


    /**
     * 检查树的大小
     * @return 数的大小
     */
    int getHeight();


    /**
     *  查找功能
     * @param value 需要查找的key
     * @return 返回查找的数据
     */
    Object findKey(Object value);

    /**
     * preorder traversal use recursion
     */
    void preOrderByRecursion();

    /**
     *  inorder traversal use recursion
     */
    void inOrderByRecursion();

    /**
     *  postOrder traversal use recursion
     */
    void postOrderByRecursion();

    /**
     * preorder traversal use stack
     */
    void preOrderByStack();

    /**
     *  inorder traversal use stack
     */
    void inOrderByStack();

    /**
     *  postOrder traversal use stack
     */
    void postOrderByStack();


    /**
     *  Order by level use stack
     */
    void levelOrderByStack();

}
