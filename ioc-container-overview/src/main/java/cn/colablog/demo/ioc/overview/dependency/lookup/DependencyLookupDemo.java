package cn.colablog.demo.ioc.overview.dependency.lookup;

import cn.colablog.demo.ioc.overview.annotation.Super;
import cn.colablog.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找实例
 *
 * @author Johnson
 * @date 2020/07/09/ 18:05:00
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        //----------根据名称查找-------------
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        //----------根据Bean类型查找------------
        lookupByType(beanFactory);

        //----------根据Bean类型查找集合对象
        lookupCollectionByType(beanFactory);

        //----------通过注解查找
        lookupByAnnotationType(beanFactory);
    }

    /**
     * 延迟查找
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        // ObjectFactory<User> objectFactory = beanFactory.getBean(ObjectFactory.class);
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("根据名称查找，延迟查找：" + user);
    }

    /**
     * 实时查找
     * @param beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("根据名称查找，实时查找：" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找，实时查找：" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象：" +beansOfType);
        }
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansOfType = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找到所有Super注解的集合对象：" +beansOfType);
        }
    }
}
