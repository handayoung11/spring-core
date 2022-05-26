package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        assertThat(ac.getBean("beanA")).isNotNull();

        assertThatThrownBy(() -> ac.getBean("beanB")).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}

@ComponentScan(includeFilters = @Filter(classes = MyIncludedComponent.class),
        excludeFilters = @Filter(classes = MyExcludedComponent.class))
class ComponentFilterAppConfig {

}
