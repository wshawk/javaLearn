## Spring学习总结
### Spring容器
* **ApplicationContext**
* **BeanFactory**
  
<font color="#dd00dd" size="2">ApplicationContext</font><br/>
  
使用ApplicationContext,配置的Bean如果scope属性是singleton,那么当容器被加载时,这些Bean就会被实例化。好处是可以预先加载,速度快；缺点是耗内存。</br></br>
  
<font color="#dd00dd" size="2">BeanFactory</font><br/>
  
使用BeanFactory,则当你实例化该对象的时候,配置的Bean不会被马上实例化,当你使用的时候才被实例化。BeanFacotry会延迟加载所有的Bean。这样的好处是节约内存,缺点是速度。</br></br>
  
<font color="#dd0000">**推荐使用ApplicationContext**</font></br>

### Bean定义
* **xml配置(需要一个xml配置)**
* **注解配置(也需要一个xml配置文件)**
* **JavaConfig(不需要xml配置文件)**

### Bean的注入
1. xml注入：
        1. 属性注入（set方法注入）
    	2. 构造函数注入
		3. 工厂方法注入
2. 注解注入:
	    @Autowired(根据类型进行注入,若不存在该类型的Bean,则会报错,若存在多个相同类型的Bean,可以使用
	    			@Qualifier注解根据BeanName指定具体的Bean)
	    @Resource(当注解写在字段上时,默认取字段名进行按照名称查找,如果注解写在setter方法上默认取属性
                    名进行装配)
	    @Required(@Required注解用于setter方法,表明这个属性是必要的,不可少的,必须注入值)

### Bean的生命周期(以ApplicationContext为例)
<font color="#FF3030" size="2">注意：Spring 只帮我们管理单例模式 Bean 的完整生命周期,对于 prototype 的 bean ,Spring 在创建好交给使用者之后则不会再管理后续的生命周期。</font><br/>

1、实例化一个Bean－－也就是我们常说的new；

2、按照Spring上下文对实例化的Bean进行配置－－也就是IOC注入；

3、如果这个Bean已经实现了BeanNameAware接口，会调用它实现的setBeanName(String)方法，此处传递的就是Spring配置文件中Bean的id值

4、如果这个Bean已经实现了BeanFactoryAware接口，会调用它实现的setBeanFactory(setBeanFactory(BeanFactory)传递的是Spring工厂自身（可以用这个方式来获取其它Bean，只需在Spring配置文件中配置一个普通的Bean就可以）；

5、如果这个Bean已经实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文（同样这个方式也可以实现步骤4的内容，但比4更好，因为ApplicationContext是BeanFactory的子接口，有更多的实现方法）；
<font color="#FF3030">注：去掉此步骤就是BeanFactory的Bean的生命周期</font><br/>

6、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj, String s)方法，BeanPostProcessor经常被用作是Bean内容的更改，并且由于这个是在Bean初始化结束时调用那个的方法，也可以被应用于内存或缓存技术；

7、如果Bean在Spring配置文件中配置了init-method属性会自动调用其配置的初始化方法。

8、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessAfterInitialization(Object obj, String s)方法、；

<font color="#FF3030">注：以上工作完成以后就可以应用这个Bean了，那这个Bean是一个Singleton的，所以一般情况下我们调用同一个id的Bean会是在内容地址相同的实例</font><br/>

9、当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用那个其实现的destroy()方法；

10、最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法

  

