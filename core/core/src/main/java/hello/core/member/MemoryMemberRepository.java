package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {
    //interface에서 추상메소드를 가져왔으므로 당연히 오버라이드 해줘야 한다.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); //id와 이름이 매칭돼야 하므로 MAP 사용, 저장
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); //memberid값을 주고 데이터를 가져옴(멤버의 이름)
    }
}
