package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//테스트 작성시 외부db를 사용하기는 번거로움 (어차피 데이터는 지워야하기 때문에...)
//그래서 test시 아예 단절된 외부 db를 사용하면 편하다 (springboot에서 지원해준다.)
@ExtendWith(SpringExtension.class) //junit 실행시 스프링과 엮어서 실행
@SpringBootTest //springboot를 띄운 상태로 테스트 (@Autowired)
@Transactional //Rollback (jpa에서 같은 트랜잭션 안에서 같은 엔티티(id(pk) 값이 똑같으면 같은 영속성 컨텍스트에서 똑같은 애가 관리)
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //@Autowired EntityManager em; //(@Rollback을 사용 안한다면)

    @Test
    //@Rollback(false) //(정말 db에 값 들어갔는지 확인할 때 사용)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
    }
       
       

}