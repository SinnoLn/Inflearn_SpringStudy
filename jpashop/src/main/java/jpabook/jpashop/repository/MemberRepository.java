package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor //final 필드만 생성자 만들어줌
public class MemberRepository {

    //@PersistenceUnit : manager-factory 를 직접 주입 받음
    //@PersistenceContext //무슨 의미인지 정확하게 파악 (spring boot jpa를 사용해서 @RequiredArgsConstructor를 사용하는 것으로 바꿈)
    //Entity 매니저는 @AutoWired로는 안되고 Persistence Context라는 표준 Annotation이 있어야 인젝션 됨,
    //그러나 스프링 부터 @AutoWired도 인젝션되게 지원을 해줌
    private final EntityManager em;

  /*  public MemberRepository(EntityManager em) {
        this.em = em;
    }*/

    public void save(Member member) {
        em.persist(member);
        //영속성 컨텍스트에 멤버객체를 올림
        //dp, pk와 mapping한게 key가 됨
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //jpql
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
