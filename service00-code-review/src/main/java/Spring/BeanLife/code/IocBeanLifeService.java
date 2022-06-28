package Spring.BeanLife.code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @author: krest
 * @date: 2021/4/25 08:52
 * @description:  BeanLife流程详解
 *
 */
public class IocBeanLifeService implements BeanNameAware,
        BeanClassLoaderAware,
        InitializingBean,
        DisposableBean,
        ApplicationContextAware,
        ApplicationEventPublisherAware,

        BeanFactoryAware,

        EnvironmentAware,
        ImportAware,
        ResourceLoaderAware {

    /**
     *  Bean的基本属性
     */
    private String name;
    private String sex;

    /**
     * Get方法
     * @return Bean的名称
     */
    public String getName() {
        return name;
    }

    /**
     * Set方法，为Bean添加属性，从Xml文件中获取得到Bean的文件配置信息
     * @param name
     */
    public void setName(String name) {
        System.out.println("【步骤2】执行Bean的set方法,设置name属性值：" + name);
        this.name = name;
    }
    public void setSex(String sex) {
        System.out.println("【步骤2】执行Bean的set方法,设置sex属性值：" + sex);
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "IocBeanLifeService{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public IocBeanLifeService(){
        System.out.println("【步骤1】执行Bean的无参构造函数");
    }

    /**
     *  实现被继承的BeanNameAware的方法，为Bean添加名称
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("【步骤3】执行BeanNameAware中setBeanName方法，beanName值："
                + s);
    }

    /**
     *  通过类加载器，得到加载这个Bean的类加载器
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("【步骤4】执行BeanClassLoaderAware中setBeanClassLoader,ClassLoader的name = " + classLoader.getClass().getName());
    }


    /**
     * 检查BeanFactory是否已经包含了这个Bean
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【步骤5】执行BeanFactoryAware中setBeanFactory,beanFactory中是否包含IocBeanLifeService：" + beanFactory.containsBean("iocBeanLifeService"));
    }

    /**
     * 为Bean设置基本属性 environment
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("【步骤6】执行EnvironmentAware的setEnvironment方法");
    }

    /**
     * 设置类加载器需要加载的资源文件
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:applicationContext.xml");
        System.out.println("【步骤7】执行ResourceLoaderAware的setResourceLoader方法，Resource File Name="
                + resource.getFilename());
    }

    /**
     * Bean 初始化之前的一个操作，属于Spring事件机制，作为事件的发布者
     * 发布者会调用 ApplicationEventPublisher的publishEvent 方法对某一事件进行发布。
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("【步骤8】执行ApplicationEventPublisherAware中setApplicationEventPublisher方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【步骤9】执行ApplicationContextAware的setApplicationContext方法，Bean Definition Names="
                + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    @PostConstruct
    public void initPostConstruct(){
        System.out.println("【步骤11】执行PostConstruct注解标注的方法");
    }

    /**
     * 属性注入之后Bean的操作
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【步骤12】执行InitializingBean的afterPropertiesSet方法");

    }

    /**
     * 对Bean进行初始化
     * @throws Exception
     */
    public void initMethod() throws Exception {
        System.out.println("【步骤13】执行配置的init-method");
    }


    @PreDestroy
    public void preDestroy(){
        System.out.println("【步骤15】执行preDestroy注解标注的方法");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("【步骤16】执行DisposableBean接口的destroy方法");
    }


    /**
     *   通过<bean>的destroy-method属性指定的销毁方法
     */
    public void destroyMethod() throws Exception {
        System.out.println("【步骤17】执行配置的destroy-method");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("+++++++++执行setImportMetadata");
    }
}
