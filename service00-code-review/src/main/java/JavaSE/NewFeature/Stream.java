package JavaSE.NewFeature;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * JDK 1.8 新特性 Stream，相当于增强的迭代器
 */
public class Stream {
    @Test
    public void test1(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 过滤掉为空的 String，生成新的List
        List<String> filtered = strings.stream().filter(string ->
                !string.isEmpty()
        ).collect(Collectors.toList());
        // 打印List集合
        filtered.stream().forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数,distinct（）使用hashCode（）和equals（）方法来获取不同的元素。
        // 因此，我们的类必须实现 hashCode（）和 equals（）方法。
        List<Integer> squaresList = numbers.stream().map(i ->
                i * i
        ).distinct().collect(Collectors.toList());
        squaresList.stream().forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<String> list = Arrays.asList("AA", "BB", "CC", "BB", "CC", "AA", "AA");
        // 对于重复的元素进行统计
        long l = list.stream().distinct().count();
        System.out.println("No. of distinct elements:"+l);
        // 将所有不重复的元素组合起来生成字符串，并以“，”进行分割
        String output = list.stream().distinct().collect(Collectors.joining(","));
        System.out.println(output);
    }

    @Test
    public void test4(){
        Random random = new Random();
        // limit 随机打印 10个数字
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void test5(){
        Random random = new Random();
        // 先限定范围，sorted进行排序
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void test6(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        //  并行（parallel）程序 ,parallelStream 是流并行处理程序的代替方法，能够处理大量数据
        int count = (int) strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("count:"+count);
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        Arrays.asList("a1", "a2", "a3")
                .parallelStream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    @Test
    public void test7(){
        // Collectors 的使用
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string ->
                !string.isEmpty()
        ).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);

        String mergedString = strings.stream().filter(string ->
                !string.isEmpty()
        ).collect(Collectors.joining(", "));

        System.out.println("合并字符串: " + mergedString);
    }

    @Test
    public void test8(){
        // summaryStatistics 用来统计基本数据
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
