# Spring Aop

## 概念

​		AOP（Aspect-OrientedProgramming，面向切面编程），可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。目的是为了代码重用，为现有的应用系统在不修改原有代码的基础上提供新的功能。使Java的编程更加灵活。

## AOP的主要应用场景

+ Authentication 权限
+ Caching 缓存
+ Context passing 内容传递

+ Error handling 错误处理

+ Lazy loading　懒加载

+ Debugging　　调试

+ logging, tracing, profiling and monitoring　记录跟踪　优化　校准

+ Performance optimization　性能优化

+ Persistence　　持久化

+ Resource pooling　资源池

+ Synchronization　同步

+ Transactions 事务

## AOP相关概念

### 基本概念

- (1)Aspect(切面):通常是一个类，里面可以定义切入点和通知
- (2)JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用
- (3)Advice(通知):AOP在特定的切入点上执行的增强处理，有before,after,afterReturning,afterThrowing,around
- (4)Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
- (5)AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类

### 通知方法:

1. 前置通知:在我们执行目标方法之前运行(**@Before**)
2. 后置通知:在我们目标方法运行结束之后 ,不管有没有异常***\*(@After)\****
3. 返回通知:在我们的目标方法正常返回值后运行***\*(@AfterReturning)\****
4. 异常通知:在我们的目标方法出现异常后运行***\*(@AfterThrowing)\****
5. 环绕通知:动态代理, 需要手动执行joinPoint.procced()(其实就是执行我们的目标方法执行之前相当于前置通知, 执行之后就相当于我们后置通知**(@Around)**

### 实现原理

​		Spring中的AOP代理还是离不开Spring的IOC容器，代理的生成，管理及其依赖关系都是由IOC容器负责，Spring默认使用JDK动态代理，在需要代理类而不是代理接口的时候，Spring会自动切换为使用CGLIB代理，不过现在的项目都是面向接口编程，所以JDK动态代理相对来说用的还是多一些。

## Aop测试代码

### 目标类

~~~java
/**
 * @author: krest
 * @date: 2021/4/25 10:16
 * @description: 业务逻辑程序
 */
public class Calculator {
    public int div(int i, int j) {
        System.out.println("+++++++++++++++++++++这主程序执行+++++++++++++++++++++++");
        return i / j;
    }
}


~~~

### 切面类

~~~java

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * 定义程序的切面类
 */
@Aspect
public class LogAspects {
    @Pointcut("execution(* Spring.Aop.code.Calculator.*(..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void logStart(){
        System.out.println("Before方法执行.......");
    }
    @After("pointCut()")
    public void logEnd(){
        System.out.println("After方法执行......");
    }
    @AfterReturning("pointCut()")
    public void logReturn(){
        System.out.println("AfterRunning方法执行......");
    }
    @AfterThrowing("pointCut()")
    public void logException(){
        System.out.println("AfterThrowing 程序跑出异常后执行.......");
    }
    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("@Arount:执行目标方法之前...");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("@Arount:执行目标方法之后...");
        return obj;
    }
}
~~~

### 配置类

~~~java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Cap10MainConfig {
    @Bean
    public Calculator calculator(){
        return new Calculator();
    }
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
~~~

### 测试程序

~~~java
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * @author: krest
 * @date: 2021/4/25 10:17
 * @description: 测试切面编程
 */
public class Cap10Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap10MainConfig.class);
        Calculator c = app.getBean(Calculator.class);
        int result = c.div(4, 3);
        System.out.println(result);
        app.close();
    }
}
~~~

### 运行结果

~~~
@Arount:执行目标方法之前...
Before方法执行.......
+++++++++++++++++++++这主程序执行+++++++++++++++++++++++
AfterRunning方法执行......
After方法执行......
@Arount:执行目标方法之后...
1

~~~



## AOP源码

### 常用注解

- @EnableAspectJAutoProxy开启AOP
- @EnableTransactionManagement开启spring事务管理,
- @EnableCaching开启spring缓存
- @EnableWebMvc 开启webMvc

