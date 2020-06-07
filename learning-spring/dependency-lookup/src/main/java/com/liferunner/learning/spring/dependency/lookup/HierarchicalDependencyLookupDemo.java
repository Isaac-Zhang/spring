package com.liferunner.learning.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次型依赖查找 {@link org.springframework.beans.factory.HierarchicalBeanFactory}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/7
 **/
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();

        // 1. 查找 Hierarchical BeanFactory <- 来自于 ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        System.out.println("当前BeanFactory 的 Parent BeanFactory 是 ：" + beanFactory.getParentBeanFactory());

        // 2. 设置 Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
//        System.out.println("当前BeanFactory 的 Parent BeanFactory 是 ：" + beanFactory.getParentBeanFactory());

        // 3. 查看当前 beanfactory 是否包含某个 bean
        displayContainsLocalBean(beanFactory,"personForDemo");

        // 4. 查看父类 beanfactory 是否包含某个 bean
        displayContainsLocalBean(parentBeanFactory,"personForDemo");
        // 5. 递归查找是否包含某个 bean
        dispayContainsBean(beanFactory,"personForDemo");

        applicationContext.close();
    }

    private static void dispayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 bean:[%s] 是否包含 beanName: [%s], 结果：%s \n",
                beanFactory, beanName, containsBean(beanFactory,beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory,beanName)){
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 bean:[%s] 是否包含 beanName: [%s], 结果：%s \n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/spring/Bean-Definition-demo-Context.xml");
        return beanFactory;
    }
}
