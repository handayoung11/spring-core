package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationContextSameBeanFindTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(DuplicatedTypeBeanConfig.class);

    @Test
    void findDuplicatedTypeBean() {
        assertThatThrownBy(() -> {
            ac.getBean(DiscountPolicy.class);
        }).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    void findBeanByNameWithDuplicatedTypeBeans() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    void findALlDuplicatedTypeBeans() {
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beans.size()).isEqualTo(2);
    }
}

@Configuration
class DuplicatedTypeBeanConfig {
    @Bean
    DiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    DiscountPolicy FixDiscountPolicy() {
        return new FixDiscountPolicy();
    }
}
