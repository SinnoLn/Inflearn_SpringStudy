package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //테스트는 반복할 수 있어야함. db에 데이터를 insert후 원래 commmit해야하는데 안하고 롤백(즉 저장 안됨)
    //test method 마다 일일히 실행
class MemberServiceIntegrationTest {
    //test 함수 이름은 한글로 해도 상관 없음 (실제로 보여지는게 아니기 때문)
    @Autowired MemberService memberService;
    //MemberService의 MemoryMemberRepository()와 서로 다른 객체
    //같은걸로 사용해야 하는데 다른 인스턴스를 생성해서 사용하고 있다.
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given : 뭔가 주어졌을때, (지켜야할 문법, 항상 적어놓기)
        Member member = new Member();
        member.setName("spring"); //처음에 "hello"였는데 에러 안났음. spring은 이미 존재하는 회원이라고 뜸(db에 데이터 남아있어서)

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