package JavaSE.Basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description：数组常用API
 * @Author: Krest
 * @Date: 2021/9/4 11:18
 */
public class ArrayDemo1 {

    /**
     * 数组转换成列表
     */
    @Test
    public void test1() {
        String[] strArray = {"zhang", "xue", "zhi"};
        List<String> list = Arrays.asList(strArray);
        // 打印元素
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    /**
     * 二分查找
     */
    @Test
    public void test2() {
        int[] a = {3, 5, 9, 7, 2};
        // 排序
        Arrays.sort(a);
        // 打印数组
        for (int item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
        // 二分查找
        int ind1 = Arrays.binarySearch(a, 2);
        int ind2 = Arrays.binarySearch(a, 4);
        int ind3 = Arrays.binarySearch(a, 1, 3, 5);
        System.out.println("2的查找位置：" + ind1);
        System.out.println("4的查找位置：" + ind2);
        System.out.println("5的查找位置：" + ind3);
    }

    /**
     * 复制长度大于原数组长度时，后面补零。
     */
    @Test
    public void test3() {
        int[] a = {3, 5, 9, 7, 2};
        int[] newa = Arrays.copyOf(a, 2);
        //复制长度大于原数组的长度
        int[] newa2 = Arrays.copyOf(a, 7);

        for (int item : newa) {
            System.out.print(item + " ");
        }
        System.out.println();

        for (int item : newa2) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Test
    public void demo() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        for (int i : collect) {
            System.out.println(i);
        }
    }
}
