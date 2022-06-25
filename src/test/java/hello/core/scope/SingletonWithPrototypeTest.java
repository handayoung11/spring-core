package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertSame(bean1.getCount(), 1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertSame(bean2.getCount(), 1);
    }

    @Test
    void singletonUsesPrototype() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        assertSame(bean1.logic(), 1);
        ClientBean bean2 = ac.getBean(ClientBean.class);
        assertSame(bean2.logic(), 2);
    }

    @Test
    void singletonUsesPrototypeWithContext() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBeanWithContext.class);
        ClientBeanWithContext bean1 = ac.getBean(ClientBeanWithContext.class);
        assertSame(bean1.logic(), 1);
        ClientBeanWithContext bean2 = ac.getBean(ClientBeanWithContext.class);
        assertSame(bean2.logic(), 1);
    }

    @Scope("singleton")
    static class ClientBeanWithContext {
        private final ApplicationContext ac;

        public ClientBeanWithContext(ApplicationContext ac) {
            this.ac = ac;
        }

        public int logic() {
            PrototypeBean bean = ac.getBean(PrototypeBean.class);
            bean.addCount();
            return bean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
