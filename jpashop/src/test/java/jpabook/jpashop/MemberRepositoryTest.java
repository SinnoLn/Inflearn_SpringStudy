package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

        @Autowired MemberRepository memberRepository;
        @Test
        @Transactional
        @Rollback(false) //RollBack 안하고 commit 해버림
        public void testMember() {
            Member member = new Member();
            member.setUsername("memberA");
            Long saveId = memberRepository.save(member);

            Member findMember = memberRepository.find(saveId);

            Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
            Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

            Assertions.assertThat(findMember).isEqualTo(member); //jpa 엔티티 동일성 보장
            System.out.println("findMember == member" + (findMember == member));
        }
}