package hello.core.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    public void singletonBeanFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertSame(bean1, bean2);
        ac.close();
    }
}

@Scope("singleton")
class SingletonBean {
    @PostConstruct
    public void init() {
        System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("SingletonBean.destroy");
    }
}
