package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAllBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = ac.getBean(name);
            System.out.println(name + " = " + bean);
        }
    }

    @Test
    void findApplicationBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(name);
                System.out.println(name + " = " + bean);
            }
        }
    }
}