### @EnableAspectJAutoProxy解析

~~~java

/**
* 这里简单介绍一下两个参数,一个是控制aop的具体实现方式,为true的话使用cglib,为false的话使用java的Proxy,默认为false,
* 第二个参数控制代理的暴露方式,解决内部调用不能使用代理的场景，默认为false.
* 这里核心是@Import(AspectJAutoProxyRegistrar.class);在AspectJAutoProxyRegistrar里,核心的地方是
* AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {
    boolean proxyTargetClass() default false;
    boolean exposeProxy() default false;
}
~~~

~~~java
package org.springframework.context.annotation;

import org.springframework.aop.com.krest.cloud.config.AopConfigUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
* 一个AOP的工具类,这个工具类的主要作用是把AnnotationAwareAspectJAutoProxyCreator这个类定义为BeanDefinition放到spring容器
* 中,这是通过实现ImportBeanDefinitionRegistrar接口来装载的,具体装载过程不是本篇的重点,这里就不赘述,我们重点看 
* AnnotationAwareAspectJAutoProxyCreator这个类.
*
*/
class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {
    AspectJAutoProxyRegistrar() {
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
        AnnotationAttributes enableAspectJAutoProxy = AnnotationConfigUtils.attributesFor(importingClassMetadata, EnableAspectJAutoProxy.class);
        if (enableAspectJAutoProxy != null) {
            if (enableAspectJAutoProxy.getBoolean("proxyTargetClass")) {
                AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
            }

            if (enableAspectJAutoProxy.getBoolean("exposeProxy")) {
                AopConfigUtils.forceAutoProxyCreatorToExposeProxy(registry);
            }
        }

    }
}

~~~

![img](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210425104214.png)

这里先看一下最顶部的抽象类:AbstractAutoProxyCreator,这个抽象类主要抽象了实现代理的逻辑:

~~~java

@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}
 
    // 主要看这个方法，在bean初始化之后对生产出的bean进行包装
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean != null) {
			Object cacheKey = getCacheKey(bean.getClass(), beanName);
			if (!this.earlyProxyReferences.contains(cacheKey)) {
				return wrapIfNecessary(bean, beanName, cacheKey);
			}
		}
		return bean;
	}
 
    // wrapIfNecessary
	protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
		if (beanName != null && this.targetSourcedBeans.contains(beanName)) {
			return bean;
		}
		if (Boolean.FALSE.equals(this.advisedBeans.get(cacheKey))) {
			return bean;
		}
		if (isInfrastructureClass(bean.getClass()) || shouldSkip(bean.getClass(), beanName)) {
			this.advisedBeans.put(cacheKey, Boolean.FALSE);
			return bean;
		}
 
		// Create proxy if we have advice.
        // 意思就是如果该类有advice则创建proxy，
		Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
		if (specificInterceptors != DO_NOT_PROXY) {
			this.advisedBeans.put(cacheKey, Boolean.TRUE);
            // 1.通过方法名也能简单猜测到，这个方法就是把bean包装为proxy的主要方法，
			Object proxy = createProxy(
					bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
			this.proxyTypes.put(cacheKey, proxy.getClass());
            
            // 2.返回该proxy代替原来的bean
			return proxy;
		}
 
		this.advisedBeans.put(cacheKey, Boolean.FALSE);
		return bean;
	}
~~~

### 总结：

- **1）将AnnotationAwareAspectJAutoProxyCreator注册到Spring容器中**
- **2）AnnotationAwareAspectJAutoProxyCreator类的postProcessAfterInitialization()方法将所有有advice的bean重新包装成proxy**

### 创建proxy过程分析

