package DataStructure.code.liner;



import DataStructure.code.exception.MyException;

import java.util.*;

/**
 *
 * @author Krest
 */
public class MyArrayList implements List {

    /**
     *  底层是一个Object数组
     */
    private Object[] elementData;

    /**
     * 元素的个数
     */
    private int size;

    /**
     * 指定默认的数组长度
     */
    private int defaultCapacity=10;

    /**
     * 构造方法
     * @param initalCapacity 指定数组的初始长度
     */
    public MyArrayList(int initalCapacity){
        // 根据传入的参数建立数组
        elementData = new Object[initalCapacity];
    }

    /**
     * 无参数的构造方法
     */
    public MyArrayList(){
        // 根据传入的参数建立数组
        elementData = new Object[defaultCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }else {
            return false;
        }
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

    /**
     * add element to list
     * @param object new element
     * @return boolean
     */
    @Override
    public boolean add(Object object) {
        this.add(size,object);
        return true;
    }

    /**
     * check elementData Capacity
     */
    private void checkCapacity() {
        if(size==elementData.length){
            grow();
        }
    }

    /**
     *  List Expansion method
     */
    private void grow() {
        System.out.println("****************[info: myList Expansion]*********************");
        elementData = Arrays.copyOf(elementData, (size << 1)-(size/2));
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    /**
     * add new list to assign point
     * @param index assign point
     * @param c list
     * @return
     */
    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        if ( index < 0 || index >= size){
            throw new MyException("Wrong index of MyArrayList to get element");
        }
        return  elementData[index];
    }


    @Override
    public Object set(int index, Object element) {
        return null;
    }

    /**
     * add new element yto list
     * @param index assign location
     * @param element   new element
     * @return
     */
    @Override
    public void add(int index, Object element) {
        checkCapacity();
        if (index>size){
            size=index;
            grow();
        }
        for (int i = size; i > index ; i--) {
            elementData[i] = elementData[i-1];
        }
        elementData[index] = element;
        size++;
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
    public String toString() {
        return "MyArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                ", defaultCapacity=" + defaultCapacity +
                '}';
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
