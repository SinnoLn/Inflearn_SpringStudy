package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
//    class 레벨에서 돌리면 전체 Test 를 같이 돌릴 수 있음
//    각각의 Test 는 성공인데 전체로 돌리면 틀릴 수 있으니 주의
//    why? 모든 Test 가 순서대로 돌아가는 것이 아니기 때문 (그래서 순서 설계를 잘 해야 함)
//    Test 를 먼저 만들고(틀) 그걸 기반으로 코드를 작성할 경우 "Test 주도 개발" (TDD)
//    그러나 지금은 구현클래스를 먼저 만들고 그걸 기반으로 Test 를 작성 했기 때문에 반대임

    MemoryMemberRepository repository = new MemoryMemberRepository();

//    각각의 Test 가 끝날 때마다 실행 (데이터를 지우기 때문에 순서와 상관이 없어짐)
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result); //Test Case 정상 작동 되는지 확인 Jupiter Assertions
        assertThat(member).isEqualTo(result); //assertj Assertions
    }

    @Test
    public void findByName() {
//        여기서 에러가 나는 이유는 findAll()에서 저장된 spring1의 객체가 나왔기 때문
//        그래서 Test 가 하나 끝나고 나면 데이터를 항상 초기화 해줘야 함
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
//        이미 spring1과 spring2를 이미 저장해버림
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
