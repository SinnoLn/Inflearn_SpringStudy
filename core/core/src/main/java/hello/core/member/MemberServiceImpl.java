package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //MemberServiceImpl은 인터페이스 MemberRepository를 의존하고 구현 객체인 MemoryMemberRepository()도 의존함
    //추상체와 구현체에 모두 의존하고 있으므로 DIP위반

    //초기화 후 변경 불가
    //interface를 가져와서 객체 구현 (save와 findById 사용 가능)
    private final MemberRepository memberRepository; //현재 인터페이스에만 의존하고 있음

    @Autowired //의존관게 자동으로 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    //MemberRepository 인스턴스를 반환합니다. 이를 통해 클래스가 사용하는 저장소 인스턴스를 확인 가능.
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    //즉 impl 입장 에서는 어떤 구현 객체가 들어올 지 알 수 없다.
    //impl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 AppConfig에서 결정
}
