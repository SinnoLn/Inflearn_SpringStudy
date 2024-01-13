package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//MemberService Test
public class MemberServiceTest {
    MemberService memberService;

    //@BeforeEach 애노테이션이 적용된 beforeEach 메서드는 각 테스트가 실행되기 전에 호출
    //AppConfig 클래스의 인스턴스를 생성하고, memberService 변수를 초기화
    //AppConfig는 MemberService의 구현체를 제공하는 설정 클래스
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    //test code를 작성 시 given, when, then 을 지켜야 함
    @Test // JUnit 테스트 메서드임을 나타냄
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember); //찾은 값이 member가 맞는지 검사
        //만약 false일 경우 뭐가 틀렸는지 알려줌
        
    }
}
