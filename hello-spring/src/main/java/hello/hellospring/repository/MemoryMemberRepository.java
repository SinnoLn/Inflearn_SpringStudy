package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//기계적으로 개발스러운 용어들로 이름지어야 함

//@Repository //명시해줌
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //고객의 id 세팅(내가 정해주는 부분)
        store.put(member.getId(), member); //store에 고객 id 저장(Map)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id); 원래 사용하던 방식 but NULL값일 경우는?
        return Optional.ofNullable(store.get(id)); //NULL이어도 Client에서 뭘 해줄 수 있다함(다음에..)

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //loof 넘어온 name이 저장된 name과 같은지 확인해서 같으면 return(없으면 Optional에 null 포함되서 반환)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
