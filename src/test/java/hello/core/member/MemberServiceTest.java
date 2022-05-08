package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    private final MemberService memberService = appConfig.memberService();

    @Test
    public void join() {
        //given
        Member member = new Member(1L, "member", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        //then
        assertThat(member).isEqualTo(findMember);
    }
}
