package DataStructure.code.liner;


import DataStructure.code.exception.MyException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author : krest
 * @date : 2021/1/3 10:20
 * @description: one-way LinkedList
 */
public class MyLinkedList extends MyNode implements List {

    /**
     * Create new node as head node but don't save data;
     */
    private MyNode headNode = new MyNode();

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
    public boolean add(Object object) {
        this.add(size,object);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        MyNode p= headNode;
        for (int i = 0; i <index ; i++) {
            p=p.nextNode;
        }
        return p.data;
    }

    public Object getNode(int index){
        checkIndex(index);
        MyNode p= headNode;
        for (int i = 0; i <index ; i++) {
            p=p.nextNode;
        }
        return p;
    }


    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);
        // find prev node, from head node
        MyNode p = headNode;
        for (int i = 0; i < index; i++) {
            p=p.nextNode;
        }

        // create new node
        MyNode newNode = new MyNode();
        newNode.data=element;
        //  indicate next node
        newNode.nextNode=p.nextNode;
        // indicate prev node
        p.nextNode=newNode;
        size++;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "HeadNode=" + headNode +
                ", size=" + size +
                '}';
    }

    private void checkIndex(int index) {
        if (index>size){
            throw new MyException("index out of limit");
        }
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
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

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