​		通过之前的代码结构分析，我们知道，所有的bean在返回给用户使用之前都需要经过AnnotationAwareAspectJAutoProxyCreator类的postProcessAfterInitialization()方法，而该方法的主要作用也就是将所有拥有advice的bean重新包装为proxy，那么我们接下来直接分析这个包装为proxy的方法即可，看一下bean如何被包装为proxy，proxy在被调用方法时，是具体如何执行的

 **以下是AbstractAutoProxyCreator.wrapIfNecessary(Object bean, String beanName, Object cacheKey)中的createProxy()代码片段分析**

~~~java
protected Object createProxy(
			Class<?> beanClass, String beanName, Object[] specificInterceptors, TargetSource targetSource) {
 
		if (this.beanFactory instanceof ConfigurableListableBeanFactory) {
			AutoProxyUtils.exposeTargetClass((ConfigurableListableBeanFactory) this.beanFactory, beanName, beanClass);
		}
 
        // 1.创建proxyFactory，proxy的生产主要就是在proxyFactory做的
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.copyFrom(this);
 
		if (!proxyFactory.isProxyTargetClass()) {
			if (shouldProxyTargetClass(beanClass, beanName)) {
				proxyFactory.setProxyTargetClass(true);
			}
			else {
				evaluateProxyInterfaces(beanClass, proxyFactory);
			}
		}
 
        // 2.将当前bean适合的advice，重新封装下，封装为Advisor类，然后添加到ProxyFactory中
		Advisor[] advisors = buildAdvisors(beanName, specificInterceptors);
		for (Advisor advisor : advisors) {
			proxyFactory.addAdvisor(advisor);
		}
 
		proxyFactory.setTargetSource(targetSource);
		customizeProxyFactory(proxyFactory);
 
		proxyFactory.setFrozen(this.freezeProxy);
		if (advisorsPreFiltered()) {
			proxyFactory.setPreFiltered(true);
		}
 
        // 3.调用getProxy获取bean对应的proxy
		return proxyFactory.getProxy(getProxyClassLoader());
	}
~~~

​		 TargetSource中存放被代理的对象,这段代码主要是为了构建ProxyFactory,将配置信息(是否使用java proxy,是否threadlocal等),目标类,切面,传入ProxyFactory中

#### 创建何种类型的Proxy？

~~~java
// getProxy()方法
	public Object getProxy(ClassLoader classLoader) {
		return createAopProxy().getProxy(classLoader);
    } 
    // createAopProxy()方法就是决定究竟创建何种类型的proxy
	protected final synchronized AopProxy createAopProxy() {
		if (!this.active) {
			activate();
		}
        // 关键方法createAopProxy()
		return getAopProxyFactory().createAopProxy(this);
	}
 
    // 生成代理对象的主方法
	public AopProxy createAopProxy(AdvisedSupport com.krest.cloud.config) throws AopConfigException {
        // 1.threadpool.isOptimize()是否使用优化的代理策略，目前使用与CGLIB
        // threadpool.isProxyTargetClass() 是否目标类本身被代理而不是目标类的接口
        // hasNoUserSuppliedProxyInterfaces()是否存在代理接口
		if (com.krest.cloud.config.isOptimize() || com.krest.cloud.config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(com.krest.cloud.config)) {
			Class<?> targetClass = com.krest.cloud.config.getTargetClass();
			if (targetClass == null) {
				throw new AopConfigException("TargetSource cannot determine target class: " +
						"Either an interface or a target is required for proxy creation.");
			}
           
            // 2.如果目标类是接口或者是代理类，则直接使用JDK动态代理
			if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
				return new JdkDynamicAopProxy(com.krest.cloud.config);
			}
            // 3.其他情况则使用CGLIBproxy
			return new ObjenesisCglibAopProxy(com.krest.cloud.config);
		}
		else {
			return new JdkDynamicAopProxy(com.krest.cloud.config);
		}
	}
~~~

#### JDK中getProxy()方法

