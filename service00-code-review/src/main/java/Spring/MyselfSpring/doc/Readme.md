# 手写Spring

目的：深刻理解Spring框架中IOC的核心理论，文档写的比较潦草

## 准备工作

### 自定义注解和测试类

#### @MyComponentScan

~~~java

/**
 * @author: krest
 * @date: 2021/4/25 11:24
 * @description: 定义扫描路径
 */
@Retention(RetentionPolicy.RUNTIME) //运行加载改注解
@Target({ElementType.TYPE}) // 注解使用位置
public @interface MyComponentScan {
    //设定注解的属性，默认值可以为空
    String value() default "";
}

~~~

####  @MyComponent

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 11:24
 * @description: 定义Bean组件
 */
@Retention(RetentionPolicy.RUNTIME) //运行加载改注解
@Target({ElementType.TYPE}) // 注解使用位置
public @interface MyComponent {
    //设定注解的属性，默认值可以为空
    String value() default "";
}

~~~

#### @MyScope

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 12:25
 * @description: 定义Bean的作用域 Singleton 或者 prototype
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyScope {
    String value();
}

~~~

#### @MyAutowired

~~~~java
/**
 * @author: krest
 * @date: 2021/4/25 13:24
 * @description: 自动装配注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD}) // 添加到方法的注解
public @interface MyAutowired {
}

~~~~

#### 自动注入的程序

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 13:23
 * @description:
 */
@MyComponent("orderService")
public class OrderService {
}

~~~

#### 测试接口

~~~java
public interface UserService {
    void test();
}
~~~

#### 接口实现类

~~~java
@MyComponent("userService")
@MyScope("prototype")
public class UserServiceImpl {

    @MyAutowired
    private OrderService orderService;
    
    private String beanName;

    private String name;

    public String getBeanName() {
        return beanName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void  testAutowired(){
        System.out.println("orderService的属性："+orderService);
    }
}

~~~

#### 测试程序入口

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 11:17
 * @description: 测试程序入口
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        KrestApplicationContext applicationContext = new KrestApplicationContext(AppConfig.class);

        // 创建多利Bean
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));

        // 属性注入测试
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();

        // 属性注入测试
        RootService rootService = (RootService) applicationContext.getBean("rootService");
        System.out.println("root:"+rootService.getBeanName());
    }
}
~~~

### 主要程序逻辑

#### 1. 扫描代码文件夹

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 11:23
 * @description: mySpring配置类，程序扫描入口
 */

@MyComponentScan("Spring.MyselfSpring.code.src")
public class AppConfig {
}

~~~

#### 2. 开始扫描文件夹

~~~java
// 获取传入的class的注解信息——扫描路径信息
        MyComponentScan componentScanAnnotation = (MyComponentScan) configClass.getDeclaredAnnotation(MyComponentScan.class);
        String path = componentScanAnnotation.value().replace(".","/");
        System.out.println("类加载器路径扫描格式："+path);

        // 对扫描路径进行扫描
        // 通过类加载器去获取对应的类加载路径，常见的类加载器与加载路径
        // Bootstrap-->jre/lib
        // Ext ------> jre/ext/lib
        // App-------> classpath
        // 获取类加载器
        ClassLoader classLoader = KrestApplicationContext.class.getClassLoader();
        // 获取加载路径 路径格式为(Spring.MyselfSpring/code/src)
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        // 获取文件加载目录下面所有的文件
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    // 调整字符串的架构，使他能够被类加载器进行加载
                    String className=fileName.substring(fileName.indexOf("Spring.MyselfSpring"),fileName.indexOf(".class")).replace("\\",".");
                    System.out.println("扫描路径下面的文件："+className);
                    Class<?> aClass = classLoader.loadClass(className);
                    // 找到被 @MyComponent 修饰的注解

                    }
                }
            }
        }
~~~

#### 4. 将@Component修饰的组件加入容器并且生成一个BeanDefiniton的Map集合

