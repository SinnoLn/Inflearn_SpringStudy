package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//가급적이면 spring 통합테스트보다 자바코드로 짠 단위 테스트들이 더 좋은 테스트임. 웬만하면 자바로 짜자
class MemberServiceTest {
//test 함수 이름은 한글로 해도 상관 없음 (실제로 보여지는게 아니기 때문)
    MemberService memberService;
    //MemberService의 MemoryMemberRepository()와 서로 다른 객체
    //같은걸로 사용해야 하는데 다른 인스턴스를 생성해서 사용하고 있다.
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 각각의 Test 가 끝날 때마다 실행 (데이터를 지우기 때문에 순서와 상관이 없어짐)
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 뭔가 주어졌을때, (지켜야할 문법, 항상 적어놓기)
        Member member = new Member();
        member.setName("hello");

        //when : 언제 실행되고,
        Long saveId = memberService.join(member);

        //then : 결과는 이렇게 나온다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}