~~~java
final class JdkDynamicAopProxy implements AopProxy, InvocationHandler, Serializable
// JdkDynamicAopProxy类结构，由此可知，其实现了InvocationHandler，则必定有invoke方法，来被调用，也就是用户调用bean相关方法时，此invoke()被真正调用
 
    public Object getProxy(ClassLoader classLoader) {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating JDK dynamic proxy: target source is " + this.advised.getTargetSource());
		}
		Class<?>[] proxiedInterfaces = AopProxyUtils.completeProxiedInterfaces(this.advised, true);
		findDefinedEqualsAndHashCodeMethods(proxiedInterfaces);
        
        // JDK proxy 动态代理的标准用法，返回代理对象
		return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
	}
~~~

#### invoke()方法

以上的代码模式可以很明确的看出来，使用了JDK动态代理模式，真正的方法执行在invoke()方法里，下面我们来看下该方法，来看下bean方法如何被代理执行的

~~~java
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MethodInvocation invocation;
		Object oldProxy = null;
		boolean setProxyContext = false;
 
		TargetSource targetSource = this.advised.targetSource;
		Class<?> targetClass = null;
		Object target = null;
 
		try {
            // 以下的几个判断，主要是为了判断method是否为equals、hashCode等Object的方法
			if (!this.equalsDefined && AopUtils.isEqualsMethod(method)) {
				return equals(args[0]);
			}
			else if (!this.hashCodeDefined && AopUtils.isHashCodeMethod(method)) {
				return hashCode();
			}
			else if (method.getDeclaringClass() == DecoratingProxy.class) {
				return AopProxyUtils.ultimateTargetClass(this.advised);
			}
			else if (!this.advised.opaque && method.getDeclaringClass().isInterface() &&
					method.getDeclaringClass().isAssignableFrom(Advised.class)) {
				return AopUtils.invokeJoinpointUsingReflection(this.advised, method, args);
			}
 
			Object retVal;
 
			if (this.advised.exposeProxy) {
				// Make invocation available if necessary.
				oldProxy = AopContext.setCurrentProxy(proxy);
				setProxyContext = true;
			}
            
			target = targetSource.getTarget();
			if (target != null) {
				targetClass = target.getClass();
			}
 
			// 2.获取当前bean被拦截方法链表
			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 
			// 3.如果为空，则直接调用target的method
			if (chain.isEmpty()) {
				Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
				retVal = AopUtils.invokeJoinpointUsingReflection(target, method, argsToUse);
			}
            // 4.不为空，则逐一调用chain中的每一个拦截方法的proceed
			else {
				invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
				retVal = invocation.proceed();
			}
 
			...
			return retVal;
		}
		...
	}
~~~

### 拦截方法真正被执行调用invocation.proceed()

~~~java
public Object proceed() throws Throwable {
		
		if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
			return invokeJoinpoint();
		}
 
		Object interceptorOrInterceptionAdvice =
				this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
		if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
			// Evaluate dynamic method matcher here: static part will already have
			// been evaluated and found to match.
			InterceptorAndDynamicMethodMatcher dm =
					(InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
			if (dm.methodMatcher.matches(this.method, this.targetClass, this.arguments)) {
				return dm.interceptor.invoke(this);
			}
			else {
				// Dynamic matching failed.
				// Skip this interceptor and invoke the next in the chain.
				return proceed();
			}
		}
		else {
			// It's an interceptor, so we just invoke it: The pointcut will have
			// been evaluated statically before this object was constructed.
			return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
		}
	}
~~~

## 总结

  纵观以上过程可知：实际就是为bean创建一个proxy，JDKproxy或者CGLIBproxy，然后在调用bean的方法时，会通过proxy来调用bean方法

  重点过程可分为：

  **1）将AnnotationAwareAspectJAutoProxyCreator注册到Spring容器中**

  **2）AnnotationAwareAspectJAutoProxyCreator类的postProcessAfterInitialization()方法将所有有advice的bean重新包装成proxy**

  **3）调用bean方法时通过proxy来调用，proxy依次调用增强器的相关方法，来实现方法切**

![img](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210425104931.png)



**转载：**https://blog.csdn.net/qq_26323323/article/details/81012855