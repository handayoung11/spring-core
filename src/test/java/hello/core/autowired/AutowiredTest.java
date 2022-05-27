package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOptionalTest() {
        //AutowiredTestBean을 parameter로 전달하여 Bean 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutowiredTestBean.class);

    }
}

class AutowiredTestBean {

    @Autowired
    AutowiredTestBean(Optional<Member> member) {
        System.out.println("member = " + member);
    }

    @Autowired(required = false)
    public void setMember1(Member member) {
        System.out.println("setMember1 = " + member);
    }

    @Autowired
    public void setMember2(@Nullable Member member) {
        System.out.println("setMember2 = " + member);
    }

    @Autowired(required = false)
    public void setMember3(Optional<Member> member) {
        System.out.println("setMember3 = " + member);
    }
}
