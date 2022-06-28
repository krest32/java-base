package Spring.MyselfSpring.code.resource;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: krest
 * @date: 2021/4/25 11:20
 * @description: Bean的主流程，通过配置类，解析Bean
 */
public class KrestApplicationContext {

    private Class configClass;
    /**
     * 用来存放生成的单例对象
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 存放每个Bean的定义
     */
    private ConcurrentHashMap<String,MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<MyBeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    /**
     * 有参构造方法
     * @param configClass
     */
    public KrestApplicationContext(Class configClass) throws ClassNotFoundException {
        this.configClass = configClass;
        MyScan(configClass);
        for (Map.Entry<String,MyBeanDefinition> entry: beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            MyBeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName,beanDefinition);
                System.out.println(entry.getKey());
                singletonObjects.put(beanName,bean);
            }
        }
    }

    /**
     * 创建 Bean 的方法
     * @param beanDefinition
     * @return
     */
    public Object createBean(String beanName, MyBeanDefinition beanDefinition)  {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 初始化之后的操作
            for (MyBeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 扫描文件夹
     * @param configClass
     * @throws ClassNotFoundException
     */
    private void MyScan(Class configClass) throws ClassNotFoundException {

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
        // 获取加载路径 路径格式为(spring.MyselfSpring.code.src)
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        // 获取文件加载目录下面所有的文件
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    // 调整字符串的架构，使他能够被类加载器进行加载
                    String className=fileName.substring(fileName.indexOf("Spring\\MyselfSpring"),fileName.indexOf(".class")).replace("\\",".");
                    System.out.println("扫描路径下面的文件："+className);
                    Class<?> aClass = classLoader.loadClass(className);
                    // 找到被 @MyComponent 修饰的注解
                    if (aClass.isAnnotationPresent(MyComponent.class)){
                        System.out.println("被@MyComponent修饰的文件："+className);
                        // 拿到MyComponent配置的value
                        MyComponent myComponentAnnotation = aClass.getDeclaredAnnotation(MyComponent.class);
                        // 判断 aClass 是否实现BeanPostProcessor这个接口
                        if (MyBeanPostProcessor.class.isAssignableFrom(aClass)){
                            try {
                                MyBeanPostProcessor beanPostProcessor =(MyBeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                                beanPostProcessorList.add(beanPostProcessor);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }


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
                }
            }
        }
    }

    /**
     * 通过名字得到对应的 Bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        if(beanDefinitionMap.containsKey(beanName)){
            MyBeanDefinition beanDefinition =(MyBeanDefinition) beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")){
                Object bean = singletonObjects.get(beanName);
                return bean;
            }else {
                // 如果 Scope 是 prototype ，那么就重新创建Bean
                return createBean(beanName,beanDefinition);
            }
        }else {
            throw new NullPointerException();
        }
    }
}
