package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "member", Grade.BASIC);

        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        System.out.println("(findMember == member) = " + (findMember == member));
    }
}
