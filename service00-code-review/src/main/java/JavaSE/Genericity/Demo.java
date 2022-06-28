package JavaSE.Genericity;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    /**
     * 测试 ？无界通配符
     */
    @Test
    public void demo01() {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs(dogs);
        // 报错
//        countLegs1(dogs);
    }


    /**
     * 下界通配符 < ? super E>
     */
    @Test
    public void demo02() {
        Demo demo = new Demo();
        List<Dog> dogs = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        demo.test(animals, dogs);
    }


    /**
     * ? 和 T 之间的区别
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void demo03() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> animal = Class.forName("JavaSE.Genericity.Animal");
        // Class<T> 会报错，而Class<？>则不会
//         Class<T> animalT = Class.forName("JavaSE.Genericity.Animal");
//         Animal animal2 = Class.forName("JavaSE.Genericity.Animal");

    }

    /**
     * List 中只要是Animal的子类都可以
     *
     * @param animals
     * @return
     */
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getCountLegs();
        }
        return retVal;
    }

    /**
     * List 中只能是Animal这个类的对象
     *
     * @param animals
     * @return
     */
    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getCountLegs();
        }
        return retVal;
    }


    /**
     * 下界通配符 < ? super E>
     *
     * @param dst
     * @param src
     * @param <T>
     */
    private <T> void test(List<? super T> dst, List<T> src) {
        for (T t : src) {
            dst.add(t);
        }
    }

}