~~~java
 if (aClass.isAnnotationPresent(MyComponent.class)){
                        System.out.println("被@MyComponent修饰的文件："+className);
                        // 拿到MyComponent配置的value
                        MyComponent myComponentAnnotation = aClass.getDeclaredAnnotation(MyComponent.class);
                            String beanName = myComponentAnnotation.value();
                        System.out.println("Component中的value："+beanName);

                        MyBeanDefinition beanDefinition = new MyBeanDefinition();

                        // 判断 是否被 MyScope.class 修饰
                        if( aClass.isAnnotationPresent(MyScope.class)){
                            MyScope myScope = aClass.getDeclaredAnnotation(MyScope.class);
                            System.out.println("myScope修饰的value："+myScope.value());
                            beanDefinition.setScope(myScope.value());
                        }else {
                            System.out.println("没有被myScope修饰");
                            beanDefinition.setScope("singleton");
                        }
                        beanDefinition.setClazz(aClass);
                        // beanDefinition 存入相应的 beanDefinitionMap 中
                        beanDefinitionMap.put(beanName,beanDefinition);
                        }
~~~



#### 5. 遍历BeanDefiniton集合，生成Bean，放入到BeanMap中

​		将所有的Bean的属性放入到专门的集合当中

~~~java
for (Map.Entry<String,MyBeanDefinition> entry: beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            MyBeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName,beanDefinition);
                System.out.println(entry.getKey());
                singletonObjects.put(beanName,bean);
            }
        }
~~~



#### 5. Autowired原理

​		利用反射，查看Bean的某个属性中，是否被Autowired修饰，如果有，那么就从单例池中找到对应Bean，从而进行属性注入

~~~java
/**
     * 创建 Bean 的方法
     * @param beanDefinition
     * @return
     */
    public  Object createBean(String beanName, MyBeanDefinition beanDefinition)  {
        Class clazz = beanDefinition.getClazz();
        Object instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();

            // 进行依赖在注入
            // 遍历所有的属性
            for (Field declaredField : clazz.getDeclaredFields()) {
                // 属性中只有添加Autowired的注解的属性，才会被自动注入属性
                if (declaredField.isAnnotationPresent(MyAutowired.class)){
                    Object bean = getBean(declaredField.getName());
                    if (bean == null){
                        throw new NullPointerException();
                    }
                    // 设置反射中的安全检查
                    declaredField.setAccessible(true);
                    declaredField.set(instance,bean);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }
~~~

#### 6 继承其他接口

属性注入之后对于Bean进行用户定制的初始化操作

~~~java
            // 判断是否实现 MyBeanNameWare 接口，如果有，那么回传 BeanName 给到实例对象
            if (instance instanceof MyBeanNameWare){
                ((MyBeanNameWare) instance).setBeanName(beanName);
            }

            // 初始化之前的操作
            for (MyBeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance  = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            // Bean 初始化，方便程序员进行使用
            if (instance instanceof MyInitializingBean){
                try {
                    ((MyInitializingBean) instance).afterPropertiesSet();
                } catch (JavaSE.Exception e) {
                    e.printStackTrace();
                }
            }
            // 初始化之后的操作
            for (MyBeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }
~~~

#### 7. Aop功能实现（Bean的后置处理器）

通过继承MyBeanPostProcessor，开始实现Aop功能

~~~java

/**
 * @author: krest
 * @date: 2021/4/25 14:00
 * @description: 所有创建的Bean都会执行这个方法
 */
@MyComponent("TestBeanPostProcessor")
public class TestBeanPostProcessor implements MyBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")){
            ((UserServiceImpl)bean).setName("你好帅");
        }
        return bean;
    }

    /**
    * Aop 功能，最终对象为代理对象
    */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后");
        if (beanName.equals("userService")){
            Object proxtyInstance = Proxy.newProxyInstance(TestBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 执行代理对象逻辑
                    System.out.println("==========执行代理逻辑=================");
                    return  method.invoke(bean,args);
                }
            });
            // 返回代理对象
            return proxtyInstance;
        }
        return bean;
    }
}
~~~







