package cn.colablog.demo.ioc.overview.dependency.injection;

import cn.colablog.demo.ioc.overview.domain.User;
import cn.colablog.demo.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找实例
 *
 * @author Johnson
 * @date 2020/07/09/ 18:05:00
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源以：自定义的Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository);

        // 依赖来源二：依赖注入（内建的依赖）
        System.out.println(userRepository.getBeanFactory());
        // 获取不到，所以是非bean，是内建的依赖
        // beanFactory.getBean(BeanFactory.class);

        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        System.out.println(userRepository.getApplicationContext() == beanFactory);

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 environment：" + environment);
    }


}
