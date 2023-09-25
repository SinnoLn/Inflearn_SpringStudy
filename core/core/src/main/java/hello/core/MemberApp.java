package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member1 = new Member(1L, "memberA", Grade.VIP); //Long id -> id"L"붙여줘야 함
        Member member2 = new Member(2L, "memberB", Grade.BASIC);
        memberService.join(member1); //멤버를 Map에 저장<Key, Data> MemberService -> MemberRepository
        memberService.join(member2);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member2.getName()); //field가 private이기 때문에 get set 메소드를 이용해서 가지고 와야함
        System.out.println("findMember = " + findMember.getName()); //soutv -> 변수값을 간단하게 출력해볼 수 있음

    }
}
